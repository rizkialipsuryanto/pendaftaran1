
package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mLogin;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mUser;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.UserClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
//    Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://127.0.0.1/api.simrs.rsudajibarang/api/auth/login/").addConverterFactory(GsonConverterFactory.create());
//    Retrofit retrofit = builder.build();
//    UserClient userClient = retrofit.create(UserClient.class);

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


    private void login(){

        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call login = restServices.Login("dodi","dodi123");
        login.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //Log.d("CEK", response.toString());
               // Object aa = response.body();
//                JSONArray arr = new JsonArray(response.body());
                //JSONObject arr = new JSONObject()
                        //Log.d("OBJEK", aa.toString());

                try{

                    //ArrayList<JSONObject> aaa = new ArrayList<JSONObject>;

//                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                    String json = gson.toJson(response.body());
//                    String aaaa  = jsonobject

                    JSONObject jo = new JSONObject(response.body().toString());

                    JSONObject rrrr = jo.getJSONObject("response");

                    String b = rrrr.getString("token");
                    Log.d("OBJEK", b);
                }catch(Exception e){
                    Log.d("OBJEK", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("CEK", t.getMessage());
            }
        });

        // ---------------------------------------------------------------------------------


// REST BACA LIST USER  ------------------------------------------------------------------
//        Call  abc =  restServices.listUsers();
//
//        abc.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("CEK", response.toString());
//                Log.d("CEK", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.d("CEK", t.getMessage());
//            }
//        });
// ---------------------------------------------------------------------------------

//        mLogin login = new mLogin("ariguswahyu.id@gmail.com", "54321");
//        Call<mUser> call = userClient.login(login);
//        call.enqueue(new Callback<mUser>() {
//            @Override
//            public void onResponse(Call<mUser> call, Response<mUser> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(Login.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
//                    token = response.body().getToken();
//                }
//                else
//                {
//                    Toast.makeText(Login.this, "login salah :", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<mUser> call, Throwable t) {
//                Toast.makeText(Login.this, "error :", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//    private void getSecret(){
//        Call<ResponseBody> call = userClient.getSecret(token);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()){
//                    try {
//                        Toast.makeText(Login.this, response.body().string(), Toast.LENGTH_SHORT).show();
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(Login.this, "token is not :", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(Login.this, "error :", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}

