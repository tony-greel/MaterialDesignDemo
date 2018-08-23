package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.base.HistoricalData;



public class MainAdapter extends CursorAdapter {


    public MainAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public MainAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = new MyViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.iten_main , viewGroup ,false);

        myViewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
        myViewHolder.tv_result = (TextView) view.findViewById(R.id.tv_result);
        myViewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
        view.setTag(myViewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyViewHolder myViewHolder = (MyViewHolder) view.getTag();
        String content = cursor.getString(cursor.getColumnIndex(HistoricalData.CONTENT));
        String result = cursor.getString(cursor.getColumnIndex(HistoricalData.RESULT));
        String time = cursor.getString(cursor.getColumnIndex(HistoricalData.DATE));

        myViewHolder.tv_content.setText(content);
        myViewHolder.tv_result.setText(result);
        myViewHolder.tv_time.setText(time);
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }


    public class MyViewHolder {

        TextView tv_content , tv_result , tv_time;
    }
}
