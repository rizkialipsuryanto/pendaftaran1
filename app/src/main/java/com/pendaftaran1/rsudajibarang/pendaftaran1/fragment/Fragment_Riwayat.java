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

//        riwayatAdapter = new RiwayatAdapter(getActivity(), goodModelRiwayatArrayList);
//        listView.setAdapter(riwayatAdapter);
//
//                riwayatAdapter = new RiwayatAdapter(getContext(), goodModelRiwayatArrayList,new RiwayatAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(mRiwayat item, int posisi) {
//                    Log.i("TAGI", String.valueOf(posisi));
//
//                }
//
//            });
        getdata();
        return view;
    }

    private void getdata() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDIwMTQ4NywiZXhwIjoxNTg0MjE5NDg3fQ.tY4W5EmVixWta2DgK7CKoIqRu5EYFoW1M3mDScmaS5k";

        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call riwayat = restServices.ListRiwayat("Bearer "+token);

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

//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
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

//            listView.setAdapter(new RiwayatAdapter(items, new RiwayatAdapter.OnItemClickListener() {
//                @Override public void onItemClick(mRiwayat item) {
//                    Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
//                }
//            }));

//            riwayatAdapter = new RiwayatAdapter(getContext(), goodModelRiwayatArrayList,new RiwayatAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(mRiwayat item, int posisi) {
//                    Log.i("TAGI", String.valueOf(posisi));
//
//                }
//
//            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
