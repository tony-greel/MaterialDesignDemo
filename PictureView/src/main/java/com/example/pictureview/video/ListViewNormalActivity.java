package com.example.pictureview.video;


import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.pictureview.R;
import com.example.pictureview.video.adapter.VidepListAdapter;


import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class ListViewNormalActivity extends AppCompatActivity{

    ListView listView;

    VidepListAdapter videpListAdapter;

    SensorManager sensorManager;

    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_normal);
        initView();
        assignment();
    }

    private void initView() {
        listView = findViewById(R.id.list_view);

    }

    private void assignment() {
        videpListAdapter = new VidepListAdapter(this);
        listView.setAdapter(videpListAdapter);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }


    @Override
    public void onBackPressed() {
        if(JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
