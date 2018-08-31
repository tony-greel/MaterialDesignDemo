package com.example.rxjavadeno.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rxjavadeno.BaseActivity;
import com.example.rxjavadeno.Interface.GetRequest_Interface;
import com.example.rxjavadeno.R;
import com.example.rxjavadeno.bean.Translation;
import com.example.rxjavadeno.bean.Translation_2;
import com.example.rxjavadeno.util.DataCache;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxjavaDemoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Rxjava";

    private int i = 0;

    private Button but_1 , but_2 , but_3;
    private TextView tv_1;

    // 定义Observable接口类型的网络请求对象
    private Observable<Translation> observable1;
    private Observable<Translation_2> observable2;

    private Observable<Integer> observable;

    private Disposable mDisposable;

    private DataCache dataCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initView(this);
        monitorInitView();
//        updateUi();
//        stopUpdating();


    }

    private void stopUpdating() {
        observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext(12);
                e.onNext(13);
                e.onNext(14);
            }
        }).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG","onSubscribe");
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Object value) {
                        Log.d(TAG, "onNext: " + value);
                        i++;
                        if (i == 2) {
                            Log.d(TAG, "dispose");
                            mDisposable.dispose();
                            Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void updateUi() {
        // 创建被观察者
        observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() { // 中间类型转换
            @Override
            public String apply(Integer integer) throws Exception {
                return "接受结果" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
        // 创建观察者
        Consumer <Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };
        // 订阅 (通过订阅关联,被观察者与观察者)
//        observable.subscribe(consumer);
        observable.subscribeOn(Schedulers.newThread()) // 指定被观察者在newThread线程中
                .observeOn(AndroidSchedulers.mainThread()) // 指定观察者在main线程中
                .subscribe(consumer);
    }

    private void initView(Context context) {
        but_1 = findViewById(R.id.but_1);
        but_2 = findViewById(R.id.but_2);
        but_3 = findViewById(R.id.but_3);
        tv_1 = findViewById(R.id.tv_1);
        tv_1.setText(BaseActivity.getSharedPreference(context,"ljj","").toString().trim());
    }

    private void monitorInitView() {
        but_1.setOnClickListener(this);
        but_2.setOnClickListener(this);
        but_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_1:
                conditionsEstablish();
                break;

            case R.id.but_2:
                unconditionalEstablish();
                break;

            case R.id.but_3:
                nestedRequest();
                break;
        }
    }

    private void nestedRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        observable1 = getRequest_interface.getCall();
        observable2 = getRequest_interface.grtCall_2();

        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Translation>() {
                    @Override
                    public void accept(Translation translation) throws Exception {
                        Log.d(TAG, "第1次网络请求成功");
                        translation.show();
                    }
                })
        // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
        // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
        // 但对于初始观察者，它则是新的被观察者
                .observeOn(Schedulers.io())
                .flatMap(new Function<Translation, ObservableSource<Translation_2>>() {
                    @Override
                    public ObservableSource<Translation_2> apply(Translation translation) throws Exception {
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return observable2;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Translation_2>() {
                    @Override
                    public void accept(Translation_2 result) throws Exception {
                        Log.d(TAG, "第2次网络请求成功");
                        result.show();
                        // 对第2次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("登录失败");
                    }
                });

    }

    private void conditionsEstablish() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 网络请求 进行封装
        Observable<Translation> observable = getRequest_interface.getCall();

        // 步骤4：发送网络请求 & 通过repeatWhen（）进行轮询
        observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {

            // 在Function函数中，必须对输入的 Observable<Object>进行处理，此处使用flatMap操作符接收上游的数据
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
                // 此处有2种情况：
                // 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
                // 2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询

                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object throwable) throws Exception {
                        // 加入判断条件：当轮询次数 = 5次后，就停止轮询
                        if (i > 3) {
                            // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
                            return Observable.error(new Throwable("轮询结束"));
                        }
                        // 若轮询次数＜4次，则发送1Next事件以继续轮询
                        // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
                        return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(Translation value) {
                        // e.接收服务器返回的数据
                        value.show() ;
                        i++;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 步骤1：采用interval（）延迟发送
     * 注：此处主要展示无限次轮询，若要实现有限次轮询，仅需将interval（）改成intervalRange（）即可
     * 参数说明：参数1 = 第1次延迟时间；参数2 = 间隔时间数字；参数3 = 时间单位；
     * 该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
     **/
    private void unconditionalEstablish() {
        Observable.interval(2,1, TimeUnit.SECONDS)

        /**
         * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
         * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
         **/
        .doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "第 " + aLong + " 次轮询");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://fy.iciba.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                // b. 创建 网络请求接口 的实例
                GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

                // c. 采用Observable<...>形式 对 网络请求 进行封装
                Observable<Translation> observable = request.getCall();

                // d. 通过线程切换发送网络请求
                observable.subscribeOn(Schedulers.io()) // 切换到IO线程进行网络请求
                        .observeOn(AndroidSchedulers.mainThread()) // 切换回到主线程 处理请求结果
                        .subscribe(new Observer<Translation>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onNext(Translation value) {
                                // e.接收服务器返回的数据
                                value.show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "请求失败");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

}
