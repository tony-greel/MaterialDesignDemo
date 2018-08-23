package com.example.lijunjie.materialdesigndemo.retrofit.activty;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lijunjie.materialdesigndemo.R;
import com.example.lijunjie.materialdesigndemo.retrofit.base.UitImg;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class EnlargeImageActivity extends Activity{

    public static String imageurl;
    public ImageView viewById , image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welfare_details);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewById.setTransitionName("head");
        }
    }

    private void initView() {
        viewById = (ImageView) findViewById(R.id.iv_welfareDetailsActivity);
        Glide.with(this)
                .load(imageurl)
                .into(viewById);
        image=(ImageView) findViewById(R.id.iv_welfare_background);
        Glide.with(this)
                .load(imageurl)
                .override(1500,2100)
                .bitmapTransform(new BlurTransformation(this,23,4))
                .into(image);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                        public void run() {
                        try{
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        }
                        catch (Exception e) {
                            Log.e("Exception when onBack", e.toString());
                        }
                    }
                }.start();
            }
        });
    }
}
