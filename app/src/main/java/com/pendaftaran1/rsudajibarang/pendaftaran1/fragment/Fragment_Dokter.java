package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.DokterAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mDokter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dokter extends Fragment {
    private ArrayList<mDokter> goodModelDokterArrayList;
    private DokterAdapter dokterAdapter;

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

    RecyclerView listView;
    TextView tvtempdokter;

    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,gettanggal,getcarabayar,getbpjs,getrujukan,getpoli,
    token;
    public Fragment_Dokter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dokter, container, false);
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
        getpoli = getArguments().getString(KEY_POLI);
        token = indexActivity.getToken();

        listView = (RecyclerView) view.findViewById(R.id.rcvdokter);
        tvtempdokter = (TextView) view.findViewById(R.id.tvtempdokter);
        RecyclerView.LayoutManager gridlay;
        gridlay = new GridLayoutManager(getActivity(), 1);
        listView.setLayoutManager(gridlay);
        getdata();

        Toasty.error(getActivity(), getpoli, Toast.LENGTH_LONG).show();
        return view;
    }

    private void getdata() {
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDQxNjc1NSwiZXhwIjoxNTg0NDM0NzU1fQ.RU4u4aV7rXKSRHvEl7Q0BW9F46qarkj5GK4XPhanvCM";
        Call dokter = restServices.ListDokter(getpoli,"Bearer "+token);

        dokter.enqueue(new Callback<String>() {
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

            goodModelDokterArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("dokterjaga");
            for (int i = 0; i < dataArray.length(); i++) {

                mDokter modelListView = new mDokter();
                JSONObject dataobj = dataArray.getJSONObject(i);

                modelListView.setKddokter(dataobj.getString("kddokter"));
                modelListView.setNAMADOKTER(dataobj.getString("NAMADOKTER"));

                goodModelDokterArrayList.add(modelListView);

            }

            dokterAdapter = new DokterAdapter(getContext(), goodModelDokterArrayList,new DokterAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(mDokter item, int posisi) {
                    Log.i("jalan poli", String.valueOf(posisi));
                    String poli;
                    poli = "poli anak";

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    // set title dialog
                    alertDialogBuilder.setTitle("ANDA MEMILIH "+posisi+"?");
                    tvtempdokter.setText(String.valueOf(posisi));
                    // set pesan dari dialog
                    alertDialogBuilder
                            .setMessage("Klik Ya untuk menyelesaikan pendaftaran!")
                            .setIcon(R.mipmap.ic_launcher)
                            .setCancelable(false)
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // jika tombol diklik, maka akan menutup activity ini
//                        getActivity().finish();
                                    daftarFragmentPoli();
                                    Fragment_Riwayat secondFragtry = new Fragment_Riwayat();
                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                    fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
//                                    nextFragment();
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
            listView.setAdapter(dokterAdapter);

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

//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDQxNjc1NSwiZXhwIjoxNTg0NDM0NzU1fQ.RU4u4aV7rXKSRHvEl7Q0BW9F46qarkj5GK4XPhanvCM";
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
        Call daftar = restServices.PendaftaranPasienLama(pasienbaru.toString(),getnorm,gettanggal,getpoli,
                tvtempdokter.getText().toString(), getjenispasien.toString(),gethubungan.toString(),
                getnotelp.toString(),getemail.toString(),getcarabayar, getbpjs,getrujukan,"Bearer "+token);
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
}
