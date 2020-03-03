package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mLogin;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mUser;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.UserClient;

import java.io.IOException;

public class Login extends AppCompatActivity {
//    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://127.0.0.1/api.simrs.rsudajibarang/api/auth/login/").addConverterFactory(GsonConverterFactory.create());
//    Retrofit retrofit = builder.build();
//    UserClient userClient = retrofit.create(UserClient.class);

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View v){

        Intent i = new Intent(Login.this, indexActivity.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    }
    public void Daftar(View v){
        Intent i = new Intent(Login.this, daftarakun.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
        startActivity(i);
    }

}
