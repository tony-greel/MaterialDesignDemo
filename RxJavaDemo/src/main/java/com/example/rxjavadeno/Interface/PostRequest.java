package com.example.rxjavadeno.Interface;


import com.example.rxjavadeno.bean.UserLog;
import com.example.rxjavadeno.bean.UserRegister;
import com.example.rxjavadeno.bean.UserSendCode;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostRequest {

    String Login = "http://172.20.10.6:8080/";

    @FormUrlEncoded
    @POST("user/login") // 登录
    Observable<UserLog> login (@Field("ac") String ac , @Field("type") String type, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("user/reg") // 注册
    Observable<UserRegister> reg (@Field("name") String name , @Field("pwd") String pwd , @Field("email") String email , @Field("verCode") String verCode);

    @FormUrlEncoded
    @POST("user/sendCode") // 邮箱验证
    Observable<UserSendCode> sendCode (@Field("email") String email );

}
