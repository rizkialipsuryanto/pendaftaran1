package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.app.AppController;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mHubunganPasien;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mProvinsi;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;
import static com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity.pDialog;
import static com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity.volleyerror;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Pasien_Lama extends Fragment {

    EditText plnorma, plnotelephona,plemaila;
    Button btnpldaftara;

    private Spinner sppbhubunganpasien;

    private ArrayList<mHubunganPasien> goodModelHubunganPasienArrayList;

    List<String> valueHubunganPasien = new ArrayList<String>();

    public Fragment_Pasien_Lama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pasien_lama, container, false);
        plnorma = (EditText) view.findViewById(R.id.plnorm);
        plnotelephona = (EditText) view.findViewById(R.id.plnotelephon);
        plemaila = (EditText)view.findViewById(R.id.plemail);
        sppbhubunganpasien = (Spinner) view.findViewById(R.id.spinnerplhubungan);
        btnpldaftara = (Button)view.findViewById(R.id.btnpldaftar);

        btnpldaftara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String msg = edtMessageF.getText().toString();
                validasi();

            }
        });

        fetchJSONHubunganPasien();
        return view;
    }

    private void validasi(){
        if(plnorma.getText().toString().length()==0) {
            plnorma.setError("Masukkan No RM!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(plnotelephona.getText().toString().length()==0) {
            plnotelephona.setError("Masukkan No Telephon!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(plemaila.getText().toString().length()==0) {
            plemaila.setError("Masukkan Email!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }

        else{
            Toasty.success(getActivity(), "Pilih Tanggal dan Jenis Pelayanan", Toast.LENGTH_LONG).show();
            nextFragment();
        }
    }

    private void nextFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment newFrame = new Fragment_Dftronline();
        fm.beginTransaction().replace(R.id.flMain, newFrame).commit();
    }

    private void fetchJSONHubunganPasien(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call hubunganpasien = restServices.ListHubunganPasien();

        hubunganpasien.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONHubunganPasien(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("onFailure",t.getMessage().toString());
            }
        });
    }

    private void spinJSONHubunganPasien(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelHubunganPasienArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("hubungan");

            for (int i = 0; i < dataArray.length(); i++) {

                mHubunganPasien spinnerModel = new mHubunganPasien();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setNama(dataobj.getString("nama"));

                goodModelHubunganPasienArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelHubunganPasienArrayList.size(); i++){
                valueHubunganPasien.add(goodModelHubunganPasienArrayList.get(i).getNama().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueHubunganPasien);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbhubunganpasien.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
