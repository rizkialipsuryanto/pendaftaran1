package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pendaftaran1.rsudajibarang.pendaftaran1.app.AppController;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;

import java.util.HashMap;
import java.util.Map;

public class forgetpassword extends AppCompatActivity {

    private static final String TAG = daftarakun.class.getSimpleName();
    private String url_insert = Base.URL + "auth/forgotpassword";
    EditText emaile, nohpe;
    Button sendforgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        emaile = (EditText) findViewById(R.id.fpemail);
        nohpe = (EditText) findViewById(R.id.fpnohp);
        sendforgot = (Button) findViewById(R.id.btnsimpanlupapassword);

        sendforgot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                forgetpassword();
            }
        });
    }

    private void forgetpassword() {
        String url;
        url = url_insert;
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
                Toast.makeText(forgetpassword.this, "SUKSES", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(forgetpassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("email", emaile.getText().toString());
                params.put("nohp", nohpe.getText().toString());

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq);
//        queue.add(strReq);
    }
}
