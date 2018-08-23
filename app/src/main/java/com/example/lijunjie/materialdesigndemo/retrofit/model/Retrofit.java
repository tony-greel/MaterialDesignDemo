package com.example.lijunjie.materialdesigndemo.retrofit.model;

import com.example.lijunjie.materialdesigndemo.retrofit.model.Interface.IBeanService;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lijunjie on 2018/2/8.
 */

public class Retrofit {
    private IBeanService service;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
              .baseUrl(IBeanService.baseUri)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
        service = retrofit.create(IBeanService.class);
    }

    /**
     * 获取IBeanService实例
     * @return
     */
    public IBeanService getService(){
        return service;
    }
}
