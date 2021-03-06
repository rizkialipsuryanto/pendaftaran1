package com.pendaftaran1.rsudajibarang.pendaftaran1;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
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
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Bantuan.Fragment_Bantuan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Riwayat.Fragment_Riwayat;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.HomeFragment;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Profil.ProfilFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class indexActivity extends AppCompatActivity {

    ConnectivityManager conMgr;
    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    public static final String TAG_TOKEN = "token";
    public static String token;
    public static String iduser;
    private String url_insert = Base.URL + "auth/decode";
    public static ProgressDialog pDialog;
    public static String Errmsg;

    JSONArray JsonArrayProvinsi = null;
    String tag_json_obj = "json_obj_req";
    ArrayAdapter<String> spinnerAdapterProvinsi;
    List<String> valueidprovinsi = new ArrayList<String>();
    List<String> valuenamaprovinsi = new ArrayList<String>();

    private String url = Base.URL + "references/provinsi";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new Fragment_Riwayat();
                    break;
                case R.id.navigation_bantuan:
                    fragment = new Fragment_Bantuan();
                    break;
                case R.id.navigation_notifications:
                    fragment = new ProfilFragment();
//                    nama();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.flMain, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.flMain, new HomeFragment()).commit();
        token = getIntent().getStringExtra(TAG_TOKEN);
        getiduser();

        Toast.makeText(getApplicationContext(),token, Toast.LENGTH_LONG).show();
    }

    private void getiduser() {
        String url;
        url = url_insert+"?token="+getIntent().getStringExtra(TAG_TOKEN);

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
//                    String message = employee.getString("decoded");
//                    Log.d("OBJECT",message);
//                    Toast.makeText(indexActivity.this, message, Toast.LENGTH_LONG).show();
                    // get employee name and salary
                    iduser = employee.getString("id");
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

    public static String getIdUser() {
        return iduser;
    }

}
