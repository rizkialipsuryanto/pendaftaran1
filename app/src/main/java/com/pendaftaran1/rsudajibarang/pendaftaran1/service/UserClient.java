package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mLogin;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<mUser> login(@Body mLogin login);

    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
