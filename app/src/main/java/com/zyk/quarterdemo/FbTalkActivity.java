package com.zyk.quarterdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.zyk.quarterdemo.adpters.FullyGridLayoutManager;
import com.zyk.quarterdemo.adpters.GridImageAdapter;
import com.zyk.quarterdemo.base.BaseActivity;
import com.zyk.quarterdemo.beans.RegesterBean;
import com.zyk.quarterdemo.presenter.FbTalkPresenter;
import com.zyk.quarterdemo.view.FbTalkView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：张玉轲
 * 时间：2017/11/29
 */

public class FbTalkActivity extends BaseActivity<FbTalkView, FbTalkPresenter> implements FbTalkView {
    @BindView(R.id.tv_duan_back)
    TextView tvDuanBack;
    @BindView(R.id.tv_duan_share)
    TextView tvDuanShare;
    @BindView(R.id.duan_content)
    EditText duanContent;
    @BindView(R.id.rv)
    RecyclerView rv;
    private FbTalkPresenter fbTalkPresenter;
    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;

    @Override
    public int setView() {
        return R.layout.fb_talk;
    }

    @Override
    public FbTalkPresenter createPresenter() {
        fbTalkPresenter = new FbTalkPresenter(this);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        rv.setAdapter(adapter);


        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(FbTalkActivity.this);
                } else {
                    Toast.makeText(FbTalkActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(FbTalkActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(FbTalkActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(FbTalkActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

        return fbTalkPresenter;
    }



    @OnClick({R.id.tv_duan_back, R.id.tv_duan_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_duan_back:
                finish();
                break;
            case R.id.tv_duan_share:
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                String uid = sharedPreferences.getString("uid", "");
             //   fbTalkPresenter.getData(uid, duanContent.getText().toString());
                ArrayList<File> list=new ArrayList<>();
                for (LocalMedia media : selectList) {
                    File file=new File(media.getPath());
                    list.add(file);
                }
                fbTalkPresenter.getDataPicture(uid,duanContent.getText().toString(),list);
                break;
        }
    }

    @Override
    public void fbTalkBackSuccess(RegesterBean bean) {

        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        if (bean.getMsg().equals("发布成功")) {
            finish();
        }
        if (bean.getMsg().equals("token超时")) {
            startActivity(new Intent(FbTalkActivity.this, LoginActivity.class));
        }

    }

    @Override
    public void fbTalkBackFailure(String code) {
        System.out.println(code);
    }
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            // 进入相册 以下是例子：用不到的api可以不写
            PictureSelector.create(FbTalkActivity.this)
                    .openGallery(1)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .maxSelectNum(9)// 最大图片选择数量 int
                    .minSelectNum(1)// 最小选择数量 int
                    .imageSpanCount(4)// 每行显示个数 int
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .previewVideo(true)// 是否可预览视频 true or false
                    .enablePreviewAudio(true) // 是否可播放音频 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                    .enableCrop(false)// 是否裁剪 true or false
                    .compress(false)// 是否压缩 true or false
                    .glideOverride(300,300)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(3,2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .isGif(true)// 是否显示gif图片 true or false
                    // .compressSavePath(getPath())//压缩图片保存地址
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .openClickSound(false)// 是否开启点击声音 true or false
                    // .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                    .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                    // .cropCompressQuality()// 裁剪压缩质量 默认90 int
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .videoQuality(0)// 视频录制质量 0 or 1 int
                    .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                    //.recordVideoSecond()//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia localMedia : selectList) {
                        String path = localMedia.getPath();
                        System.out.println("图片地址"+path);
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }


}
