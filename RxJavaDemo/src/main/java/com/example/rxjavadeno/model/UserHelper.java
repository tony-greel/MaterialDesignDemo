package com.example.rxjavadeno.model;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavadeno.BaseActivity;
import com.example.rxjavadeno.Interface.PostRequest;
import com.example.rxjavadeno.activity.RxjavaDemoActivity;
import com.example.rxjavadeno.bean.UserLog;
import com.example.rxjavadeno.bean.UserRegister;
import com.example.rxjavadeno.bean.UserSendCode;
import com.example.rxjavadeno.util.DataCache;
import com.example.rxjavadeno.util.NetworkRequest;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserHelper {
    private static PostRequest postRequest;

    /**
     * 登录方法
     * @param context
     * @param etAc
     * @param etPwd
     * @param tv1
     */
    public static void logIn(final Context context ,
                             String etAc , String etPwd ,
                             final TextView tv1) {
    if (etAc.equals("") || etPwd.equals("")) {
        BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
      return;
    }
    postRequest = NetworkRequest.create().create(PostRequest.class);
    postRequest.login(etAc,"1",etPwd)
            .subscribeOn(Schedulers.io()) // 在IO线程进行请求网络
            .observeOn(AndroidSchedulers.mainThread()) // 回到主线程处理请求
            .subscribe(new Consumer<UserLog>() {
                @Override
                public void accept(UserLog userLog) throws Exception {
                    Log.d("userLog", String.valueOf(userLog.getCode()));
                    String code = String.valueOf(userLog.getCode());
                    if (code.equals("-1")){
                        BaseActivity.showToast(context,"密码或账号错误");
                    }else {
                        BaseActivity.showToast(context,"登录成功！");
                        String tv = userLog.getDatas().getEmail();
                        tv1.setText(tv);
                        Log.d("userLog" , tv);
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    BaseActivity.showToast(context,throwable.getMessage());
                }
            });
  }

    /**
     * 注册方法
     * @param context
     * @param etId
     * @param etPassword
     * @param etEmail
     * @param etVerCode
     */
    public static void register(final Context context ,
                                String etId ,String etPassword ,
                                String etEmail ,String etVerCode) {
    if (etId.equals("") || etPassword.equals("")
            || etEmail.equals("") || etVerCode.equals("")) {
        BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
      return;
    }

    postRequest = NetworkRequest.create().create(PostRequest.class);
    postRequest.reg(etId,etPassword,etEmail,etVerCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<UserRegister>() {@Override public void onSubscribe(Disposable d) {}
              @Override
              public void onNext(UserRegister value) {
                  BaseActivity.showToast(context,value.toString());
                  Log.d("userSendCode", String.valueOf(value.getCode()));
                  String code = String.valueOf(value.getCode());
                  if (code.equals("-1")){
                      BaseActivity.showToast(context,"请先获取验证码！");
                  }else if (code.equals("-2")){
                      BaseActivity.showToast(context,"验证码发送太快，稍后再试吧！");
                  }else {
                      BaseActivity.showToast(context,"注册成功");
                  }
            }
              @Override
              public void onError(Throwable e) {
                  BaseActivity.showToast(context,"服务器连接失败"); }
              @Override
              public void onComplete() { }
            });
  }

    /**
     * 获取邮箱验证码
     * @param context
     * @param mailbox
     */
    public static void verification(final Context context ,
                                    String mailbox) {
      if (mailbox.equals("")){
          BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
          return;
      }
      postRequest = NetworkRequest.create().create(PostRequest.class);
      postRequest.sendCode(mailbox)
              .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<UserSendCode>() {
                  @Override
                  public void accept(UserSendCode userSendCode) throws Exception {
                      Log.d("userSendCode", String.valueOf(userSendCode.getCode()));
                      String code = String.valueOf(userSendCode.getCode());
                      if (code.equals("-1")){
                          BaseActivity.showToast(context,"邮箱已经注册啦，请换个邮箱吧！");
                      }else if (code.equals("-2")){
                          BaseActivity.showToast(context,"验证码发送太快，稍后再试吧！");
                      }else {
                          BaseActivity.showToast(context,"发送验证成功,请注意查收！");
                      }
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      BaseActivity.showToast(context,"服务器连接失败,请检查网络！！");
                  }
              });
  }

    /**
     * 嵌套请求：注册后直接登入
     * @param context
     * @param etId
     * @param etPassword
     * @param etEmail
     * @param etVerCode
     */
    public static void nestedRequest(final Context context ,
                                     String etId , final String etPassword ,
                                     final String etEmail , String etVerCode ){
      postRequest = NetworkRequest.create().create(PostRequest.class);
      postRequest.reg(etId,etPassword,etEmail,etVerCode)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .doOnNext(new Consumer<UserRegister>() {
                  @Override
                  public void accept(UserRegister registerResponse) throws Exception {
                      Log.d("nestedRequest",registerResponse.getMsg().toString());
                  }
              })
              .observeOn(Schedulers.io())
              .flatMap(new Function<UserRegister, ObservableSource<UserLog>>() {
                  @Override
                  public ObservableSource<UserLog> apply(UserRegister userRegister) throws Exception {
                      return postRequest.login(etEmail,"1",etPassword);
                  }
              })
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<UserLog>() {
                  @Override
                  public void accept(UserLog userLog) throws Exception {
                      Log.d("userLog", String.valueOf(userLog.getCode()));
                      String code = String.valueOf(userLog.getCode());
                      if (code.equals("-1")){
                          BaseActivity.showToast(context,"密码或账号错误");
                      }else {
                          BaseActivity.showToast(context,"登录成功！");
                          String Email = userLog.getDatas().getEmail();
                          String Id = String.valueOf(userLog.getDatas().getId());

                          BaseActivity.putSharedPreference(context,"ljj",Email);
                          BaseActivity.putSharedPreference(context,"Email",Id);
                          Intent intent = new Intent(context, RxjavaDemoActivity.class);
                          context.startActivity(intent);
                      }
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      BaseActivity.showToast(context,"连接服务器失败！");
                  }
              });
  }
}
