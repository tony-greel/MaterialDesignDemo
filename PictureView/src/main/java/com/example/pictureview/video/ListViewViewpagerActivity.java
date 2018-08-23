package com.example.pictureview.video;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.ListView;

import com.example.pictureview.R;
import com.example.pictureview.video.adapter.MyAdapter;
import com.example.pictureview.video.adapter.VidepListAdapter;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ListViewViewpagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private MyAdapter myAdapter;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_viewpager);
        initView();
        assignment();
        monitorInitialization();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void assignment() {
        List<View> listViews = new ArrayList<>();
        ListView listView1 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);
        ListView listView2 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);
        ListView listView3 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);

        listView1.setAdapter(new VidepListAdapter(this,0));
        listView2.setAdapter(new VidepListAdapter(this,1));
        listView3.setAdapter(new VidepListAdapter(this,2));

        listViews.add(listView1);
        listViews.add(listView2);
        listViews.add(listView3);

        myAdapter = new MyAdapter(listViews);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
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


    private void monitorInitialization() {
    }

    @Override
    public void onPageScrolled(int position, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
