package com.example.pictureview.video;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pictureview.R;
import com.example.pictureview.video.adapter.VideoListAdapter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ListViewMultiHolderActivity extends Activity {

    private ListView list_view_tow;
    private VideoListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_multi_holder);
        initView();
        assignment();
        monitorInitialization();
    }

    private void initView() {
        list_view_tow = findViewById(R.id.list_view_tow);
    }

    private void assignment() {
        mAdapter = new VideoListAdapter(this);
        list_view_tow.setAdapter(mAdapter);
    }

    private void monitorInitialization() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
