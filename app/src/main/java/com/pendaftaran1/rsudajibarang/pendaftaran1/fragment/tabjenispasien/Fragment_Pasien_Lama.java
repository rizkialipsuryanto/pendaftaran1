package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.tabjenispasien;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

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

import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Pendaftaran.Fragment_Dftronline;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mHubunganPasien;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Pasien_Lama extends Fragment {
    public static String KEY_JENIS_PASIEN = "jenis_pasien";
    public static String KEY_HUBUNGAN = "hubungan";
    public static String KEY_NORM = "norm";
    public static String KEY_TGLLAHIR = "tgl_lahir";
    public static String KEY_NOTELP = "notelp";
    public static String KEY_EMAIL = "email";

    public static ProgressDialog pDialog;

    EditText plnorma, plnotelephona,plemaila, kalenderinputcatatan;
    Button btnpldaftara;
    ImageButton btnTanggal;
    String hubunganspinner, token;

    private Spinner sppbhubunganpasien;

    private ArrayList<mHubunganPasien> goodModelHubunganPasienArrayList;

    List<String> valueHubunganPasien = new ArrayList<String>();

    public Fragment_Pasien_Lama() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pasien_lama, container, false);
        plnorma = (EditText) view.findViewById(R.id.plnorm);
        plnotelephona = (EditText) view.findViewById(R.id.plnotelephon);
        plemaila = (EditText)view.findViewById(R.id.plemail);
        sppbhubunganpasien = (Spinner) view.findViewById(R.id.spinnerplhubungan);
        btnpldaftara = (Button)view.findViewById(R.id.btnpldaftar);
        btnTanggal = (ImageButton) view.findViewById(R.id.btnTanggal1);
        kalenderinputcatatan = view.findViewById(R.id.kalenderinputcatatan);

        Toasty.error(getActivity(), indexActivity.getIdUser(), Toast.LENGTH_LONG).show();

        btnpldaftara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();

            }
        });

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogDatePicker();
            }
        });

        token = indexActivity.getToken();

        fetchJSONHubunganPasien();
        return view;
    }

    private void validasi(){
        if(plnorma.getText().toString().length()==0) {
            plnorma.setError("Masukkan No RM!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(plnotelephona.getText().toString().length()==0) {
            plnotelephona.setError("Masukkan No Telephon!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(kalenderinputcatatan.getText().toString().length()==0) {
            kalenderinputcatatan.setError("Pilih Tanggal Lahir!");
            Toasty.error(getActivity(), "Pilih Tanggal Lahir", Toast.LENGTH_LONG).show();
        }
        if(plemaila.getText().toString().length()==0) {
            plemaila.setError("Masukkan Email!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }

        else{
            Toasty.success(getActivity(), "Pilih Tanggal dan Jenis Pelayanan", Toast.LENGTH_LONG).show();
            nextFragment();
        }
    }

    private void nextFragment() {
        // TODO Auto-generated method stub

        Fragment_Dftronline secondFragtry = new Fragment_Dftronline();
        Bundle mBundle = new Bundle();
        mBundle.putString(KEY_JENIS_PASIEN, "0");
        mBundle.putString(KEY_HUBUNGAN, sppbhubunganpasien.getSelectedItem().toString());
        mBundle.putString(KEY_NORM, plnorma.getText().toString());
        mBundle.putString(KEY_TGLLAHIR, kalenderinputcatatan.getText().toString());
        mBundle.putString(KEY_NOTELP, plnotelephona.getText().toString());
        mBundle.putString(KEY_EMAIL, plemaila.getText().toString());

        secondFragtry.setArguments(mBundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
    }

    private void fetchJSONHubunganPasien(){
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call hubunganpasien = restServices.ListHubunganPasien();

        hubunganpasien.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        hideDialog();
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONHubunganPasien(jsonresponse);

                    } else {
                        hideDialog();
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                hideDialog();
                Log.i("onFailure",t.getMessage().toString());
            }
        });
    }

    private void spinJSONHubunganPasien(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelHubunganPasienArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("hubungan");

            for (int i = 0; i < dataArray.length(); i++) {

                mHubunganPasien spinnerModel = new mHubunganPasien();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setNama(dataobj.getString("nama"));

                goodModelHubunganPasienArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelHubunganPasienArrayList.size(); i++){
                valueHubunganPasien.add(goodModelHubunganPasienArrayList.get(i).getNama().toString());

            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueHubunganPasien);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbhubunganpasien.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String desimal(int des){
        String dc=String.valueOf(des);
        if(des<10){
            dc="0"+dc;
        }
        return dc;
    }

    public void alertDialogDatePicker() {
        // inflate file
        LayoutInflater inflater = (LayoutInflater)getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate file layout_datepicker.xml
        View view = inflater.inflate(R.layout.layout_datepicker, null, false);

        // dapatkan id dan nilai
//        final DatePicker myDatePicker = (DatePicker)view
//                .findViewById(R.id.datepicker);

        // buat popup
//        new AlertDialog.Builder(getActivity()).setView(view)
//                // judul
//                .setTitle("Tanggal Hari Ini")
//                // tombol
//                .setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        String month=desimal(myDatePicker.getMonth() + 1);
//                        String day=desimal(myDatePicker.getDayOfMonth());
//                        int year = myDatePicker.getYear();
//                        // print hasil dalam toast
//                        kalenderinputcatatan.setText(year+"-"+month+"-"+day);
//                        dialog.cancel();
//                    }
//                }).show();

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, (month+1));
                calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                kalenderinputcatatan.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DAY_OF_YEAR, -1);
//        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis()); -(1000*60*60*24*1)// TODO: used to hide previous date,month and year
        calendar.add(Calendar.YEAR, 0);
        dialog.getDatePicker().setMaxDate((calendar.getTimeInMillis()));
        dialog.show();

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
