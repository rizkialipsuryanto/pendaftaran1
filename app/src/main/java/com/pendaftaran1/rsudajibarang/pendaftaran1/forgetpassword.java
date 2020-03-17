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
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class forgetpassword extends AppCompatActivity {

    EditText emaile, nohpe;
    String codejson;
    Button sendforgot;
    private String url_insert = Base.URL + "auth/forgotpassword";
    int success;
    private static final String TAG_SUCCESS = "1";
    private static final String TAG_MESSAGE = "message";

    private static final String TAG = forgetpassword.class.getSimpleName();
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
                simpan();
            }
        });
    }

    private void forgetpassword() {
        String url;
        url = url_insert;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("OBJECT", "Response");
                Log.d("OBJECT","SUKSESSS BROOO");
//                Toast.makeText(forgetpassword.this, "SUKSES", Toast.LENGTH_LONG).show();
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(response);
                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("metaData");
                    String message = employee.getString("message");
                    Log.d("OBJECT",message);
                    Toast.makeText(forgetpassword.this, message, Toast.LENGTH_LONG).show();
                    // get employee name and salary
//                    name = employee.getString("name");
////                    salary = employee.getString("salary");
//                    // set employee name and salary in TextView's
////                    employeeName.setText("Name: "+name);
////                    employeeSalary.setText("Salary: "+salary);
//                    Log.d(TAG, "Response: " + codejson.toString());
//                    Toasty.success(getApplicationContext(), codejson.toString(), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.d(TAG, "Response: " + codejson.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(forgetpassword.this, "error", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("email", emaile.getText().toString());
                params.put("notelepon", nohpe.getText().toString());

                return params;
            }
        };

        queue.add(strReq);
//        AppController.getInstance().addToRequestQueue(strReq);

    }

    private void simpan() {
        String url;
        url = url_insert;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
                Toast.makeText(forgetpassword.this, "SUKSES", Toast.LENGTH_LONG).show();
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONObject rrrr = jObj.getJSONObject("response");
                    success = rrrr.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());
                        Toast.makeText(forgetpassword.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(forgetpassword.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(forgetpassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("email", emaile.getText().toString());
                params.put("notelepon", nohpe.getText().toString());

                return params;
            }

        };

        queue.add(strReq);
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
