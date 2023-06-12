package com.sk.maa;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    public static String BASE_URL = "http://10.0.2.2/dev-api/";
    @GET("captchaImage")
    Call<ResponseBody> captchaImage();
    @Headers({"Content-Type: application/json"})
    @POST("login")
    Call<ResponseBody> login(@Body RequestBody body);
}

