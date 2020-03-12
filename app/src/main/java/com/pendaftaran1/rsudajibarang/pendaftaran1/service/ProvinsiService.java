package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProvinsiService {

    @GET("/references/provinsi")
    Call<JsonObject> CariProvinsi();
}
