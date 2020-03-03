package com.pendaftaran1.rsudajibarang.pendaftaran1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
//    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://127.0.0.1/api.simrs.rsudajibarang/api/auth/login/").addConverterFactory(GsonConverterFactory.create());
//    Retrofit retrofit = builder.build();
//    UserClient userClient = retrofit.create(UserClient.class);

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                login();
//            }
//        });
//
//        findViewById(R.id.buttondaftar).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                getSecret();
//            }
//        });


//        GithubService githubService = ServiceGenerator.build().create(GithubService.class);
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
