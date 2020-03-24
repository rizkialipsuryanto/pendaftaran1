package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.EditText;
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
    public static ProgressDialog pDialog;
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
    public static String KEY_CARABAYARNAMA = "carabayarnama";
    public static String KEY_BPJS = "bpjs";
    public static String KEY_RUJUKAN = "rujukan";
    public static String KEY_POLI = "poliklinik";
    public static String KEY_POLINAMA = "polikliniknama";
    public static String KEY_DOKTER = "dokter";
    public static String KEY_DOKTERNAMA = "dokternama";
    public static String KEY_BOOKINGCODE = "bookingcode";

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

    RecyclerView listView;
    TextView tvtempdokter,tvtempdokternama;
    View dialogView;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;

    String iduser;
    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,gettanggal,getcarabayar,getbpjs,getrujukan,getpoli,
    token,getcarabayarnama,getpolinama;
    String getnama,getnik,getjeniskelamin,gettempatlahir,getalamatsesuaiktp,getprovinsi,getkabupaten,getkecamatan,getkelurahan, getnamaayah,getnamaibu,getsuami,getistri,getagama,getpendidikan,getpekerjaan,getstatuskawin,getkewarganegaraan,getsuku,getbahasa,gettitle;
    EditText txtalertjenispasien, txtalertnorm, txtalerttgllahir, txtalertnotelp,txtalertemail,txtalerttanggal,txtalertcarabayar,
    txtalertnobpjs,txtalertnorujukan,txtalertpoli,txtalertdokter;
    String jenis_pasien, norm, tgllahir, notelp, email, tanggal, carabayar,bpjs, rujukan, poli, dokter;
    String bookingcode,jenisnya;
    public Fragment_Dokter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dokter, container, false);
        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        getpoli = getArguments().getString(KEY_POLI);
        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        jenisnya = getjenispasien;
        Log.i("DATA", getjenispasien);
        getfrombefore();

        token = indexActivity.getToken();
        Toasty.error(getActivity(), token, Toast.LENGTH_LONG).show();

        listView = (RecyclerView) view.findViewById(R.id.rcvdokter);
        tvtempdokter = (TextView) view.findViewById(R.id.tvtempdokter);
        tvtempdokternama = (TextView) view.findViewById(R.id.tvtempdokternama);
        RecyclerView.LayoutManager gridlay;
        gridlay = new GridLayoutManager(getActivity(), 1);
        listView.setLayoutManager(gridlay);
        getdata();

//        Toasty.error(getActivity(), getpoli, Toast.LENGTH_LONG).show();
        iduser = indexActivity.getIdUser();
        Toasty.error(getActivity(), iduser, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), indexActivity.getIdUser(), Toast.LENGTH_LONG).show();
        return view;
    }

    private void getdata() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
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

//        getfrombefore();
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
                    getfrombefore();
                    Log.i("jalan poli", String.valueOf(posisi));

                    tvtempdokter.setText(String.valueOf(posisi));
                    tvtempdokternama.setText(item.getNAMADOKTER());
                    dialog = new AlertDialog.Builder(getActivity());
                    inflater = getLayoutInflater();
                    dialogView = inflater.inflate(R.layout.form_alertdialog, null);
                    dialog.setView(dialogView);
                    dialog.setCancelable(true);
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setTitle("Data Pendaftaran");

                    txtalertjenispasien    = (EditText) dialogView.findViewById(R.id.txtalertjenispasien);
                    txtalertnorm    = (EditText) dialogView.findViewById(R.id.txtalertnorm);
                    txtalerttgllahir  = (EditText) dialogView.findViewById(R.id.txtalerttgllahir);
                    txtalertnotelp = (EditText) dialogView.findViewById(R.id.txtalertnotelp);
                    txtalertemail    = (EditText) dialogView.findViewById(R.id.txtalertemail);
                    txtalerttanggal    = (EditText) dialogView.findViewById(R.id.txtalerttanggal);
                    txtalertcarabayar  = (EditText) dialogView.findViewById(R.id.txtalertcarabayar);
                    txtalertnobpjs = (EditText) dialogView.findViewById(R.id.txtalertnobpjs);
                    txtalertnorujukan    = (EditText) dialogView.findViewById(R.id.txtalertnorujukan);
                    txtalertpoli  = (EditText) dialogView.findViewById(R.id.txtalertpoli);
                    txtalertdokter  = (EditText) dialogView.findViewById(R.id.txtalertdokter);

                    txtalertjenispasien.setText(getjenispasien);
                    txtalertnorm.setText(getnorm);
                    txtalerttgllahir.setText(gettgllahir);
                    txtalertnotelp.setText(getnotelp);
                    txtalertemail.setText(getemail);
                    txtalerttanggal.setText(gettanggal);
                    txtalertcarabayar.setText(getcarabayarnama);
                    txtalertnobpjs.setText(getbpjs);
                    txtalertnorujukan.setText(getrujukan);
                    txtalertpoli.setText(getpolinama);
                    txtalertdokter.setText(item.getNAMADOKTER());
                    dialog.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getfrombefore();
                            daftarFragmentDokter();

                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            });
            listView.setAdapter(dokterAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void daftarFragmentDokter() {
//        getfrombefore();
//        pDialog = new ProgressDialog(getContext());
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading ...");
//        showDialog();
        String pasienbaru = getjenispasien;
        Call daftar;
        Log.d("OBJEK", "Jalan-----");
        Log.d("OBJEK", token);
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        if (pasienbaru == "0"){
            Log.d("OBJEK", "Jalan lama");
            daftar = restServices.PendaftaranPasienLama(getjenispasien,getnorm,gettanggal,getpoli,
                    tvtempdokter.getText().toString()
                    ,gethubungan,
                    getnotelp,getemail,getcarabayar, getbpjs,getrujukan,iduser.toString(),"Bearer "+token);
        }
        else{
            Log.d("OBJEK", "Jalan baru");
            daftar = restServices.PendaftaranPasienBaru(getjenispasien,gettanggal,getpoli,
                    tvtempdokter.getText().toString(),gethubungan, getnotelp
                    ,getcarabayar, getbpjs,getrujukan,iduser.toString(),getnama,getnik,getjeniskelamin,gettempatlahir,
                    getalamatsesuaiktp,getprovinsi,getkabupaten,getkecamatan,getkelurahan,getnamaayah,getnamaibu,getsuami,
                    getistri,getagama,getpendidikan,getpekerjaan,getstatuskawin,getkewarganegaraan,getsuku,
                    getbahasa,gettitle,"Bearer "+token);
        }

        daftar.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject jo = new JSONObject(response.body().toString());
                    Log.d("OBJEK","RESPON BODY : "+response.body().toString());
                    // CASTING JSON OBJECT
                    JSONObject rrrr = jo.getJSONObject("response");

                    JSONObject metaData = jo.getJSONObject("metaData");
                    String code = metaData.getString("code");
                    bookingcode = rrrr.getString("bookingcode");
                    Log.d("OBJEK","RESPON BODY : "+bookingcode);
//                    hideDialog();

                    Fragment_Daftar_Selesai secondFragtry = new Fragment_Daftar_Selesai();
                    Bundle mBundle = new Bundle();
                    mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
                    mBundle.putString(KEY_HUBUNGAN, gethubungan);
                    mBundle.putString(KEY_NORM, getnorm);
                    mBundle.putString(KEY_TGLLAHIR, gettgllahir);
                    mBundle.putString(KEY_NOTELP, getnotelp);
                    mBundle.putString(KEY_EMAIL, getemail);
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
                    mBundle.putString(KEY_TANGGAL, gettanggal);
                    mBundle.putString(KEY_CARABAYAR, getcarabayar);
                    mBundle.putString(KEY_CARABAYARNAMA, getcarabayarnama);
                    mBundle.putString(KEY_BPJS, getbpjs);
                    mBundle.putString(KEY_RUJUKAN, getrujukan);
                    mBundle.putString(KEY_POLI, getpoli);
                    mBundle.putString(KEY_POLINAMA, getpolinama);
                    mBundle.putString(KEY_DOKTER, tvtempdokter.getText().toString());
                    mBundle.putString(KEY_DOKTERNAMA, tvtempdokternama.getText().toString());
                    mBundle.putString(KEY_BOOKINGCODE, bookingcode);
                    Log.d("OBJEK","RESPON BODY : "+bookingcode);

                    secondFragtry.setArguments(mBundle);
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
                } catch (Exception e) {
                    Toasty.error(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    Log.d("OBJEK","RESPON BODY : "+e.getMessage().toString());
//                    hideDialog();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Log.d("OBJEK", t.getMessage());
//                hideDialog();
            }
        });
    }

    private void getfrombefore(){
//        pDialog = new ProgressDialog(getContext());
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading ...");
//        showDialog();
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
            gettitle = getArguments().getString(KEY_TITLE);
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
            getpoli = getArguments().getString(KEY_POLI);
            getpolinama = getArguments().getString(KEY_POLINAMA);
//            hideDialog();
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
//            getkewarganegaraan = getArguments().getString(KEY_KEWARGANEGARAAN);
//            Log.i("DATA", getkewarganegaraan);
            getsuku = getArguments().getString(KEY_SUKU);
            Log.i("DATA", getsuku);
            getbahasa = getArguments().getString(KEY_BAHASADAERAH);
            Log.i("DATA", getbahasa);
            gettanggal = getArguments().getString(KEY_TANGGAL);
            getcarabayar = getArguments().getString(KEY_CARABAYAR);
            getcarabayarnama = getArguments().getString(KEY_CARABAYARNAMA);
            getbpjs = getArguments().getString(KEY_BPJS);
            getrujukan = getArguments().getString(KEY_RUJUKAN);
            getpoli = getArguments().getString(KEY_POLI);
            getpolinama = getArguments().getString(KEY_POLINAMA);
//            hideDialog();
        }
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
