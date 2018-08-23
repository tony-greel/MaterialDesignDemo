package com.example.pictureview.pictureSelector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pictureview.R;
import com.example.pictureview.pictureSelector.adapter.MainAdapter;
import com.example.pictureview.pictureSelector.base.ImgData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<ImgData>imgDataList = new ArrayList<>();

    private MainAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new MainAdapter(this,imgDataList);
        recyclerView.setAdapter(adapter);
    }

    private void init() {
        ImgData imgData = new ImgData(R.mipmap.ic_launcher);
        imgDataList.add(imgData);
        ImgData imgData1 = new ImgData(R.mipmap.ic_launcher);
        imgDataList.add(imgData1);
        ImgData imgData2 = new ImgData(R.mipmap.ic_launcher);
        imgDataList.add(imgData2);

//        //需要加载的网络图片
//        String[] urls = {
//                "http://a.hiphotos.baidu.com/image/pic/item/00e93901213fb80e3b0a611d3fd12f2eb8389424.jpg",
//                "http://b.hiphotos.baidu.com/image/pic/item/5243fbf2b2119313999ff97a6c380cd790238d1f.jpg",
//                "http://f.hiphotos.baidu.com/image/pic/item/43a7d933c895d1430055e4e97af082025baf07dc.jpg"
//        };
//        imgDataList.add(1,urls);
    }
}
