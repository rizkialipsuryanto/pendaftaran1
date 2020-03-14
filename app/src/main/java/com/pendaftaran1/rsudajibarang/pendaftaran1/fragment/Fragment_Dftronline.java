package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mCaraBayar;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mHubunganPasien;
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

    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail;
    EditText kalenderinputcontrol, ponmrbpjs;
    ImageButton btnTanggal;
    Button btnpodaftar;

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

        Toasty.error(getActivity(), getjenispasien, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), gethubungan, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getnorm, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), gettgllahir, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getnotelp, Toast.LENGTH_LONG).show();
        Toasty.error(getActivity(), getemail, Toast.LENGTH_LONG).show();

        kalenderinputcontrol= (EditText) view.findViewById(R.id.kalenderinputtglkontrol);
        btnTanggal = (ImageButton) view.findViewById(R.id.btnTanggalcontrol);
        spcarabayar = (Spinner) view.findViewById(R.id.spinnercarabayar);
        btnpodaftar = view.findViewById(R.id.btnpodaftar);
        ponmrbpjs = (EditText) view.findViewById(R.id.ponmrbpjs);

        ponmrbpjs.setText(getemail);

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogDatePicker();
            }
        });

        btnpodaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar();
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
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFyaWd1c3dhaHl1LmlkQGdtYWlsLmNvbSIsImZpcnN0bmFtZSI6Ii0tLS0iLCJsYXN0bmFtZSI6Ii0tLSIsImNlayI6dHJ1ZSwiaWF0IjoxNTg0MTA4OTQ2LCJleHAiOjE1ODQxMjY5NDZ9.ARwcUUTLaYyuahI9zKoESYFv0J6QbgOeaSkveQgGVnM";
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
        Call daftar = restServices.PendaftaranPasienLama(pasienbaru.toString(),getnorm.toString(),tanggal.toString(),poliklinik.toString(),dokter.toString(),
                getjenispasien.toString(),gethubungan.toString(),getnotelp.toString(),ponmrbpjs.getText().toString(),"Bearer "+token);
        daftar.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject jo = new JSONObject(response.body().toString());
                    Log.d("OBJEK","RESPON BODY : "+response.body().toString());
                    // CASTING JSON OBJECT
//                    JSONObject rrrr = jo.getJSONObject("response");
//                    JSONObject metaData = jo.getJSONObject("metaData");
//                    String code = metaData.getString("code");
//                    String message = metaData.getString("message");
//
//                    // MENDAPATKAN TOKEN
//                    String token = rrrr.getString("token");
//
//                    Log.d("OBJEK", jo.toString());
//                    Log.d("OBJEK", code);
//                    Log.d("OBJEK", message);
//
////
//                    if (Integer.parseInt(code) == 200) {
//                        Toasty.success(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//
//                        Log.d("OBJEK", "TOKEN : "+token);
////                       menyimpan login ke session
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//                        editor.putBoolean(session_status, true);
//                        editor.putString(TAG_TOKEN, token);
//                        editor.commit();
////                        // Memanggil main activity
//                        Intent intent = new Intent(Login.this, indexActivity.class);
//                        intent.putExtra(TAG_TOKEN, token);
//                        finish();
//                        startActivity(intent);
//
//                    } else {
//                        Toasty.error(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//                    }

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
}
