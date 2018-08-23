package com.example.lijunjie.materialdesigndemo;


import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

/**
 * Created by lijunjie on 2018/3/23.
 *
 * 使用静态方法和弱引用结合的方式来实现
 *
 */

public class MyHandler {


//    // 对Activity的弱引用
//    private final WeakReference<HandlerActivity> mActivity;
//
//
//    public MyHandler(HandlerActivity handlerActivity) {
//        mActivity = new WeakReference<HandlerActivity>(handlerActivity);
//    }

    private boolean isOk = false;

    public interface onDealListener{
        void onSuccess();
        void onFailure();
    }

    public void onThread(final onDealListener onDealListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 做网络请求耗时操作
                try {
                    Thread.sleep(2000);
                    isOk = true;
                }catch (InterruptedException e){
                    e.printStackTrace();
                    isOk = false;
                }
                final boolean finalIsOk = isOk;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       if (finalIsOk){
                           onDealListener.onSuccess();
                       }else {
                           onDealListener.onFailure();
                       }
                    }
                });
            }
        }).start();
    }

}
