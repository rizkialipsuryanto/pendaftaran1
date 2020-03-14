package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestServices {

    @GET("/api.simrs.rsudajibarang/api/main/users")
    public Call<JsonObject> listUsers();

    @POST("/api.simrs.rsudajibarang/api/auth/login")
    @FormUrlEncoded
    public Call<JsonObject> Login(@Field("username") String username, @Field("password") String password);

    @GET("/api.simrs.rsudajibarang/api/references/provinsi")
    public Call<JsonObject> ListProvinsi();

    @GET("/api.simrs.rsudajibarang/api/references/kabupaten")
    public Call<JsonObject> ListKabupaten(@Query("id") String id);

    @GET("/api.simrs.rsudajibarang/api/references/kecamatan")
    public Call<JsonObject> ListKecamatan(@Query("id") String id);

    @GET("/api.simrs.rsudajibarang/api/references/kelurahan")
    public Call<JsonObject> ListKelurahan(@Query("id") String id);

    @GET("/api.simrs.rsudajibarang/api/references/agama")
    public Call<JsonObject> ListAgama();

    @GET("/api.simrs.rsudajibarang/api/references/pendidikan")
    public Call<JsonObject> ListPendidikan();

    @GET("/api.simrs.rsudajibarang/api/references/pekerjaan")
    public Call<JsonObject> ListPekerjaan();

    @GET("/api.simrs.rsudajibarang/api/references/statuspernikahan")
    public Call<JsonObject> ListStatusPernikahan();

    @GET("/api.simrs.rsudajibarang/api/references/etnis")
    public Call<JsonObject> ListSuku();

    @GET("/api.simrs.rsudajibarang/api/references/bahasa")
    public Call<JsonObject> ListBahasa();

    @GET("/api.simrs.rsudajibarang/api/references/hubunganpasien")
    public Call<JsonObject> ListHubunganPasien();

    @GET("/api.simrs.rsudajibarang/api/references/carabayar")
    public Call<JsonObject> ListCaraBayar();

    @GET("/api.simrs.rsudajibarang/api/references/poliklinik")
    public Call<JsonObject> ListPolik();

//    @Headers({"Authorization", "Bearer "+ token}) NEK PAKE TOKEN
    @POST("/api.simrs.rsudajibarang/api/auth/registration")
    @FormUrlEncoded
    public Call<JsonObject> RegistrationNewUser(
                                                @Field("firstname") String firstname,
                                                @Field("lastname") String lastname,
                                                @Field("email") String email,
                                                @Field("password") String password );

//    NEK PAKE TOKEN
//        @Headers({"Authorization", "Bearer "+ token})
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/api.simrs.rsudajibarang/api/registration/outPatient")
    @FormUrlEncoded
    public Call<JsonObject> PendaftaranPasienLama(
            @Field("pasienbaru") String pasienbaru,
            @Field("nomr") String nomr,
            @Field("tanggal") String tanggal,
            @Field("poliklinik") String poliklinik,
            @Field("dokter") String dokter,
            @Field("jenis_pasien") String jenis_pasien,
            @Field("hubungan") String hubungan,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("penjamin") String penjamin,
            @Field("nobpjs") String nobpjs,
            @Field("norujukan") String norujukan,
            @Header("Authorization") String auth);


//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/api.simrs.rsudajibarang/api/registration/outPatientList")
//    @FormUrlEncoded
    public Call<JsonObject> ListRiwayat(@Header("Authorization") String auth);


//    @Header("Authorization") String auth

}
