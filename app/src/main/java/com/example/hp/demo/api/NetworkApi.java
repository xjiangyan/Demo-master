package com.example.hp.demo.api;


import com.example.hp.demo.bean.NewsBean;
import com.example.hp.demo.bean.TestBean;
import com.example.hp.demo.bean.ZhuanLanAuthor;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetworkApi {

    @GET("/api/columns/{user} ")
        //替换前后对应？？    @Path--写死了对应关系   @Query修饰需要外面传进来对应的值然后拼接  @QueryMap 传map值
    Call<ZhuanLanAuthor> getAuthor(@Path("user") String user);

    @GET("/jisuapi/driverexamQuery")
    Call<TestBean> getQuestion(@QueryMap Map<String, String> params);

    @GET("/")
    Call<NewsBean> getNews(@QueryMap Map<String, String> params);

}
