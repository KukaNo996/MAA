package com.sk.maa;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("captchaImage")
    Call<ResponseBody> captchaImage();
    @Headers({"Content-Type: application/json"})
    @POST("login")
    Call<ResponseBody> login(@Body RequestBody body);
    @PUT("system/user/profile/updatePwd")
    Call<ResponseBody> update(@HeaderMap Map<String, String> headers,
                              @QueryMap HashMap<String, String> content);
    @GET("system/notice/list")
    Call<ResponseBody> notice(@HeaderMap Map<String, String> headers,
                              @QueryMap HashMap<String, Integer> content);
    @GET("getInfo")
    Call<ResponseBody> userInfo(@HeaderMap Map<String, String> headers);
    @PUT("system/user/profile")
    Call<ResponseBody> updateUserInfo(@HeaderMap Map<String, String> headers,
                                      @Body RequestBody body);
    @GET("schedule/stype/list")
    Call<ResponseBody> PGItem(@HeaderMap Map<String, String> headers,
                              @QueryMap HashMap<String, Integer> content);
    @GET("schedule/stype/listSite/{type}")
    Call<ResponseBody> SiteInfoItem(@HeaderMap Map<String, String> headers,
                                    @Path("type") Integer type);
}

