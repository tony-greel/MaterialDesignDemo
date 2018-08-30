package com.example.rxjavadeno.model;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.rxjavadeno.BaseActivity;
import com.example.rxjavadeno.Interface.PostRequest;
import com.example.rxjavadeno.bean.UserLog;
import com.example.rxjavadeno.bean.UserRegister;
import com.example.rxjavadeno.bean.UserSendCode;
import com.example.rxjavadeno.util.RetrofitProvider;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserHelper {

  public static void logIn(final Context context , String etAc , String etPwd , final TextView tv1) {
    if (etAc.equals("") || etPwd.equals("")) {
        BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
      return;
    }
    PostRequest postRequest = RetrofitProvider.create().create(PostRequest.class);
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

  public static void register(final Context context , String etId ,String etPassword ,String etEmail ,String etVerCode) {
    if (etId.equals("") || etPassword.equals("")
            || etEmail.equals("") || etVerCode.equals("")) {
        BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
      return;
    }

    PostRequest postRequest = RetrofitProvider.create().create(PostRequest.class);
    postRequest.reg(etId,etPassword,etEmail,etVerCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<UserRegister>() {@Override public void onSubscribe(Disposable d) {}
              @Override
              public void onNext(UserRegister value) {
                  BaseActivity.showToast(context,value.toString()); }
              @Override
              public void onError(Throwable e) {
                  BaseActivity.showToast(context,"服务器连接失败"); }
              @Override
              public void onComplete() { }
            });
  }

  public static void verification(final Context context , String mailbox) {
      if (mailbox.equals("")){
          BaseActivity.showToast(context,"骚年,输入内容不能为空哦！!");
          return;
      }
      PostRequest postRequest = RetrofitProvider.create().create(PostRequest.class);
      postRequest.sendCode(mailbox).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<UserSendCode>() {
                  @Override
                  public void accept(UserSendCode userSendCode) throws Exception {
                      Log.d("userSendCode", String.valueOf(userSendCode.getCode()));
                      String code = String.valueOf(userSendCode.getCode());
                      if (code.equals("-1")){
                          BaseActivity.showToast(context,"邮箱不存在，请检查邮箱是否正确！");
                      }else if (code.equals("-2")){
                          BaseActivity.showToast(context,"验证码发送太快，稍后再试吧！");
                      }else {
                          BaseActivity.showToast(context,"发送验证成功,请注意查收！");
                      }
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      BaseActivity.showToast(context,throwable.getMessage());
                  }
              });
  }

}
