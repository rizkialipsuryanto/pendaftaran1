package com.pendaftaran1.rsudajibarang.pendaftaran1;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.DaftarFragment;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.HomeFragment;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.ProfilFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class indexActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    public static final String TAG_TOKEN = "token";
    public static String token;
    public static String name;
    private String url_insert = Base.URL + "auth/decode";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new DaftarFragment();
                    break;
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
                case R.id.navigation_notifications:
                    fragment = new ProfilFragment();
                    nama();
                    break;
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
        token = getIntent().getStringExtra(TAG_TOKEN);

        Toast.makeText(getApplicationContext(),token, Toast.LENGTH_LONG).show();

//        nama();
    }

    private void nama() {
        String url;

        url = url_insert+"?token="+getIntent().getStringExtra(TAG_TOKEN);
//
//        final String urlnya = url_insert + "?token=" + token;
//
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, urlnya, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("AMBILDATA",response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("AMBILDATA","erormas");
//                    }
//                });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("OBJECT", "Response");
                Log.d("OBJECT","SUKSESSS BROOO");
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(response);
                    // fetch JSONObject named employee
                    JSONObject employee = obj.getJSONObject("response");
                    String message = employee.getString("decoded");
                    Log.d("OBJECT",message);
                    Toast.makeText(indexActivity.this, message, Toast.LENGTH_LONG).show();
                    // get employee name and salary
                    name = employee.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(indexActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("token", getIntent().getStringExtra(TAG_TOKEN));

                return params;
            }
        };

        queue.add(strReq);

    }
    public static String getToken() {
        return token;
    }

    public static String getName() {
        return name;
    }
}
