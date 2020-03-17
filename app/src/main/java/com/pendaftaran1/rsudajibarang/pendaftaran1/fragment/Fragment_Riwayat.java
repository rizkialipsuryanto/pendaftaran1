package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.RiwayatAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mRiwayat;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Riwayat extends Fragment {
    private ArrayList<mRiwayat> goodModelRiwayatArrayList;
    private RiwayatAdapter riwayatAdapter;
    RecyclerView listView;

    public Fragment_Riwayat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__riwayat, container, false);
        listView=(RecyclerView) view.findViewById(R.id.rcvriwayat);
        RecyclerView.LayoutManager gridlay;
        gridlay = new GridLayoutManager(getActivity(), 1);
        listView.setLayoutManager(gridlay);
        getdata();
        return view;
    }

    private void getdata() {
        String id = "2";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDQxMTg3OCwiZXhwIjoxNTg0NDI5ODc4fQ.afATTKIba_ekReGFSUD3_NE9fBzRof2yVLeeDlOeB7I";

        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call riwayat = restServices.ListRiwayat(id, "Bearer "+token);

        riwayat.enqueue(new Callback() {
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
            public void onFailure(Call call, Throwable t) {
                Log.i("onFailure",t.getMessage().toString());
            }
        });
    }

    private void writeListView(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelRiwayatArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("registrations");
            for (int i = 0; i < dataArray.length(); i++) {

                mRiwayat modelListView = new mRiwayat();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelListView.setId(dataobj.getString("id"));
                modelListView.setTanggal(dataobj.getString("tanggal"));
                modelListView.setPoliklinik(dataobj.getString("poliklinik"));

                goodModelRiwayatArrayList.add(modelListView);

            }

            riwayatAdapter = new RiwayatAdapter(getActivity(), goodModelRiwayatArrayList);
            listView.setAdapter(riwayatAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
