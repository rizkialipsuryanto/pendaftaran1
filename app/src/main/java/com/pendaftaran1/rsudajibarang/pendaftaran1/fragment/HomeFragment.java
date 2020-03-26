package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity.TAG_TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public Button barcode;
    public ImageView image;
    public TextView daftar;

    String teksbarcode,name;
    private String url_insert = Base.URL + "auth/decode";

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        daftar = (TextView) view.findViewById(R.id.tvpendaftaranpoli);
//        barcode = (Button)view.findViewById(R.id.barcodeteks);
//        image = (ImageView)view.findViewById(R.id.imageview);
//        name = indexActivity.getName();
        teksbarcode = indexActivity.getToken();


        view.findViewById(R.id.tvpendaftaranpoli).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Daftar();
            }
        });
//        barcode.setText(name.toString());
//        barcode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String greeting = teksbarcode;
////                teksbarcode = teks.getText().toString();
//                nama();
//                try
//                {
//                    BitMatrix bitMatrix = multiFormatWriter.encode(teksbarcode, BarcodeFormat.QR_CODE, 300,300);
//                    BarcodeEncoder encoder = new BarcodeEncoder();
//                    Bitmap bitmap = encoder.createBitmap(bitMatrix);
//                    image.setImageBitmap(bitmap);
//
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        return view;
    }

    private void nama() {
        String url;
        url = url_insert+"?token="+indexActivity.getToken();

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
                    name = employee.getString("email");
                    barcode.setText(name);
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

    private void Daftar() {
        // TODO Auto-generated method stub
        DaftarFragment secondFragtry = new DaftarFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }

//    public void onClick(View v) {
//        String greeting = "Hello world!";
////        teksbarcode = aaaaa;
//        try                {
//            BitMatrix bitMatrix = multiFormatWriter.encode(greeting, BarcodeFormat.QR_CODE, 300,300);
//            BarcodeEncoder encoder = new BarcodeEncoder();
//            Bitmap bitmap = encoder.createBitmap(bitMatrix);
//            image.setImageBitmap(bitmap);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }

}
