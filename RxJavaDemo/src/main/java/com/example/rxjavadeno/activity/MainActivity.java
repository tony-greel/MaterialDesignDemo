package com.example.rxjavadeno.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rxjavadeno.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Rxjava";

    private Button but_1 , but_2 , but_3 , but_4 , but_5;

    private Integer i = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but_1 = findViewById(R.id.but_1);
        but_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createObserve();
            }
        });

        but_2 = findViewById(R.id.but_2);
        but_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createObserver();
            }
        });

        but_3 = findViewById(R.id.but_3);
        but_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quicklyCreateObserversArray();
            }
        });

        but_4 = findViewById(R.id.but_4);
        but_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { 
                quicklyCreateObserversaggregate();
            }
        });

        but_5 = findViewById(R.id.but_5);
        but_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delayedCreationOfObservers();
            }
        });

    }


    /**
     * 直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
     * 通过 Observable工厂方法创建被观察者对象（Observable）每次订阅后，
     * 都会得到一个刚创建的最新的Observable对象，这可以确保Observable对象里的数据是最新的
     * 动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
     */
    private void delayedCreationOfObservers() {

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建

        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        i = 15;

        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.interval(3 , 1 , TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, "接收到了事件"+ value  );

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


    /**
     * 发送事件的特点：直接发送 传入的集合List数据
     * 快速创建 被观察者对象（Observable） & 发送10个以上事件（集合形式）集合元素遍历
     */
    private void quicklyCreateObserversaggregate() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 2. 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                        Log.d(TAG, "集合遍历");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                        Log.d(TAG, "集合中的数据元素 = "+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                        Log.d(TAG, "遍历结束");
                    }
                });
    }


    /**
     * 快速创建观察者对象
     * 发送事件的特点：直接发送 传入的数组数据
     * 会将数组中的数据转换为Observable对象
     * 快速创建 被观察者对象（Observable） & 发送10个以上事件（数组形式）数组元素遍历
     * 可发送10个以上参数,若直接传递一个list集合进去，否则会直接把list当做一个数据元素发送
     */
    private void quicklyCreateObserversArray() {
        // 1. 设置需要传入的数组
        Integer[] integers = {0 , 1 , 2 , 3 , 4 };

        // 2. 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(integers).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
                Log.d(TAG,"数组遍历");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件"+ value  );
                Log.d(TAG, "数组中的元素 = "+ value  );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
                Log.d(TAG, "遍历结束");
            }
        });
    }


    /**
     * 链式调用来创建观察者
     */
    private void createObserver() {
        // 基本创建 , 创建被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {

            // 传入参数： OnSubscribe 对象
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现由被观察者向观察者的事件传递 & 被观察者调用了观察者的回调方法 ，即观察者模式

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 在复写的subscribe（）里定义需要发送的事件
                // 注：建议发送事件前检查观察者的isUnsubscribed状态，以便在没有观察者时，让Observable停止发射数据
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            } // 至此，一个被观察者对象（Observable）就创建完毕

            // 使用链式调用来创建观察者
        }).subscribe(new Observer<Integer>() {
            // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件"+ value  );
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


    /**
     * 普通观察者模式的创建
     */
    private void createObserve() {
        //  1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }

        });
        // 创建观察者 Observer 并 定义响应事件行为
            Observer<Integer> observer = new Observer<Integer>() {
                // 通过复写对应方法来 响应 被观察者
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "开始采用subscribe连接");
                }

                // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
                }

                // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
                }

                // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
                @Override
                public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
                }
            };
            // 通过订阅（Subscribe）连接观察者和被观察者
        observable.subscribe(observer);

    }
}