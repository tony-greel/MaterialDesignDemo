package com.example.lijunjie.materialdesigndemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lijunjie.materialdesigndemo.retrofit.adapter.RecyclerData;
import com.example.lijunjie.materialdesigndemo.retrofit.base.UitImg;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>  {

    private List<VehicleData.PlaceBean> placeBeans;
    private Context mcontext;

    public MainAdapter(List<VehicleData.PlaceBean> placeBeanList , Context context ){
        this.placeBeans = placeBeanList;
        this.mcontext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return placeBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
