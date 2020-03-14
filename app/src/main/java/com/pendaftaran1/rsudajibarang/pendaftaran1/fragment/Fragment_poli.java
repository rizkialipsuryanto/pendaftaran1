package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.PoliklinikAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPoliklinik;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_poli extends Fragment {
    private ArrayList<mPoliklinik> goodModelPoliklinikArrayList;
    ArrayList<mPoliklinik>birdsLists;
    PoliklinikAdapter adapter;
    List<String> valueKode = new ArrayList<String>();
    List<String> valueNama = new ArrayList<String>();
    ListView listView;
    private PoliklinikAdapter poliklinikAdapter;

    public Fragment_poli() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poli, container, false);
        listView=(ListView) view.findViewById(R.id.rcvpoli);
//        fetchJSONPoli();
        getdata();
        return view;
    }

    private void getdata() {
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
//        Call poli = restServices.ListPoli();
        Call poli = restServices.ListPolik();

        poli.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("TAGI", "jalannnn");
                Log.i("TAGI", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeListView(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("onFailure",t.getMessage().toString());
            }
        });
    }

    private void writeListView(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelPoliklinikArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("poliklinik");
            for (int i = 0; i < dataArray.length(); i++) {

                mPoliklinik modelListView = new mPoliklinik();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelListView.setKode(dataobj.getString("kode"));
                modelListView.setNama(dataobj.getString("nama"));

                goodModelPoliklinikArrayList.add(modelListView);

            }

            poliklinikAdapter = new PoliklinikAdapter(getActivity(), goodModelPoliklinikArrayList);
            listView.setAdapter(poliklinikAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
