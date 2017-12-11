package com.zyk.quarterdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zyk.quarterdemo.utils.MyApplication;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 作者：张玉轲
 * 时间：2017/12/7
 */

public class FbVideoActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_yuan)
    ImageView imgYuan;
    @BindView(R.id.img_dong)
    ImageView imgDong;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    @BindView(R.id.player_list_video)
    JCVideoPlayerStandard player_list_video;
    @BindView(R.id.tv_next1)
    TextView tvNext;
    private Uri fileUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbvideo);
        ButterKnife.bind(this);

        Glide.with(MyApplication.context).asGif().load(R.mipmap.dong1).into(imgDong);

    }

    @OnClick({R.id.img_back, R.id.img_yuan,R.id.tv_next1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_yuan:
                // 创建拍照Intent并将控制权返回给调用的程序
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                try {
                    fileUri = getImageContentUri(new File(Environment.getExternalStorageDirectory(), "psvideo.mp4"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.tv_next1:
                Intent intent1 = new Intent(FbVideoActivity.this,FbVideoItemActivity.class);
                startActivity(intent1);
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imgDong.setVisibility(View.GONE);
                player_list_video.setVisibility(View.VISIBLE);
                //展示视频
                Toast.makeText(this, "Video saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
                System.out.println("===========" + "Video saved to:\n" + data.getData());
                boolean setUp = player_list_video.setUp(data.getData().toString(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                if (setUp) {
                    Glide.with(FbVideoActivity.this).asBitmap().load(data.getData().toString()).into(player_list_video.thumbImageView);
                }

            } else if (resultCode == RESULT_CANCELED) {
            } else {
            }
        }
    }

    //7.0的路径变更
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

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
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}
