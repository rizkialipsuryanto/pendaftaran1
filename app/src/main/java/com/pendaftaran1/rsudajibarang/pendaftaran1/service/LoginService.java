package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.pendaftaran1.rsudajibarang.pendaftaran1.model.userLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth/login")
    Call<userLogin> login(@Body userLogin user);


}
