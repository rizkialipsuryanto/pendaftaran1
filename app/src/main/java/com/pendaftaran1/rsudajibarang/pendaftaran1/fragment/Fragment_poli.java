package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_poli extends Fragment {
    private ArrayList<mPoliklinik> goodModelPoliklinikArrayList;
//    ArrayList<mPoliklinik>birdsLists;
    private PoliklinikAdapter adapter;
    List<String> valueKode = new ArrayList<String>();
    List<String> valueNama = new ArrayList<String>();
    RecyclerView listView;
    private PoliklinikAdapter poliklinikAdapter;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    public static String KEY_JENIS_PASIEN = "jenis_pasien";
    public static String KEY_HUBUNGAN = "hubungan";
    public static String KEY_NORM = "norm";
    public static String KEY_TGLLAHIR = "tgl_lahir";
    public static String KEY_NOTELP = "notelp";
    public static String KEY_EMAIL = "email";
    public static String KEY_TANGGAL = "tanggal";
    public static String KEY_CARABAYAR = "carabayar";
    public static String KEY_BPJS = "bpjs";
    public static String KEY_RUJUKAN = "rujukan";
    public static String KEY_POLI = "poliklinik";
    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,gettanggal,getcarabayar,getbpjs,getrujukan;
    public String polik;

    TextView tvtemppoli;

    public Fragment_poli() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poli, container, false);
        listView = (RecyclerView) view.findViewById(R.id.rcvpoli);
        tvtemppoli = (TextView) view.findViewById(R.id.tvtemppoli);
        RecyclerView.LayoutManager gridlay;
        gridlay = new GridLayoutManager(getActivity(), 1);
        listView.setLayoutManager(gridlay);

        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        gethubungan = getArguments().getString(KEY_HUBUNGAN);
        getnorm = getArguments().getString(KEY_NORM);
        gettgllahir = getArguments().getString(KEY_TGLLAHIR);
        getnotelp = getArguments().getString(KEY_NOTELP);
        getemail = getArguments().getString(KEY_EMAIL);
        gettanggal = getArguments().getString(KEY_TANGGAL);
        getcarabayar = getArguments().getString(KEY_CARABAYAR);
        getbpjs = getArguments().getString(KEY_BPJS);
        getrujukan = getArguments().getString(KEY_RUJUKAN);

        Toasty.error(getActivity(), gettanggal, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getcarabayar, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getbpjs, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getrujukan, Toast.LENGTH_LONG).show();
//        fetchJSONPoli();

//        goodModelPoliklinikArrayList = new ArrayList<>();
//        adapter = new PoliklinikAdapter(getContext(), goodModelPoliklinikArrayList,new PoliklinikAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(mPoliklinik item, int posisi) {
//                Log.e("OBJEK", String.valueOf(posisi));
//                daftarFragmentPoli();
//            }
//
//        });
        listView.setAdapter(adapter);

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

            poliklinikAdapter = new PoliklinikAdapter(getContext(), goodModelPoliklinikArrayList,new PoliklinikAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(mPoliklinik item, int posisi) {
                    Log.i("jalan poli", String.valueOf(posisi));
                    String poli;
                    poli = "poli anak";

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    // set title dialog
                    alertDialogBuilder.setTitle("ANDA MEMILIH "+posisi+"?");
                    tvtemppoli.setText(String.valueOf(posisi));
//                    polik = String.valueOf(posisi);
                    // set pesan dari dialog
                    alertDialogBuilder
                            .setMessage("Klik Ya untuk pilih dokter!")
                            .setIcon(R.mipmap.ic_launcher)
                            .setCancelable(false)
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // jika tombol diklik, maka akan menutup activity ini
//                        getActivity().finish();

                                    nextFragment();
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // jika tombol ini diklik, akan menutup dialog
                                    // dan tidak terjadi apa2
                                    dialog.cancel();
                                }
                            });

                    // membuat alert dialog dari builder
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // menampilkan alert dialog
                    alertDialog.show();
                }

            });
            listView.setAdapter(poliklinikAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void daftarFragmentPoli() {
        String pasienbaru = "0";
        String poliklinik = "2";
        String dokter = "1";
        String tanggal = "2020-03-17";
        String normnya = "1";
        String jenispasiennya = "SENDIRI";
        String hubungannya = "TIDAK ADA";
        String notelpnya = "4444";
        String emailnya = "aa@gmail.com";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDQxNjc1NSwiZXhwIjoxNTg0NDM0NzU1fQ.RU4u4aV7rXKSRHvEl7Q0BW9F46qarkj5GK4XPhanvCM";
        Log.d("OBJEK", "Jalan-----");
        Log.d("OBJEK", getnorm);
        Log.d("OBJEK", getjenispasien);
        Log.d("OBJEK", gethubungan);
        Log.d("OBJEK", gettgllahir);
        Log.d("OBJEK", getnotelp);
        Log.d("OBJEK", getemail);
        Log.d("OBJEK", token);
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call daftar = restServices.PendaftaranPasienLama(pasienbaru.toString(),getnorm,gettanggal,poliklinik.toString(),dokter.toString(),
                getjenispasien.toString(),gethubungan.toString(),getnotelp.toString(),getemail.toString(),getcarabayar,
                getbpjs,getrujukan,"Bearer "+token);
        daftar.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject jo = new JSONObject(response.body().toString());
                    Log.d("OBJEK","RESPON BODY : "+response.body().toString());
                } catch (Exception e) {
                    Toasty.error(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    Log.d("OBJEK","RESPON BODY : "+e.getMessage().toString());
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("OBJEK", t.getMessage());
            }
        });
    }

    private void nextFragment() {
        // TODO Auto-generated method stub

        Fragment_Dokter secondFragtry = new Fragment_Dokter();
        Bundle mBundle = new Bundle();
        mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
        mBundle.putString(KEY_HUBUNGAN, gethubungan);
        mBundle.putString(KEY_NORM, getnorm);
        mBundle.putString(KEY_TGLLAHIR, gettgllahir);
        mBundle.putString(KEY_NOTELP, getnotelp);
        mBundle.putString(KEY_EMAIL, getemail);
        mBundle.putString(KEY_TANGGAL, gettanggal);
        mBundle.putString(KEY_CARABAYAR, getcarabayar);
        mBundle.putString(KEY_BPJS, getbpjs);
        mBundle.putString(KEY_RUJUKAN, getrujukan);
        mBundle.putString(KEY_POLI, tvtemppoli.getText().toString());

        secondFragtry.setArguments(mBundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }

}