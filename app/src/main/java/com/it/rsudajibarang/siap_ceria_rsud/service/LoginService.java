package com.it.rsudajibarang.siap_ceria_rsud.service;

import com.it.rsudajibarang.siap_ceria_rsud.model.userLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth/login")
    Call<userLogin> login(@Body userLogin user);


}
