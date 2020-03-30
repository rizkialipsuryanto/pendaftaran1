package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Pendaftaran;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.PoliklinikAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;
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
    public static ProgressDialog pDialog;
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
    public static String KEY_CARABAYARNAMA = "carabayarnama";
    public static String KEY_BPJS = "bpjs";
    public static String KEY_RUJUKAN = "rujukan";
    public static String KEY_POLI = "poliklinik";
    public static String KEY_POLINAMA = "polikliniknama";

    public static String KEY_NAMA = "nama";
    public static String KEY_NIK = "nik";
    public static String KEY_JENISKELAMIN = "jenis_kelamin";
    public static String KEY_TEMPATLAHIR = "tempat_lahir";
    public static String KEY_ALAMATSESUAIKTP = "alamat_sesuai_ktp";
    public static String KEY_PROVINSI = "provinsi";
    public static String KEY_KABUPATEN = "kabupaten";
    public static String KEY_KECAMATAN = "kecamatan";
    public static String KEY_KELURAHAN = "kelurahan";
    public static String KEY_NAMAAYAH = "nama_ayah";
    public static String KEY_NAMAIBU = "nama_ibu";
    public static String KEY_SUAMI = "nama_suami";
    public static String KEY_ISTRI = "nama_istri";
    public static String KEY_AGAMA = "agama";
    public static String KEY_PENDIDIKAN = "pendidikan";
    public static String KEY_PEKERJAAN = "pekerjaan";
    public static String KEY_STATUSKAWIN = "status_kawin";
    public static String KEY_KEWARGANEGARAAN = "kewarganegaraan";
    public static String KEY_SUKU = "suku";
    public static String KEY_BAHASADAERAH = "bahasa_daerah";
    public static String KEY_TITLE = "title";
    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,gettanggal,getcarabayar,getcarabayarnama,getbpjs,getrujukan;
    String getnama,getnik,getjeniskelamin,gettempatlahir,getalamatsesuaiktp,getprovinsi,getkabupaten,getkecamatan,getkelurahan, getnamaayah,getnamaibu,getsuami,getistri,getagama,getpendidikan,getpekerjaan,getstatuskawin,getkewarganegaraan,getsuku,getbahasa,gettitle;
    public String polik;

    TextView tvtemppoli,tvtemppolinama;

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
        tvtemppolinama = (TextView) view.findViewById(R.id.tvtemppolinama);
        RecyclerView.LayoutManager gridlay;
        gridlay = new GridLayoutManager(getActivity(), 1);
        listView.setLayoutManager(gridlay);

        getfrombefore();

        listView.setAdapter(adapter);

        Toasty.error(getActivity(), indexActivity.getIdUser(), Toast.LENGTH_LONG).show();

        getdata();

        return view;
    }

    private void getdata() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();
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
                        hideDialog();
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("onFailure",t.getMessage().toString());
                hideDialog();
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
                    alertDialogBuilder.setTitle("ANDA MEMILIH "+item.getNama()+"?");
                    tvtemppoli.setText(String.valueOf(posisi));
                    tvtemppolinama.setText(item.getNama());
//                    polik = String.valueOf(posisi);
                    // set pesan dari dialog
                    alertDialogBuilder
                            .setMessage("Klik Ya untuk pilih dokter!")
                            .setIcon(R.mipmap.ic_launcher)
                            .setCancelable(false)
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // jika tombol diklik, maka akan menutup activity ini
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

    private void nextFragment() {
//        pDialog = new ProgressDialog(getContext());
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading ...");
//        showDialog();
        // TODO Auto-generated method stub
        postnextfragment();
//        hideDialog();
    }

    private void getfrombefore(){
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();
        String jenispasiennya;
        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        jenispasiennya = getjenispasien;
        Log.i("DATA", getjenispasien);
        if(jenispasiennya == "0") {
            getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
            gethubungan = getArguments().getString(KEY_HUBUNGAN);
            getnorm = getArguments().getString(KEY_NORM);
            gettgllahir = getArguments().getString(KEY_TGLLAHIR);
            getnotelp = getArguments().getString(KEY_NOTELP);
            getemail = getArguments().getString(KEY_EMAIL);
            gettanggal = getArguments().getString(KEY_TANGGAL);
            getcarabayar = getArguments().getString(KEY_CARABAYAR);
            getcarabayarnama = getArguments().getString(KEY_CARABAYARNAMA);
            getbpjs = getArguments().getString(KEY_BPJS);
            getrujukan = getArguments().getString(KEY_RUJUKAN);
            getnama = getArguments().getString(KEY_NAMA);
            getnik = getArguments().getString(KEY_NIK);
            getjeniskelamin = getArguments().getString(KEY_JENISKELAMIN);
            gettempatlahir = getArguments().getString(KEY_TEMPATLAHIR);
            getalamatsesuaiktp = getArguments().getString(KEY_ALAMATSESUAIKTP);
            getprovinsi = getArguments().getString(KEY_PROVINSI);
            getkabupaten = getArguments().getString(KEY_KABUPATEN);
            getkecamatan = getArguments().getString(KEY_KECAMATAN);
            getkelurahan = getArguments().getString(KEY_KELURAHAN);
            getnamaayah = getArguments().getString(KEY_NAMAAYAH);
            getnamaibu = getArguments().getString(KEY_NAMAIBU);
            getsuami = getArguments().getString(KEY_SUAMI);
            getistri = getArguments().getString(KEY_ISTRI);
            getnotelp = getArguments().getString(KEY_NOTELP);
            getagama = getArguments().getString(KEY_AGAMA);
            getpendidikan = getArguments().getString(KEY_PENDIDIKAN);
            getpekerjaan = getArguments().getString(KEY_PEKERJAAN);
            getstatuskawin = getArguments().getString(KEY_STATUSKAWIN);
            getkewarganegaraan = getArguments().getString(KEY_KEWARGANEGARAAN);
            getsuku = getArguments().getString(KEY_SUKU);
            getbahasa = getArguments().getString(KEY_BAHASADAERAH);
            hideDialog();
        }
        if(jenispasiennya == "1")
        {
            getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
            gethubungan = getArguments().getString(KEY_HUBUNGAN);
            gettitle = getArguments().getString(KEY_TITLE);
            Log.i("DATA", gettitle);
            getnama = getArguments().getString(KEY_NAMA);
            Log.i("DATA", getnama);
            getnik = getArguments().getString(KEY_NIK);
            Log.i("DATA", getnik);
            getjeniskelamin = getArguments().getString(KEY_JENISKELAMIN);
            Log.i("DATA", getjeniskelamin);
            gettempatlahir = getArguments().getString(KEY_TEMPATLAHIR);
            Log.i("DATA", gettempatlahir);
            getalamatsesuaiktp = getArguments().getString(KEY_ALAMATSESUAIKTP);
            Log.i("DATA", getalamatsesuaiktp);
            getprovinsi = getArguments().getString(KEY_PROVINSI);
            Log.i("DATA", getprovinsi);
            getkabupaten = getArguments().getString(KEY_KABUPATEN);
            Log.i("DATA", getkabupaten);
            getkecamatan = getArguments().getString(KEY_KECAMATAN);
            Log.i("DATA", getkecamatan);
            getkelurahan = getArguments().getString(KEY_KELURAHAN);
            Log.i("DATA", getkelurahan);
            getnamaayah = getArguments().getString(KEY_NAMAAYAH);
            Log.i("DATA", getnamaayah);
            getnamaibu = getArguments().getString(KEY_NAMAIBU);
            Log.i("DATA", getnamaibu);
            getsuami = getArguments().getString(KEY_SUAMI);
            Log.i("DATA", getsuami);
            getistri = getArguments().getString(KEY_ISTRI);
            Log.i("DATA", getistri);
            getnotelp = getArguments().getString(KEY_NOTELP);
            Log.i("DATA", getnotelp);
            getagama = getArguments().getString(KEY_AGAMA);
            Log.i("DATA", getagama);
            getpendidikan = getArguments().getString(KEY_PENDIDIKAN);
            Log.i("DATA", getpendidikan);
            getpekerjaan = getArguments().getString(KEY_PEKERJAAN);
            Log.i("DATA", getpekerjaan);
            getstatuskawin = getArguments().getString(KEY_STATUSKAWIN);
            Log.i("DATA", getstatuskawin);
            getsuku = getArguments().getString(KEY_SUKU);
            Log.i("DATA", getsuku);
            getbahasa = getArguments().getString(KEY_BAHASADAERAH);
            Log.i("DATA", getbahasa);
            gettanggal = getArguments().getString(KEY_TANGGAL);
            getcarabayar = getArguments().getString(KEY_CARABAYAR);
            getcarabayarnama = getArguments().getString(KEY_CARABAYARNAMA);
            getbpjs = getArguments().getString(KEY_BPJS);
            getrujukan = getArguments().getString(KEY_RUJUKAN);
            hideDialog();
        }
    }

    private void postnextfragment(){
        getfrombefore();
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
        mBundle.putString(KEY_CARABAYARNAMA, getcarabayarnama);
        mBundle.putString(KEY_BPJS, getbpjs);
        mBundle.putString(KEY_RUJUKAN, getrujukan);
        mBundle.putString(KEY_POLI, tvtemppoli.getText().toString());
        mBundle.putString(KEY_POLINAMA, tvtemppolinama.getText().toString());
        mBundle.putString(KEY_TITLE, gettitle);
        mBundle.putString(KEY_NAMA, getnama);
        mBundle.putString(KEY_NIK, getnik);
        mBundle.putString(KEY_JENISKELAMIN, getjeniskelamin);
        mBundle.putString(KEY_TEMPATLAHIR, gettempatlahir);
        mBundle.putString(KEY_ALAMATSESUAIKTP, getalamatsesuaiktp);
        mBundle.putString(KEY_PROVINSI, getprovinsi);
        mBundle.putString(KEY_KABUPATEN, getkabupaten);
        mBundle.putString(KEY_KECAMATAN, getkecamatan);
        mBundle.putString(KEY_KELURAHAN, getkelurahan);
        mBundle.putString(KEY_NAMAAYAH, getnamaayah);
        mBundle.putString(KEY_NAMAIBU, getnamaibu);
        mBundle.putString(KEY_SUAMI, getsuami);
        mBundle.putString(KEY_ISTRI, getistri);
        mBundle.putString(KEY_NOTELP, getnotelp);
        mBundle.putString(KEY_AGAMA, getagama);
        mBundle.putString(KEY_PENDIDIKAN, getpendidikan);
        mBundle.putString(KEY_PEKERJAAN, getpekerjaan);
        mBundle.putString(KEY_STATUSKAWIN, getstatuskawin);
        mBundle.putString(KEY_KEWARGANEGARAAN, getkewarganegaraan);
        mBundle.putString(KEY_SUKU, getsuku);
        mBundle.putString(KEY_BAHASADAERAH, getbahasa);

        secondFragtry.setArguments(mBundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
