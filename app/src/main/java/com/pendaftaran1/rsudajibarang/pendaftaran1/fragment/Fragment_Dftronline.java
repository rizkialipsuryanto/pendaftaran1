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
    public static String KEY_BPJS = "bpjs";
    public static String KEY_RUJUKAN = "rujukan";
    public static String save;

    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail;
    EditText kalenderinputcontrol, ponmrbpjs, ponmrrujukan;
    LinearLayout llnobpjs;
    ImageButton btnTanggal;
    Button btnpodaftar;
    TextView tvcarabayartemp;


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
        gethubungan = getArguments().getString(KEY_HUBUNGAN);
        getnorm = getArguments().getString(KEY_NORM);
        gettgllahir = getArguments().getString(KEY_TGLLAHIR);
        getnotelp = getArguments().getString(KEY_NOTELP);
        getemail = getArguments().getString(KEY_EMAIL);

//        Toasty.error(getActivity(), getjenispasien, Toast.LENGTH_LONG).show();
//        Toasty.error(getActivity(), gethubungan, Toast.LENGTH_LONG).show();
//        Toasty.error(getActivity(), getnorm, Toast.LENGTH_LONG).show();
//        Toasty.error(getActivity(), gettgllahir, Toast.LENGTH_LONG).show();
//        Toasty.error(getActivity(), getnotelp, Toast.LENGTH_LONG).show();
//        Toasty.error(getActivity(), getemail, Toast.LENGTH_LONG).show();

        kalenderinputcontrol= (EditText) view.findViewById(R.id.kalenderinputtglkontrol);
        btnTanggal = (ImageButton) view.findViewById(R.id.btnTanggalcontrol);
        spcarabayar = (Spinner) view.findViewById(R.id.spinnercarabayar);
        btnpodaftar = view.findViewById(R.id.btnpodaftar);
        ponmrbpjs = (EditText) view.findViewById(R.id.ponmrbpjs);
        ponmrrujukan = (EditText) view.findViewById(R.id.ponmrrujukan);
        tvcarabayartemp = (TextView) view.findViewById(R.id.tvcarabayartemp);
        llnobpjs = (LinearLayout) view.findViewById(R.id.llnobpjs);

//        ponmrbpjs.setVisibility(View.GONE);
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
//                daftar();
//                showDialog();
                nextFragment();
            }
        });

        spcarabayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvcarabayartemp.setText(valueId.get(position));
                if(tvcarabayartemp.getText().toString().equals("4") || tvcarabayartemp.getText().toString().equals("3")){
                    llnobpjs.setVisibility(View.VISIBLE);
                    ponmrbpjs.setText(valueId.get(position));
                    Log.d("OBJEK", valueId.get(position));
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

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueNama);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spcarabayar.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void daftar() {
        String pasienbaru = "0";
        String poliklinik = "2";
        String dokter = "1";
        String tanggal = "2020-03-17";
        String normnya = "1";
        String jenispasiennya = "SENDIRI";
        String hubungannya = "TIDAK ADA";
        String notelpnya = "4444";
        String emailnya = "aa@gmail.com";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyIiwiZW1haWwiOiJhcmlndXN3YWh5dS5pZEBnbWFpbC5jb20iLCJmaXJzdG5hbWUiOiItLS0tIiwibGFzdG5hbWUiOiItLS0iLCJjZWsiOnRydWUsImlhdCI6MTU4NDE0NjY4MSwiZXhwIjoxNTg0MTY0NjgxfQ.x86nzjptDyIbEoluwzEZ_k8naThFqbo8n80smkXuQHY";
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
        Call daftar = restServices.PendaftaranPasienLama(pasienbaru.toString(),getnorm,kalenderinputcontrol.getText().toString(),poliklinik.toString(),dokter.toString(),
                getjenispasien.toString(),gethubungan.toString(),getnotelp.toString(),getemail.toString(),tvcarabayartemp.getText().toString(),
                ponmrbpjs.getText().toString(),ponmrrujukan.getText().toString(),"Bearer "+token);
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

        Fragment_poli secondFragtry = new Fragment_poli();
        Bundle mBundle = new Bundle();
        mBundle.putString(KEY_JENIS_PASIEN, getjenispasien);
        mBundle.putString(KEY_HUBUNGAN, gethubungan);
        mBundle.putString(KEY_NORM, getnorm);
        mBundle.putString(KEY_TGLLAHIR, gettgllahir);
        mBundle.putString(KEY_NOTELP, getnotelp);
        mBundle.putString(KEY_EMAIL, getemail);
        mBundle.putString(KEY_TANGGAL, kalenderinputcontrol.getText().toString());
        mBundle.putString(KEY_CARABAYAR, spcarabayar.getSelectedItem().toString());
        mBundle.putString(KEY_BPJS, ponmrbpjs.getText().toString());
        mBundle.putString(KEY_RUJUKAN, ponmrrujukan.getText().toString());

        secondFragtry.setArguments(mBundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }

//    public static String getSave(){
//        return daftar;
//    }

}
