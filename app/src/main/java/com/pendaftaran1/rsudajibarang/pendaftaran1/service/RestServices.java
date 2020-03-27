package com.pendaftaran1.rsudajibarang.pendaftaran1.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("/api.simrs.rsudajibarang/api/references/title")
    public Call<JsonObject> ListTitle();

    @GET("/api.simrs.rsudajibarang/api/references/bahasa")
    public Call<JsonObject> ListBahasa();

    @GET("/api.simrs.rsudajibarang/api/references/hubunganpasien")
    public Call<JsonObject> ListHubunganPasien();

    @GET("/api.simrs.rsudajibarang/api/references/carabayar")
    public Call<JsonObject> ListCaraBayar();

    @GET("/api.simrs.rsudajibarang/api/references/poliklinik")
    public Call<JsonObject> ListPolik();

    @GET("/api.simrs.rsudajibarang/api/dokter/dokterjaga/{kodePoli}")
    public Call<JsonObject> ListDokter(@Path("kodePoli") String kodePoli,
                                       @Header("Authorization") String auth);


    @POST("/api.simrs.rsudajibarang/api/auth/registration")
    @FormUrlEncoded
    public Call<JsonObject> RegistrationNewUser(
                                                @Field("firstname") String firstname,
                                                @Field("lastname") String lastname,
                                                @Field("email") String email,
                                                @Field("password") String password );


    @POST("/api.simrs.rsudajibarang/api/registration/outPatient")
    @FormUrlEncoded
    public Call<JsonObject> PendaftaranPasienLama(
            @Field("pasienbaru") String pasienbaru,
            @Field("nomr") String nomr,
            @Field("tanggal") String tanggal,
            @Field("poliklinik") String poliklinik,
            @Field("dokter") String dokter,
            @Field("hubungan") String hubungan,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("penjamin") String penjamin,
            @Field("nobpjs") String nobpjs,
            @Field("norujukan") String norujukan,
            @Field("userid") String userid,
            @Header("Authorization") String auth);

    @POST("/api.simrs.rsudajibarang/api/registration/outPatient")
    @FormUrlEncoded
    public Call<JsonObject> PendaftaranPasienBaru(
            @Field("pasienbaru") String pasienbaru,
            @Field("tanggal") String tanggal,
            @Field("poliklinik") String poliklinik,
            @Field("dokter") String dokter,
            @Field("hubungan") String hubungan,
            @Field("notelp") String notelp,
            @Field("penjamin") String penjamin,
            @Field("nobpjs") String nobpjs,
            @Field("norujukan") String norujukan,
            @Field("userid") String userid,
            @Field("nama") String nama,
            @Field("nik") String nik,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("alamat_sesuai_ktp") String alamat_sesuai_ktp,
            @Field("provinsi_id") String provinsi_id,
            @Field("kabupaten_id") String kabupaten_id,
            @Field("kecamatan_id") String kecamatan_id,
            @Field("kelurahan_id") String kelurahan_id,
            @Field("nama_ayah") String nama_ayah,
            @Field("nama_ibu") String nama_ibu,
            @Field("nama_suami") String nama_suami,
            @Field("nama_istri") String nama_istri,
            @Field("agama_id") String agama_id,
            @Field("pendidikan_id") String pendidikan_id,
            @Field("pekerjaan_id") String pekerjaan_id,
            @Field("status_kawin_id") String status_kawin_id,
            @Field("kewarganegaraan") String kewarganegaraan,
            @Field("suku") String suku,
            @Field("bahasa_daerah") String bahasa_daerah,
            @Field("nama_title") String nama_title,
            @Header("Authorization") String auth);

    @GET("/api.simrs.rsudajibarang/api/registration/outPatientList")
    public Call<JsonObject> ListRiwayat(@Query("id") String id,
                                        @Header("Authorization") String auth);

    @GET("/api.simrs.rsudajibarang/api/registration/outPatientList")
    public Call<JsonObject> Decode(@Header("Authorization") String auth);

    @POST("/api.simrs.rsudajibarang/api/registration/outPatient")
    @FormUrlEncoded
    public Call<JsonObject> EditProfil(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("nik") String nik,
            @Field("alamat") String alamat,
            @Field("nohp") String nohp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("userid") String userid,
            @Header("Authorization") String auth);
}
