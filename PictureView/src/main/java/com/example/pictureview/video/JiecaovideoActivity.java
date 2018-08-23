package com.example.pictureview.video;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pictureview.R;
import com.squareup.picasso.Picasso;


import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class JiecaovideoActivity extends AppCompatActivity implements View.OnClickListener{

    private JCVideoPlayerStandard jCVideoPlayerStandard;

    private JCVideoPlayerSimple jCVideoPlayerSimple;

    private Button tiny_window , about_listview , about_ui , about_webview;

    private SensorManager sensorManager;

    private JCVideoPlayer.JCAutoFullscreenListener thumbImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiecaovideo);
        initView();
        monitorInitialization();
        assignment();
    }

    private void initView() {
        jCVideoPlayerStandard = findViewById(R.id.jCVideoPlayerStandard);
        jCVideoPlayerSimple = findViewById(R.id.jCVideoPlayerSimple);

        tiny_window = findViewById(R.id.tiny_window);
        about_listview = findViewById(R.id.about_listview);
        about_ui = findViewById(R.id.about_ui);
        about_webview = findViewById(R.id.about_webview);
    }

    private void monitorInitialization() {
        tiny_window.setOnClickListener(this);
        about_listview.setOnClickListener(this);
        about_ui.setOnClickListener(this);
        about_webview.setOnClickListener(this);
    }

    private void assignment() {
        jCVideoPlayerSimple.setUp("http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "爬取的视频");

        jCVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "爬取的视频");

        Picasso.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360")
                .into(jCVideoPlayerStandard.thumbImageView);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        thumbImageView = new JCVideoPlayer.JCAutoFullscreenListener();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tiny_window:
                jCVideoPlayerStandard.startWindowTiny();
                break;

            case R.id.about_listview:
                startActivity(new Intent(this , ListViewActivirty.class));
                break;
            case R.id.about_ui:
                startActivity(new Intent(this, UIActivity.class));
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 设置传感器传感器
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(thumbImageView, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onBackPressed() {
        if(jCVideoPlayerStandard.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 取消传感器
        sensorManager.unregisterListener(thumbImageView);
        JCVideoPlayer.releaseAllVideos();

    }
}
