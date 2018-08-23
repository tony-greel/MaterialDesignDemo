package com.example.pictureview.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.pictureview.R;
import com.example.pictureview.video.adapter.RecyclerViewVideoAdapter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;

public class RecyclerViewNormalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewVideoAdapter adapterVideoList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_content);
        initView();
        assignment();
        monitorInitialization();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void assignment() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterVideoList = new RecyclerViewVideoAdapter(this);
        recyclerView.setAdapter(adapterVideoList);
    }

    private void monitorInitialization() {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener(){
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                if(JCVideoPlayerManager.getCurrentJcvd() != null){
                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.getCurrentJcvd();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
