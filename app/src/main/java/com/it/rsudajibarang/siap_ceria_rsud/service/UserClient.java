package com.it.rsudajibarang.siap_ceria_rsud.service;

import com.it.rsudajibarang.siap_ceria_rsud.model.mLogin;
import com.it.rsudajibarang.siap_ceria_rsud.model.mUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
