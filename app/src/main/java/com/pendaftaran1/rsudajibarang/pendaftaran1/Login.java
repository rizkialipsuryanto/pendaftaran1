

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

import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {


    String token;
    TextView usernamea, passwordd;

    public final static String TAG_TOKEN = "token";
    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    RestServices restServices = ServiceGenerator.build().create(RestServices.class);

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

        Call login = restServices.Login(usernamea.getText().toString(), passwordd.getText().toString());
        login.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject jo = new JSONObject(response.body().toString());
                    Log.d("OBJEK","RESPON BODY : "+response.body().toString());

                    // CASTING JSON OBJECT
                    JSONObject rrrr = jo.getJSONObject("response");
//<<<<<<< HEAD
//                    String b = rrrr.getString("token");
//                    Log.d("OBJEK", b);
//                    if (b.length() > 0) {
//                        Toast.makeText(getApplicationContext(), "OKOKOKOKO", Toast.LENGTH_LONG).show();
////                        Toast.success(getApplicationContext(), "OKOKOKOKO", Toast.LENGTH_LONG).show();
//                        // menyimpan login ke session
//=======
                    JSONObject metaData = jo.getJSONObject("metaData");
                    String code = metaData.getString("code");
                    String message = metaData.getString("message");

                    // MENDAPATKAN TOKEN
                    String token = rrrr.getString("token");
                    if (Integer.parseInt(code) == 200) {
                        Toasty.success(getApplicationContext(), message, Toast.LENGTH_LONG).show();

//                       menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_TOKEN, token);
                        editor.commit();
//                        // Memanggil main activity
                        Intent intent = new Intent(Login.this, indexActivity.class);
                        intent.putExtra(TAG_TOKEN, token);
                        finish();
                        startActivity(intent);

                    } else {
                        Toasty.error(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Toasty.error(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("OBJEK", t.getMessage());
            }
        });
    }
}

