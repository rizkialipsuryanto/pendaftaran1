package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestServices {



    @GET("/api.simrs.rsudajibarang/api/main/users")
    public Call<JsonObject> listUsers();


    @POST("/api.simrs.rsudajibarang/api/auth/login")
    @FormUrlEncoded
    public Call<JsonObject> Login(@Field("username") String username, @Field("password") String password);


    @GET("/api.simrs.rsudajibarang/api/references/provinsi")
    public Call<JsonObject> ListProvinsi();

    @GET("/api.simrs.rsudajibarang/api/references/kabupaten")
    public Call<JsonObject> ListKabupaten(@Field("idprovinsi") String idprovinsi);


//    @Headers({"Authorization", "Bearer "+ token}) NEK PAKE TOKEN
    @POST("/api.simrs.rsudajibarang/api/auth/registration")
    @FormUrlEncoded
    public Call<JsonObject> RegistrationNewUser(
                                                @Field("firstname") String firstname,
                                                @Field("lastname") String lastname,
                                                @Field("email") String email,
                                                @Field("password") String password );
    


}
