package com.example.retrofitdemo.interfacei;

import com.example.retrofitdemo.base.Translation1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Post_NetworkRequest {
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation1> getCall(@Field("i") String targetSentence);
    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    // 需要配合@Field 向服务器提交需要的字段

    @POST("login") // 登录
    Call<User> login(@Field("ac") String ac , @Field("type") String type, @Field("pwd") String pwd);

    @POST("reg") // 注册
    Call<User> reg(@Field("name") String name , @Field("pwd") String pwd , @Field("email") String email , @Field("verCode") String verCode);

    @POST("sendCode") // 邮箱注册
    Call<User> sendCode(@Field("email") String email );
}
