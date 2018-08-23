package com.example.lijunjie.materialdesigndemo.retrofit.activty;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.example.lijunjie.materialdesigndemo.R;
import com.example.lijunjie.materialdesigndemo.retrofit.adapter.RecyclerData;
import com.example.lijunjie.materialdesigndemo.retrofit.base.UitImg;
import com.example.lijunjie.materialdesigndemo.retrofit.model.Interface.IBeanService;
import com.example.lijunjie.materialdesigndemo.retrofit.model.Retrofit;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitActivity extends Activity{


    private RecyclerView recyclerView;

    private LinearLayoutManager mManagerLayout;

    private IBeanService service;
    private Retrofit retrofit;

    private RecyclerData recyclerData;
    private RefreshLayout swipeRefreshLayout;

    private int page = 1;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initialization();
        setUpRecyclerView(this);
        swipeRefreshLayout.autoRefresh();
        DropDownRefresh(this);
    }

    private void initialization() {
        swipeRefreshLayout = (RefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mManagerLayout = new LinearLayoutManager(this);
    }

    private void setUpRecyclerView(final Context context) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        retrofit = Retrofit.getRetrofit();
        service = retrofit.getService();
    }

    private void requestDate(final Context context , int count, int page) {
        Call<UitImg> call = service.login(count, page++);
        call.enqueue(new Callback<UitImg>() {
            @Override
            public void onResponse(Call<UitImg> call, Response<UitImg> response) {
                if (response.isSuccessful()) {
                    UitImg result = response.body();//关键
                    if (result != null) {
                        recyclerView.setAdapter(new RecyclerData(context, result.getResults()));
                        recyclerData = new RecyclerData(context, result.getResults());
                        recyclerData.setWelfareItemClickedListener(new RecyclerData.WelfareItemClickedListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void OnClick(ImageView imageView) {
                                Pair<View, String> p = new Pair<View, String>(imageView, "head");//haderIv是头像控件
                                Intent intent = new Intent(context, EnlargeImageActivity.class);
                                context.startActivity(intent, ActivityOptions
                                        .makeSceneTransitionAnimation((Activity) context, p).toBundle());
                            }
                        });
                        recyclerView.setAdapter(recyclerData);
                    }
                }
            }
            @Override
            public void onFailure(Call<UitImg> call, Throwable t) {

            }
        });
    }


    /**
     * 刷新数据
     */
    private void DropDownRefresh(final Context context) {

        swipeRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                Call<UitImg> call = service.login(count++, page);
                call.enqueue(new Callback<UitImg>() {
                    @Override
                    public void onResponse(Call<UitImg> call, Response<UitImg> response) {
                        if (response.isSuccessful()) {
                            UitImg result = response.body();//关键
                            if (result != null) {
                                recyclerData.pullUp(result.getResults());
                                recyclerData.setWelfareItemClickedListener(new RecyclerData.WelfareItemClickedListener(){
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void OnClick(ImageView imageView) {
                                        Pair<View, String> p = new Pair<View, String>(imageView, "head");//haderIv是头像控件
                                        Intent intent = new Intent(context, EnlargeImageActivity.class);
                                        context.startActivity(intent, ActivityOptions
                                                .makeSceneTransitionAnimation((Activity) context, p).toBundle());
                                    }
                                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UitImg> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.finishLoadMore(true);//结束加载
            }


            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestDate(context,count,page++);

                swipeRefreshLayout.finishRefresh(0, true);//传入false表示刷新失败
            }
        });
    }



}
