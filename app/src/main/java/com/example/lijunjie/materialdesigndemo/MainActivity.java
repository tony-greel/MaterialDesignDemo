package com.example.lijunjie.materialdesigndemo;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button bt_1 , bt_2;
    private TextView tv;
    public String string;
    private ImageView img_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
        tv = findViewById(R.id.tv);
        img_1 = findViewById(R.id.img_1);

        String path = "http://seopic.699pic.com/photo/50026/1350.jpg_wh1200.jpg";
        Test.downloadImager(path, new Test.OnDealListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                img_1.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                final Request request = builder.get().url("https://interface.meiriyiwen.com/article/today?dev=1").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("LJJ","失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException{
                        string = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(string);
                            }
                        });
                    }
                });
            }
        });


        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://47.105.102.218/lastPlace";
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody
                        .Builder()
                        .add("Serial","6170775417")
                        .build();
                final Request request = new Request
                        .Builder()
                        .post(formBody)
                         .url(url)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // 此方法运行在子线程中，不能在此方法中进行UI操作。
                        String result = response.body().string();
//                        Log.d("Ljj", result);
                        response.body().close();
                        jsonx(result);
                    }
                });
            }
        });
    }

    private void jsonx(String response) {
        Log.d("Ljj",response.toString());
        final String json = response;
        // 2 解析Json数据
        Gson gson = new Gson();
        final VehicleData vehicleData = gson.fromJson(json, VehicleData.class);
        // 3 判断json数据
        if (vehicleData.getState().equals("error")){
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }else {
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    tv.setText(json);
//                    tv.setText(vehicleData.toString());
//                }
//            });
            new MyHandler().onThread(new MyHandler.onDealListener() {
                @Override
                public void onSuccess() {
                    tv.setText(json);
                    tv.setText(vehicleData.getDate());
                }

                @Override
                public void onFailure() {
                    Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}