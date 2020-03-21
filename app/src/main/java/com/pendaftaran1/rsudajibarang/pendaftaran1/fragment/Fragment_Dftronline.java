package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mCaraBayar;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mHubunganPasien;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Dftronline extends Fragment {
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
    public static String save;

    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,getnama,getnik,getjeniskelamin,gettempatlahir,getalamatsesuaiktp,getprovinsi,getkabupaten,getkecamatan;
    String getkelurahan, getnamaayah,getnamaibu,getsuami,getistri,getagama,getpendidikan,getpekerjaan,getstatuskawin,getkewarganegaraan,getsuku,getbahasa;
    EditText kalenderinputcontrol, ponmrbpjs, ponmrrujukan;
    LinearLayout llnobpjs;
    ImageButton btnTanggal;
    Button btnpodaftar;
    TextView tvcarabayartemp, tvcarabayartempnama;


    private Spinner spcarabayar;
    private ArrayList<mCaraBayar> goodModelCaraBayarArrayList;
    List<String> valueId = new ArrayList<String>();
    List<String> valueNama = new ArrayList<String>();

    public Fragment_Dftronline() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__dftronline, container, false);

        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        getfrombefore();

        kalenderinputcontrol= (EditText) view.findViewById(R.id.kalenderinputtglkontrol);
        btnTanggal = (ImageButton) view.findViewById(R.id.btnTanggalcontrol);
        spcarabayar = (Spinner) view.findViewById(R.id.spinnercarabayar);
        btnpodaftar = view.findViewById(R.id.btnpodaftar);
        ponmrbpjs = (EditText) view.findViewById(R.id.ponmrbpjs);
        ponmrrujukan = (EditText) view.findViewById(R.id.ponmrrujukan);
        tvcarabayartemp = (TextView) view.findViewById(R.id.tvcarabayartemp);
        tvcarabayartempnama = (TextView) view.findViewById(R.id.tvcarabayartempnama);
        llnobpjs = (LinearLayout) view.findViewById(R.id.llnobpjs);

        llnobpjs.setVisibility(View.GONE);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogDatePicker();
            }
        });

        btnpodaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFragment();
            }
        });

        spcarabayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvcarabayartemp.setText(valueId.get(position));
                tvcarabayartempnama.setText(valueNama.get(position));
                if(tvcarabayartemp.getText().toString().equals("4") || tvcarabayartemp.getText().toString().equals("3")){
                    llnobpjs.setVisibility(View.VISIBLE);
//                    ponmrbpjs.setText(valueNama.toString());
                    Log.d("OBJEK", valueId.get(position));
//                    Log.d("OBJEK", valueNama.get(position));
                }
                else{
                    llnobpjs.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        fetchJSONHubunganPasien();
        return view;
    }

    private void fetchJSONHubunganPasien(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call carabayar = restServices.ListCaraBayar();

        carabayar.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONCaraBayar(jsonresponse);

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

    private void spinJSONCaraBayar(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelCaraBayarArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("carabayar");

            for (int i = 0; i < dataArray.length(); i++) {

                mCaraBayar spinnerModel = new mCaraBayar();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setKode(dataobj.getString("KODE"));
                spinnerModel.setNama(dataobj.getString("NAMA"));

                goodModelCaraBayarArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelCaraBayarArrayList.size(); i++){
                valueId.add(goodModelCaraBayarArrayList.get(i).getKode().toString());
                valueNama.add(goodModelCaraBayarArrayList.get(i).getNama().toString());
            }
//            ponmrbpjs.setText(valueNama.toString());

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueNama);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spcarabayar.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void alertDialogDatePicker() {
        // inflate file
        LayoutInflater inflater = (LayoutInflater)getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate file layout_datepicker.xml
        View view = inflater.inflate(R.layout.layout_datepicker, null, false);

        // dapatkan id dan nilai
        final DatePicker myDatePicker = (DatePicker)view
                .findViewById(R.id.datepicker);

        // buat popup
        new AlertDialog.Builder(getActivity()).setView(view)
                // judul
                .setTitle("Tanggal Hari Ini")
                // tombol
                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String month=desimal(myDatePicker.getMonth() + 1);
                        String day=desimal(myDatePicker.getDayOfMonth());
                        int year = myDatePicker.getYear();
                        // print hasil dalam toast
                        kalenderinputcontrol.setText(year+"-"+month+"-"+day);
                        dialog.cancel();
                    }
                }).show();

    }

    public String desimal(int des){
        String dc=String.valueOf(des);
        if(des<10){
            dc="0"+dc;
        }
        return dc;
    }

    private void nextFragment() {
        // TODO Auto-generated method stub
        String jenis_pasien;
        jenis_pasien = getjenispasien.toString();
        if(tvcarabayartemp.getText().toString().equals("4") || tvcarabayartemp.getText().toString().equals("3")) {
            if(ponmrbpjs.getText().toString().length()<13){
                ponmrbpjs.setError("Masukkan 13 Digit!");
                Toasty.error(getActivity(), "Masukkan 13 Digit!", Toast.LENGTH_LONG).show();
            }
            if(ponmrrujukan.getText().toString().length()<16) {
                ponmrrujukan.setError("Masukkan 16 Digit!");
                Toasty.error(getActivity(), "Masukkan 16 Digit!", Toast.LENGTH_LONG).show();
            }
            if (ponmrbpjs.getText().toString().length()==13 && ponmrrujukan.getText().toString().length()==16){

                Toasty.error(getActivity(), jenis_pasien, Toast.LENGTH_LONG).show();
                postnextfragment();
//                if(jenis_pasien == "0")
//                {
//                    Fragment_poli secondFragtry = new Fragment_poli();
//                    Bundle mBundle = new Bundle();
//                    mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
//                    mBundle.putString(KEY_HUBUNGAN, gethubungan);
//                    mBundle.putString(KEY_NORM, getnorm);
//                    mBundle.putString(KEY_TGLLAHIR, gettgllahir);
//                    mBundle.putString(KEY_NOTELP, getnotelp);
//                    mBundle.putString(KEY_EMAIL, getemail);
//                    mBundle.putString(KEY_TANGGAL, kalenderinputcontrol.getText().toString());
//                    mBundle.putString(KEY_CARABAYAR, tvcarabayartemp.getText().toString());
//                    mBundle.putString(KEY_CARABAYARNAMA, tvcarabayartempnama.getText().toString());
//                    mBundle.putString(KEY_BPJS, ponmrbpjs.getText().toString());
//                    mBundle.putString(KEY_RUJUKAN, ponmrrujukan.getText().toString());
//                    secondFragtry.setArguments(mBundle);
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
//                }
//                if(jenis_pasien == "0")
//                {
//                    Fragment_poli secondFragtry = new Fragment_poli();
//                    Bundle mBundle = new Bundle();
//                    mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
//                    mBundle.putString(KEY_HUBUNGAN, gethubungan);
//                    mBundle.putString(KEY_NAMA, getnama);
//                    mBundle.putString(KEY_NIK, getnik);
//                    mBundle.putString(KEY_JENISKELAMIN, getjeniskelamin);
//                    mBundle.putString(KEY_TEMPATLAHIR, gettempatlahir);
//                    mBundle.putString(KEY_ALAMATSESUAIKTP, getalamatsesuaiktp);
//                    mBundle.putString(KEY_PROVINSI, getprovinsi);
//                    mBundle.putString(KEY_KABUPATEN, getkabupaten);
//                    mBundle.putString(KEY_KECAMATAN, getkecamatan);
//                    mBundle.putString(KEY_KELURAHAN, getkelurahan);
//                    mBundle.putString(KEY_NAMAAYAH, getnamaayah);
//                    mBundle.putString(KEY_NAMAIBU, getnamaibu);
//                    mBundle.putString(KEY_SUAMI, getsuami);
//                    mBundle.putString(KEY_ISTRI, getistri);
//                    mBundle.putString(KEY_NOTELP, getnotelp);
//                    mBundle.putString(KEY_AGAMA, getagama);
//                    mBundle.putString(KEY_PENDIDIKAN, getpendidikan);
//                    mBundle.putString(KEY_PEKERJAAN, getpekerjaan);
//                    mBundle.putString(KEY_STATUSKAWIN, getstatuskawin);
//                    mBundle.putString(KEY_KEWARGANEGARAAN, getkewarganegaraan);
//                    mBundle.putString(KEY_SUKU, getsuku);
//                    mBundle.putString(KEY_BAHASADAERAH, getbahasa);
//                    mBundle.putString(KEY_TANGGAL, kalenderinputcontrol.getText().toString());
//                    mBundle.putString(KEY_CARABAYAR, tvcarabayartemp.getText().toString());
//                    mBundle.putString(KEY_CARABAYARNAMA, tvcarabayartempnama.getText().toString());
//                    mBundle.putString(KEY_BPJS, ponmrbpjs.getText().toString());
//                    mBundle.putString(KEY_RUJUKAN, ponmrrujukan.getText().toString());
//                    secondFragtry.setArguments(mBundle);
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
//                }
            }

        }
        else {
            postnextfragment();
        }

    }

    private void getfrombefore(){
        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        Log.i("OBJEK", getjenispasien);
        if(getjenispasien == String.valueOf('0')) {
            getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
            gethubungan = getArguments().getString(KEY_HUBUNGAN);
            getnorm = getArguments().getString(KEY_NORM);
            gettgllahir = getArguments().getString(KEY_TGLLAHIR);
            getnotelp = getArguments().getString(KEY_NOTELP);
            getemail = getArguments().getString(KEY_EMAIL);
        }
        if(getjenispasien == String.valueOf('1'))
        {
            getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
            gethubungan = getArguments().getString(KEY_HUBUNGAN);
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
        }
    }

    private void postnextfragment(){
            Fragment_poli secondFragtry = new Fragment_poli();
            Bundle mBundle = new Bundle();
            mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
            mBundle.putString(KEY_HUBUNGAN, gethubungan);
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
            mBundle.putString(KEY_TANGGAL, kalenderinputcontrol.getText().toString());
            mBundle.putString(KEY_CARABAYAR, tvcarabayartemp.getText().toString());
            mBundle.putString(KEY_CARABAYARNAMA, tvcarabayartempnama.getText().toString());
            mBundle.putString(KEY_BPJS, ponmrbpjs.getText().toString());
            mBundle.putString(KEY_RUJUKAN, ponmrrujukan.getText().toString());
            secondFragtry.setArguments(mBundle);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }
}
