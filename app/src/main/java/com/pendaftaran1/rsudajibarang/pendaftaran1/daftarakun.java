package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pendaftaran1.rsudajibarang.pendaftaran1.app.AppController;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;

public class daftarakun extends AppCompatActivity {

    private static final String TAG_SUCCESS = "1";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG = daftarakun.class.getSimpleName();
    private String url_insert = Base.URL + "auth/registration";
    int success;
    String firstnames, lastnames, emails,passwords;
    EditText firstnamee, lastnamee,emaile, passworde;
    Button btn_lanjut;

    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarakun);

        firstnamee = (EditText) findViewById(R.id.firstname);
        lastnamee = (EditText) findViewById(R.id.lastname);
        emaile = (EditText) findViewById(R.id.email);
        passworde = (EditText) findViewById(R.id.passworddaftar);
        btn_lanjut = (Button) findViewById(R.id.daftarsimpan);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                simpan();
//                saveRegistration();
//                Intent intent = new Intent(daftarakun.this, Login.class);
//                finish();
//                startActivity(intent);


            }
        });
    }

    private void simpan() {
        String url;
        url = url_insert;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
                Toast.makeText(daftarakun.this, "SUKSES", Toast.LENGTH_LONG).show();
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONObject rrrr = jObj.getJSONObject("response");
                    success = rrrr.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());
                        Toast.makeText(daftarakun.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(daftarakun.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(daftarakun.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("firstname", firstnamee.getText().toString());
                params.put("lastname", lastnamee.getText().toString());
                params.put("email", emaile.getText().toString());
                params.put("password", passworde.getText().toString());

                return params;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }

        };

        queue.add(strReq);
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    private void saveRegistration(){

        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call registrationNewUser = restServices.RegistrationNewUser(  firstnamee.getText().toString(),
                                                        lastnamee.getText().toString(),
                                                        emaile.getText().toString(),
                                                        passworde.getText().toString());

        registrationNewUser.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                int status = response.code();
                Log.d("DAFTARAKUN/RESPON_CODE", String.valueOf(status));
                Toasty.success(getApplicationContext(), "OKEOKEOKOEK", Toast.LENGTH_LONG).show();

                    if(response.isSuccessful()) {
                        Toasty.success(getApplicationContext(), "SUKSES", Toast.LENGTH_LONG).show();
                    }else{
                        Toasty.error(getApplicationContext(), "GAGAL REGISTRASI. SILAKAN ULANGI", Toast.LENGTH_LONG).show();
                    }
//                if(response.code()== 200){

//                }else{
//                    Toasty.error(getApplicationContext(), "GAGAL REGISTRASI", Toast.LENGTH_LONG).show();
//                }
//                response.isSuccessful()

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toasty.success(getApplicationContext(), "GAGAL", Toast.LENGTH_LONG).show();
            }
        });
    }
}
