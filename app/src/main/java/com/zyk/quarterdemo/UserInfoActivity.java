package com.zyk.quarterdemo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.zyk.quarterdemo.base.BaseActivity;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.beans.UserBean;
import com.zyk.quarterdemo.presenter.UserInfoPresenter;
import com.zyk.quarterdemo.utils.RandomUtil;
import com.zyk.quarterdemo.utils.RetrofitUtils;
import com.zyk.quarterdemo.utils.Utils;
import com.zyk.quarterdemo.view.IUserInfoView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import imageloader.bwie.com.imageloaderlibrary.UtilImage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：张玉轲
 * 时间：2017/11/30
 */

public class UserInfoActivity extends BaseActivity<IUserInfoView,UserInfoPresenter> implements IUserInfoView{


    @BindView(R.id.user_tv_back)
    TextView userTvBack;
    @BindView(R.id.user_save)
    TextView userSave;
    @BindView(R.id.userinfo_img)
    ImageView userImg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_msg)
    TextView userMsg;
    private UserInfoPresenter userInfoPresenter;
    private SharedPreferences sharedPreferences;
    protected static final int CHOOSE_PICTURE = 0;//choose picture
    protected static final int TAKE_PICTURE = 1;//tack picture
    private static final int CROP_SMALL_PICTURE = 2;//crop_small_picture
    private Uri tempUri;
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int GO_TO_SETTING_REQUEST_CODE = 10;
    public static String TAG = "permission_tag";

    @Override
    public int setView() {
        return R.layout.userinfo_item;
    }

    @Override
    public UserInfoPresenter createPresenter() {
        userInfoPresenter = new UserInfoPresenter(this);
        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        userInfoPresenter.getData(sharedPreferences.getString("uid",""));
        return userInfoPresenter;
    }


    @OnClick({R.id.user_tv_back, R.id.user_save, R.id.userinfo_img, R.id.user_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_tv_back:
               startActivity( new Intent(UserInfoActivity.this,MainActivity.class));
                break;
            case R.id.user_save:
                sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                userInfoPresenter.getData(sharedPreferences.getString("uid",""));
                break;
            case R.id.userinfo_img:
                showChoosePicDia();
                break;
            case R.id.user_msg:
                Intent intent1 = new Intent(UserInfoActivity.this, ChangerNameActivity.class);
                startActivity(intent1);
                break;
        }
    }

    //调用view层的成功的方法
    @Override
    public void userInfoBackSuccess(UserBean bean) {
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();

        userName.setText(bean.getData().getUsername()==null?"昵称":bean.getData().getUsername());
        userMsg.setText(sharedPreferences.getString("nicheng",""));
        String icon = (String) bean.getData().getIcon();
        if (icon != null) {
            ImageLoader.getInstance().displayImage(icon,userImg, UtilImage.getOptions());
            //userImg.setImageURI(Uri.parse(icon));
        }

        //保存图像
        sharedPreferences.edit().putString("icon", (String) bean.getData().getIcon()).commit();
    }
    //调用view层的失败的方法
    @Override
    public void userinfoBackFailure(String code) {
        Logger.i("===="+code);
    }
    //展示图片
    public void showChoosePicDia(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items={"本地照片","拍照"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case CHOOSE_PICTURE://本地相册
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE://拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = getImageContentUri(new File(Environment.getExternalStorageDirectory(), "raw.png"));
                        // tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "raw.png"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }
    //获得contentProvider中的我们拍照后的图片，这个是7.0后版本变成了24所以获得Uri的方式也由Uri.fromUri变成了ContentProvider查询方式获得Uri
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);

                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            } else {
                return null;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */

    private void startPhotoZoom(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "PNG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, CROP_SMALL_PICTURE);//同样的在onActivityResult中处理剪裁好的图片
    }


    /* * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Utils utils=new Utils();
            photo = utils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            userImg.setImageBitmap(photo);
            setFile(photo,null);


        }
    }

    //保存图片
    private void setFile(Bitmap photo,File file1) {
        String fileName = RandomUtil.getRandomFileName();
        if (file1==null){
            file1=new File(Environment.getExternalStorageDirectory()+"/image"+fileName+".png");
        }

        try {
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(file1));
            //转换格式
            photo.compress(Bitmap.CompressFormat.JPEG,100,bout);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        uploadPic(file1);
    }

    /**
     * 上传头像
     * @param
     */
    private void  uploadPic(File file) {
        System.out.println("头像的路径"+file.getPath());
        String uid = sharedPreferences.getString("uid", "");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Observable<RegesterBean> data = new RetrofitUtils.Builder()
                .addConverterFactorys(GsonConverterFactory.create())
                .addCallAdapterFactorys(RxJava2CallAdapterFactory.create())
                .build().service().getUserHeader(uid,multipartBody);
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegesterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegesterBean regesterBean) {
                        Toast.makeText(UserInfoActivity.this,regesterBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("e================="+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //请求权限和打开相机
    public void openCamera(){
       //检测是否授权
        if (ContextCompat.checkSelfPermission(this,CAMERA_PERMISSION)== PackageManager.PERMISSION_GRANTED){
            Logger.i(TAG,"===========检查权限---用户已经拥有相机这个权限了");
            startCamera();
        }else{
            Log.e(TAG, "===========检查权限---用户没有相机这个权限");
            ActivityCompat.requestPermissions(this,new String[]{CAMERA_PERMISSION},CAMERA_PERMISSION_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                if (permissions[0].equals(CAMERA_PERMISSION)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "===========权限回调---用户同意了");
                        startCamera();
                    } else {
                        Log.e(TAG, "===========权限回调---用户拒绝了");

                        if(ActivityCompat.shouldShowRequestPermissionRationale(this,CAMERA_PERMISSION)){
                            Log.e(TAG,"=========== shouldShowRequestPermissionRationale 返回值为 true");

                            //返回tue 因为系统刚刚有权限弹窗，所以不用解释了，直接告诉用户如何开启权限
                            showTipGoSetting();
                        }else {
                            //返回false ，用户勾选了  不再询问，之后系统也不会再弹出系统权限弹框，所以我们自己弹框解释
                            Log.e(TAG,"=========== shouldShowRequestPermissionRationale 返回值为 false");
                            showTipExplainPermission();
                        }
                    }
                }
                break;
        }
    }
    /**
     * 对话框 -- 给用户解释需要的权限
     */
    public void showTipExplainPermission() {
        new AlertDialog.Builder(this)
                .setTitle("说明")
                .setMessage("需要相机权限，去拍照")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //告诉用户怎么去打开权限
                        showTipGoSetting();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    /**
     * 对话框 -- 告诉用户怎么去打开权限
     */
    public void showTipGoSetting() {
        new AlertDialog.Builder(this)
                .setTitle("需要打开相机权限")
                .setMessage("在设置-权限中去打开相机权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //告诉用户怎么去打开权限
                        goToSetting();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    /**
     * 跳转到设置权限页面
     */
    private void goToSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",getPackageName(),null);
        intent.setData(uri);
        startActivityForResult(intent,GO_TO_SETTING_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                case GO_TO_SETTING_REQUEST_CODE:
                    if (ContextCompat.checkSelfPermission(this,CAMERA_PERMISSION)==PackageManager.PERMISSION_GRANTED){
                        Log.e(TAG, "===========设置页面返回之后-再次检查权限---用户已经拥有相机这个权限了");
                        startCamera();
                    } else {
                        Log.e(TAG, "===========设置页面返回之后-再次检查权限---用户没有开启这个权限，在这不用再去请求权限了");
                    }
                    break;
            }
        }

    }

    //打开相机
    public void startCamera(){
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "raw.png"));
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

}
