package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mLogin;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mUser;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.UserClient;

import java.io.IOException;

public class Login extends AppCompatActivity {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://127.0.0.1/api.simrs.rsudajibarang/api/auth/login/").addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                login();
            }
        });

        findViewById(R.id.buttondaftar).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getSecret();
            }
        });
    }

    public void Login(View v){

//        Intent i = new Intent(Login.this, indexActivity.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
//        startActivity(i);
    }
    public void Daftar(View v){
        Intent i = new Intent(Login.this, daftarakun.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    }

    private void login(){
        mLogin login = new mLogin("ariguswahyu.id@gmail.com", "54321");
        Call<mUser> call = userClient.login(login);
        call.enqueue(new Callback<mUser>() {
            @Override
            public void onResponse(Call<mUser> call, Response<mUser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Login.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                }
                else
                {
                    Toast.makeText(Login.this, "login salah :", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mUser> call, Throwable t) {
                Toast.makeText(Login.this, "error :", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSecret(){
        Call<ResponseBody> call = userClient.getSecret(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        Toast.makeText(Login.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "token is not :", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Login.this, "error :", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
