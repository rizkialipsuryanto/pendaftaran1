

package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
    TextView usernamea, passwordd;

    public final static String TAG_TOKEN = "token";
    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernamea = findViewById(R.id.username);
        passwordd = findViewById(R.id.password);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        token = sharedpreferences.getString(TAG_TOKEN, null);

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
        Call login = restServices.Login(usernamea.getText().toString(),passwordd.getText().toString());
        login.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                try{
                    JSONObject jo = new JSONObject(response.body().toString());

                    JSONObject rrrr = jo.getJSONObject("response");

                    String b = rrrr.getString("token");
                    Log.d("OBJEK", b);
                    if(b.length()>0){
                        Toast.makeText(getApplicationContext(),"Berhasil login", Toast.LENGTH_LONG).show();
                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_TOKEN, b);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(Login.this, indexActivity.class);
                        intent.putExtra(TAG_TOKEN, b);
                        finish();
                        startActivity(intent);
//
//                        Intent i = new Intent(Login.this, indexActivity.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
//                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login gagal", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                        alertDialogBuilder.setMessage("Gagal login");
                    }
//                    Intent i = new Intent(Login.this, indexActivity.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
//                    startActivity(i);
                }catch(Exception e){
//                    Log.d("OBJEK", e.getMessage());
                    Toast.makeText(getApplicationContext(),"Login gagal", Toast.LENGTH_LONG).show();
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

