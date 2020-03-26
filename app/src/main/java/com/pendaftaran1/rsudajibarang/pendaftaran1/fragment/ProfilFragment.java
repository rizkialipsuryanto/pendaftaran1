package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {


    String teksbarcode,email, namaa;
    private String url_insert = Base.URL + "auth/decode";
    TextView nama, lastnamee, emaill;
    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        nama = (TextView) view.findViewById(R.id.tvprofilnama);
//        lastnamee = (EditText) view.findViewById(R.id.profilLastname);
//        emaill = (EditText) view.findViewById(R.id.profilEmail);

        LoadProfil();
        view.findViewById(R.id.lllogout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Logout();
            }
        });
        return view;
    }

    private void LoadProfil() {
        String url;
        url = url_insert+"?token="+ indexActivity.getToken();

        RequestQueue queue = Volley.newRequestQueue(getActivity());

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
                    String message = employee.getString("email");
                    Log.d("OBJECT",message);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    // get employee name and salary
                    email = employee.getString("email");
                    namaa = employee.getString("firstname")+" "+employee.getString("lastname");
//                    lastname = employee.getString("lastname");

                    //ini untuk settext
//                    emaill.setText(email);
                    nama.setText(namaa);
//                    lastnamee.setText(lastname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update

                params.put("token", indexActivity.getToken());

                return params;
            }
        };

        queue.add(strReq);

    }

    private void Logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Log Out?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Apakah anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Batalkan",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

}
