

package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.SliderImageAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.user.LoginResponseRepos;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    ViewPager viewPager;

    String token;
    TextView usernamea, passwordd;

    public static ProgressDialog pDialog;

    public final static String TAG_TOKEN = "token";
    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    RestServices restServices = ServiceGenerator.build().create(RestServices.class);
    Gson  _messageGson = new Gson();
    LoginResponseRepos _loginResponseReposMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernamea = findViewById(R.id.username);
        passwordd = findViewById(R.id.password);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        token = sharedpreferences.getString(TAG_TOKEN, null);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        SliderImageAdapter viewPagerAdapter = new SliderImageAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                login();
            }
        });
        findViewById(R.id.forgetpassword).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ForgetPassword();
            }
        });

    }

    public void Login(View v){

        Intent i = new Intent(Login.this, indexActivity.class);
        startActivity(i);
    }
    public void Daftar(View v){
        Intent i = new Intent(Login.this, daftarakun.class);
        startActivity(i);
    }

    private void ForgetPassword(){
        Intent i = new Intent(Login.this, forgetpassword.class);
        startActivity(i);
    }


    private void login() {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        Call<LoginResponseRepos>  login= restServices.Login(usernamea.getText().toString(), passwordd.getText().toString());
        login.enqueue(new Callback<LoginResponseRepos>() {
            @Override
            public void onResponse(Call<LoginResponseRepos> call, Response<LoginResponseRepos> response) {
                if (response.isSuccessful()) {
                    hideDialog();
                    Toasty.success(getApplicationContext(), response.body().getMetaData().getMessage(), Toast.LENGTH_LONG).show();
//                    menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_TOKEN, response.body().getResponse().getToken());
                        editor.commit();
//                        // Memanggil main activity
                        Intent intent = new Intent(Login.this, indexActivity.class);
                        intent.putExtra(TAG_TOKEN, response.body().getResponse().getToken());
                        finish();
                        startActivity(intent);
                }
                else {
                    hideDialog();
                    _loginResponseReposMessage = _messageGson.fromJson(response.errorBody().charStream(), LoginResponseRepos.class);
                    Toasty.error(getApplicationContext(), _loginResponseReposMessage.getMetaData().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseRepos> call, Throwable t) {
                hideDialog();
                Toasty.error(getApplicationContext(), "Gagal Menghubungkan ke server...", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

