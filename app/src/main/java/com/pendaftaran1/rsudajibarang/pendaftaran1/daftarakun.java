package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pendaftaran1.rsudajibarang.pendaftaran1.app.AppController;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class daftarakun extends AppCompatActivity {

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG = indexActivity.class.getSimpleName();
    private String url_insert = Base.REST_BASE_URL + "auth/registration";
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
        passworde = (EditText) findViewById(R.id.password);
        btn_lanjut = (Button) findViewById(R.id.daftarsimpan);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                simpan();
                Intent intent = new Intent(daftarakun.this, Login.class);
                finish();
                startActivity(intent);


            }
        });
    }

    private void simpan() {
        String url;
        url = url_insert;

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

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
                params.put("password", passworde.getText().toString().trim());

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
