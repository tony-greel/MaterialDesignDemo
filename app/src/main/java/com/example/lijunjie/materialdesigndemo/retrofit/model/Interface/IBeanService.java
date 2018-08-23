package com.example.lijunjie.materialdesigndemo.retrofit.model.Interface;


import com.example.lijunjie.materialdesigndemo.retrofit.base.UitImg;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lijunjie on 2018/2/8.
 */

public interface IBeanService {
    String baseUri = "http://gank.io/api/data/福利/";

    @GET("{count}/{page}") // 连上页码
    Call<UitImg> login(@Path("count") int count, @Path("page") int page);


}


