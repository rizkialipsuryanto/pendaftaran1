package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestServices {

    @GET("/api.simrs.rsudajibarang/api/main/users")
    public Call<JsonObject> listUsers();
}
