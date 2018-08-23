package com.example.lijunjie.materialdesigndemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

  public interface OnDealListener{
    void onSuccess(Bitmap bitmap);
    void onFailure();
  }

  public static void downloadImager(final String path, final OnDealListener onDealListener){
    final Handler handler = new Handler(Looper.getMainLooper());
    new Thread(new Runnable() {
      @Override
      public void run() {
        Bitmap bitmap = null;
        try {
          File file = new File("/sdcard/ljj.jpg");
          if (!file.exists()){
            download(path);
          }
          bitmap = BitmapFactory.decodeFile("/sdcard/ljj.jpg");
        } catch (Exception e) {
          e.printStackTrace();
          bitmap = null;
        }
        final Bitmap finalBitmap = bitmap;
        handler.post(new Runnable() {
          @Override
          public void run() {
            if (finalBitmap == null){
              onDealListener.onFailure();
            }else {
              onDealListener.onSuccess(finalBitmap);
            }
          }
        });
      }
    }).start();
  }

  public static void download(String path) throws Exception{
    URL url = new URL(path);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //在头里面请求下载开始位置和结束位置
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Charset","UTF-8");
    conn.setConnectTimeout(8000);
    conn.setReadTimeout(8000);
    InputStream inputStream = conn.getInputStream();
    File f = new File("/sdcard/ljj.jpg");
    if (!f.exists()){
      f.createNewFile();
    }
    RandomAccessFile file = new RandomAccessFile(f,"rwd");
    file.seek(0);
    byte[] buffer = new byte[1024];
    int len;
    while ((len = inputStream.read(buffer)) != -1){
      file.write(buffer,0,len);
    }
    conn.disconnect();
    inputStream.close();
    file.close();
  }

}
