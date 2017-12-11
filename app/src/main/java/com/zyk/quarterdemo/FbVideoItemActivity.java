package com.zyk.quarterdemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;
import com.zyk.quarterdemo.base.BaseActivity;
import com.zyk.quarterdemo.base.BaseFragment;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.presenter.ViedoPresenter;
import com.zyk.quarterdemo.utils.GlideLoader;
import com.zyk.quarterdemo.view.IVideoView;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：张玉轲
 * 时间：2017/12/7
 */

public class FbVideoItemActivity extends BaseActivity<IVideoView,ViedoPresenter> implements IVideoView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.feng)
    ImageView feng;
    @BindView(R.id.fengmian)
    TextView fengmian;
    @BindView(R.id.tongbu)
    TextView tongbu;
    @BindView(R.id.len)
    LinearLayout len;
    @BindView(R.id.linq)
    LinearLayout linq;
    @BindView(R.id.fabu)
    TextView fabu;
    private AMapLocationClientOption mLocationOption;
    private AMapLocationClient mLocationClient;
    private double lat;
    private double log;
    private ViedoPresenter viedoPresenter;
    private File file;
    private File file2;
    private ArrayList<String> path = new ArrayList<>();
    public static final int REQUEST_CODE = 1001;
    private File cover;

    @Override
    public int setView() {
        return R.layout.fbvideo_item;
    }

    @Override
    public ViedoPresenter createPresenter() {
        viedoPresenter = new ViedoPresenter(this);
        initdata();
        return viedoPresenter;
    }

    public void initdata() {
        init();
        File filepath = Environment.getExternalStorageDirectory();
        file = new File(filepath.getAbsolutePath() + filepath.separator + "psvideo.mp4");
        //  file2 = new File(filepath.getAbsolutePath() + filepath.separator + "image.jpg");
    }

    //定位
    public void init() {
        //声明AMapLocationClient类对象
        //声明AMapLocationClientOption对象
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //经度
                        log = aMapLocation.getLongitude();
                        //纬度
                        lat = aMapLocation.getLatitude();
                        //可在其中解析amapLocation获取相应内容。
                        System.out.println("====经度===" + aMapLocation.getLongitude() + "=====纬度===" + aMapLocation.getLatitude());
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //点击事件
    @OnClick({R.id.back, R.id.feng, R.id.fengmian, R.id.fabu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.feng:
                break;
            case R.id.fengmian:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(Color.rgb(24, 172, 252))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(Color.rgb(24, 172, 252))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        .singleSelect()
                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(FbVideoItemActivity.this, imageConfig); // 开启图片选择器
                break;
            case R.id.fabu:
                SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
                viedoPresenter.getData(preferences.getString("uid",""),file,cover,String.valueOf(log),String.valueOf(lat));
                break;
        }
    }

    @Override
    public void videoBackSuccess(RegesterBean bean) {
    Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void videoBackFailure(String code) {
        System.out.println(code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            System.out.println("===================1111111");
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                System.out.println("================图片path："+path);
                Glide.with(this).load(path).into(feng);
                cover = new File(path);
                System.out.println("=======================cover:"+cover.getPath());
            }
            path.clear();
            path.addAll(pathList);
        }
    }


    //权限
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }
    }

    /**
     *
     * @param permissions
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23){
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer)checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean)shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否允许高德定位此设备的位置信息？");

        // 拒绝, 退出应用
        builder.setNegativeButton("拒绝",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("允许",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
