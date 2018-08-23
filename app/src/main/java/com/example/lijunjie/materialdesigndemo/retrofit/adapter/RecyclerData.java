package com.example.lijunjie.materialdesigndemo.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.lijunjie.materialdesigndemo.R;
import com.example.lijunjie.materialdesigndemo.retrofit.activty.EnlargeImageActivity;
import com.example.lijunjie.materialdesigndemo.retrofit.base.UitImg;

import java.util.List;


/**
 * Created by lijunjie on 2018/2/8.
 */

public class RecyclerData extends RecyclerView.Adapter<RecyclerData.ViewHolder> {

    private List<UitImg.ResultsBean> resultsBeanList;
    private Context context;

    private WelfareItemClickedListener welfareItemClickedListener;

    public void setWelfareItemClickedListener(WelfareItemClickedListener welfareItemClickedListener) {
        this.welfareItemClickedListener = welfareItemClickedListener;

    }

    public interface WelfareItemClickedListener{
        void OnClick(ImageView imageView);
    }

    public RecyclerData(Context context , List<UitImg.ResultsBean> resultsBeans){
        this.context = context;
        this.resultsBeanList = resultsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(holder.imageView.getContext())
                .load(resultsBeanList.get(position).getUrl()) // 图片地址
                .placeholder(R.drawable.img_default_meizi) // 占位图
                .crossFade(1000)
//                .bitmapTransform(new BlurTransformation(context,23,4)) // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
                .override(540,700) // 图片显示的分辨率 ，像素值可以转化为DP再设置
                .priority(Priority.IMMEDIATE) // 设置优先级加载
                .into(holder.imageView); // 给控件
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultsBeanList.get(position).getUrl() != null) {
                    EnlargeImageActivity.imageurl = resultsBeanList.get(position).getUrl();
                    welfareItemClickedListener.OnClick(holder.imageView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeanList.size();
    }

    /**
     * 下拉刷新
     * @param resultsBeans
     */
    public void dropDown(List<UitImg.ResultsBean> resultsBeans) {
        this.resultsBeanList = resultsBeans;
        notifyDataSetChanged();
    }

    /**
     * 上拉加载
     * @param resultsBeans
     */
    public void pullUp(List<UitImg.ResultsBean> resultsBeans) {
        int firstPositon = getItemCount();
        resultsBeanList.addAll(resultsBeans);
        int lastPosition = getItemCount();
        for (int i = firstPositon; i < lastPosition; i++){
            notifyItemInserted(i);
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.img_left);
        }
    }
}

