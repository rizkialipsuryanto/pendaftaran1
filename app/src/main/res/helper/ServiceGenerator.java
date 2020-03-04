package com.pendaftaran1.rsudajibarang.pendaftaran1.helper;

import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Base.REST_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}