package com.example.retrofitdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.adapter.MainAdapter;
import com.example.retrofitdemo.base.HistoricalData;
import com.example.retrofitdemo.base.Translation;
import com.example.retrofitdemo.base.Translation1;
import com.example.retrofitdemo.database.InfoDatabaseHelper;
import com.example.retrofitdemo.interfacei.Gte_NetworkRequest;
import com.example.retrofitdemo.interfacei.Post_NetworkRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_get , button_post;

    private TextView tv_chinese;

    private EditText et_english;

    private ListView list_view;

    private MainAdapter mainAdapter;

    private static final String TAG = "MainActivity";

    private SQLiteDatabase database;

    private SQLiteOpenHelper openHelper = null;

    private InfoDatabaseHelper infoDatabaseHelper;

    private String content;
    private String orderBy;//查询数据库的排序
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        monitorInitView();
    }

    private void initView() {
        tv_chinese = findViewById(R.id.tv_chinese);
        et_english = findViewById(R.id.et_english);
        button_get = findViewById(R.id.butt_1);
        button_post = findViewById(R.id.butt_2);
        list_view = findViewById(R.id.list_view);
    }

    private void initData() {
        openHelper = new InfoDatabaseHelper(this);
        database = openHelper.getWritableDatabase();

        orderBy = "_id desc";
        Cursor cursor = database.query(
                HistoricalData.PERSON_INFO_TABLE,
                null,null,
                null,null,
                null,orderBy);
        mainAdapter = new MainAdapter(MainActivity.this,cursor);
        list_view.setAdapter(mainAdapter);

    }

    private void monitorInitView() {
        button_get.setOnClickListener(this);
        button_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.butt_1:
                get_Request();
                break;
            case R.id.butt_2:
                post_Request();
                break;
        }
    }

    private void get_Request() {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置网络请求Url
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建网络请求接口的实例
        Gte_NetworkRequest request = retrofit.create(Gte_NetworkRequest.class);

        // 对发送请求进行封装
        Call<Translation> call = request.getCall();

        // 发送网络请求
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Log.d("TAG" , response.body().toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.d("TAG" , String.valueOf(t));
            }
        });
    }

    private void post_Request() {

        content = et_english.getText().toString();

        if(content.equals("")){
            Toast.makeText(this, "骚年,输入内容不能为空哦！", Toast.LENGTH_SHORT).show();
            return;
        }

        infoDatabaseHelper = new InfoDatabaseHelper(this);
        database = infoDatabaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from "+HistoricalData.PERSON_INFO_TABLE+" where CONTENT=?",new String[]{content});

        Log.d("WSS",cursor.toString());

        if (content.equals(cursor)){
            Toast.makeText(this, "草你妈,没点记性,这个单词已经查过一次了,自己去翻", Toast.LENGTH_SHORT).show();
        }else {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建网络请求接口的实例
        final Post_NetworkRequest request = retrofit.create(Post_NetworkRequest.class);

        // 对发送请求进行封装
        Call<Translation1> call = request.getCall(content);

        // 发送网络请求
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                Log.d("TAG" , response.body().toString());

                if (response.body().getErrorCode() == 1){
                    Toast.makeText(MainActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                }else {
                    tv_chinese.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(HistoricalData.CONTENT,content);
                    contentValues.put(HistoricalData.RESULT,response.body().getTranslateResult().get(0).get(0).getTgt());
                    contentValues.put(HistoricalData.DATE,"2018/8/21");

                    // 把EditText中的文本插入数据库
                    database.insert(HistoricalData.PERSON_INFO_TABLE,null,contentValues);

                    // 根据 _id 降序插叙数据库保证最后插入的在最上面
                    Cursor cursor = database.query(
                            HistoricalData.PERSON_INFO_TABLE,
                            null,null,
                            null,null,
                            null,orderBy);

                    // Cursor改变调用chanageCursor()方法
                    mainAdapter.changeCursor(cursor);
                }
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                Log.d("TAG" , "失败");
            }
        });
        }
    }
}
