package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mAgama;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mBahasa;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mHubunganPasien;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mKabupaten;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mKecamatan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mKelurahan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPekerjaan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPendidikan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mProvinsi;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mStatusPernikahan;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mSuku;
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
public class Fragment_Pasien_Baru extends Fragment {

    EditText pbnamaa,pbnika,pbtempatlahira,pbalamatktpa,pbayaha,pbibua,pbsuamia,pbistria,pbnmrtelpa;
    TextView tvtempprovinsii,tvtempkabupatenn,tvtempkecamatann;
    private Spinner sppbprovinsi,sppbjeniskelamin,sppbkabupaten,sppbkecamatan,sppbkelurahan,sppbagama, sppbpendidikan, sppbpekerjaan, sppbstatuspernikahan
    ,sppbsuku,sppbbahasa,sppbhubunganpasien;
    Button btnpbdaftarr;

    private ArrayList<mProvinsi> goodModelArrayList;
    private ArrayList<mKabupaten> goodModelKabArrayList;
    private ArrayList<mKecamatan> goodModelKecArrayList;
    private ArrayList<mKelurahan> goodModelKelArrayList;
    private ArrayList<mAgama> goodModelAgamaArrayList;
    private ArrayList<mPendidikan> goodModelPendidikanArrayList;
    private ArrayList<mPekerjaan> goodModelPekerjaanArrayList;
    private ArrayList<mStatusPernikahan> goodModelStatusPernikahanArrayList;
    private ArrayList<mSuku> goodModelSukuArrayList;
    private ArrayList<mBahasa> goodModelBahasaArrayList;
    private ArrayList<mHubunganPasien> goodModelHubunganPasienArrayList;
    private ArrayList<String> playerNames = new ArrayList<String>();


    List<String> valueIdProvinsi = new ArrayList<String>();
    List<String> valueIdKota = new ArrayList<String>();
    List<String> valueKota = new ArrayList<String>();
    List<String> valueIdKecamatan = new ArrayList<String>();
    List<String> valueKecamatan = new ArrayList<String>();
    List<String> valueIdKelurahan = new ArrayList<String>();
    List<String> valueKelurahan = new ArrayList<String>();
    List<String> valueIdAgama = new ArrayList<String>();
    List<String> valueAgama = new ArrayList<String>();
    List<String> valueIdPendidikan = new ArrayList<String>();
    List<String> valuePendidikan = new ArrayList<String>();
    List<String> valuePekerjaan = new ArrayList<String>();
    List<String> valueIdPernikahan = new ArrayList<String>();
    List<String> valuePernikahan = new ArrayList<String>();
    List<String> valueIdSuku = new ArrayList<String>();
    List<String> valueSuku = new ArrayList<String>();
    List<String> valueIdBahasa = new ArrayList<String>();
    List<String> valueBahasa = new ArrayList<String>();
    List<String> valueHubunganPasien = new ArrayList<String>();

//    private String url = Base.URL + "references/provinsi";
    public Fragment_Pasien_Baru() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pasien_baru, container, false);
        btnpbdaftarr = (Button) view.findViewById(R.id.btnpbdaftar);
        pbnamaa = (EditText) view.findViewById(R.id.pbnama);
        pbnika = (EditText) view.findViewById(R.id.pbnik);
        pbtempatlahira = (EditText) view.findViewById(R.id.pbtempatlahir);
        pbalamatktpa = (EditText) view.findViewById(R.id.pbalamatktp);
        pbayaha = (EditText) view.findViewById(R.id.pbayah);
        pbibua = (EditText) view.findViewById(R.id.pbibu);
        pbsuamia = (EditText) view.findViewById(R.id.pbsuami);
        pbistria = (EditText) view.findViewById(R.id.pbistri);
        pbnmrtelpa = (EditText) view.findViewById(R.id.pbnmrtelp);
        tvtempprovinsii = (TextView) view.findViewById(R.id.tvpbtempprovinsi);
        tvtempkabupatenn = (TextView) view.findViewById(R.id.tvpvtempkabupaten);
        tvtempkecamatann = (TextView) view.findViewById(R.id.tvpbtempkecamatan);
        sppbprovinsi = (Spinner) view.findViewById(R.id.spinnerprov);
        sppbkabupaten = (Spinner) view.findViewById(R.id.spinnerkab);
        sppbkecamatan = (Spinner) view.findViewById(R.id.spinnerkec);
        sppbkelurahan = (Spinner) view.findViewById(R.id.pbspinnerkel);
        sppbjeniskelamin = (Spinner) view.findViewById(R.id.spinnerpbjk);
        sppbagama = (Spinner) view.findViewById(R.id.spinneragama);
        sppbpendidikan = (Spinner) view.findViewById(R.id.spinnerpend);
        sppbpekerjaan = (Spinner) view.findViewById(R.id.spinnerpbpekerjaan);
        sppbstatuspernikahan = (Spinner) view.findViewById(R.id.spinnerpbstatuskawin);
        sppbsuku = (Spinner) view.findViewById(R.id.spinnersuku);
        sppbbahasa = (Spinner) view.findViewById(R.id.spinnerbhs);
        sppbhubunganpasien = (Spinner) view.findViewById(R.id.spinnerpbhub);

        fetchJSONProvinsi();
        fetchJSONAgama();
        fetchJSONPendidikan();
        fetchJSONPekerjaan();
        fetchJSONStatusPernikahan();
        fetchJSONSuku();
        fetchJSONBahasa();
        fetchJSONHubunganPasien();

        btnpbdaftarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();

            }
        });

        sppbprovinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvtempprovinsii.setText(valueIdProvinsi.get(position));
//                goodModelKabArrayList.clear();
                fetchJSONKabupaten();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        sppbkabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvtempkabupatenn.setText(valueIdKota.get(position));
                fetchJSONKecamatan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        sppbkecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvtempkecamatann.setText(valueIdKecamatan.get(position));
                fetchJSONKelurahan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        sppbjeniskelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return view;
    }

    private void validasi(){
        if(pbnamaa.getText().toString().length()==0) {
            pbnamaa.setError("Masukkan Nama!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbnika.getText().toString().length()==0) {
            pbnika.setError("Masukkan NIK!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbtempatlahira.getText().toString().length()==0) {
            pbtempatlahira.setError("Masukkan Tempat Lahir!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbalamatktpa.getText().toString().length()==0) {
            pbalamatktpa.setError("Masukkan Alamat KTP!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbayaha.getText().toString().length()==0) {
            pbayaha.setError("Masukkan Ayah!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbibua.getText().toString().length()==0) {
            pbibua.setError("Masukkan Ibu!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbsuamia.getText().toString().length()==0) {
            pbsuamia.setError("Masukkan Suami!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbistria.getText().toString().length()==0) {
            pbistria.setError("Masukkan Istri!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        if(pbnmrtelpa.getText().toString().length()==0) {
            pbnmrtelpa.setError("Masukkan Telepon!");
            Toasty.error(getActivity(), "Isi Data Dengan Lengkap", Toast.LENGTH_LONG).show();
        }
        else{
            Toasty.success(getActivity(), "Pilih Tanggal dan Jenis Pelayanan", Toast.LENGTH_LONG).show();
            // Di dalam activity
            nextFragment();
        }
    }

    private void nextFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment newFrame = new Fragment_Dftronline();
        fm.beginTransaction().replace(R.id.flMain, newFrame).commit();
    }

    private void fetchJSONProvinsi(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call povinsi = restServices.ListProvinsi();

        povinsi.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONProvinsi(jsonresponse);

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

    private void spinJSONProvinsi(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");
//                    JSONObject rrarr = rrrr.getJSONObject("provinsi");
//            if(obj.optString("status").equals("true")){

                goodModelArrayList = new ArrayList<>();
                JSONArray dataArray  = rrrr.getJSONArray("provinsi");

                for (int i = 0; i < dataArray.length(); i++) {

                    mProvinsi spinnerModel = new mProvinsi();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    spinnerModel.setIdprovinsi(dataobj.getString("idprovinsi"));
                    spinnerModel.setNamaprovinsi(dataobj.getString("namaprovinsi"));

                    goodModelArrayList.add(spinnerModel);

                }

                for (int i = 0; i < goodModelArrayList.size(); i++){
                    valueIdProvinsi.add(goodModelArrayList.get(i).getIdprovinsi().toString());
                    playerNames.add(goodModelArrayList.get(i).getNamaprovinsi().toString());
                }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, playerNames);

                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                sppbprovinsi.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONKabupaten(){
        Log.d("OBJEK", "Jalan-----");
//        String id = "1";
        // REST LOGIN ------------------------------------------------------------------
//        tvtempprovinsii.setText("1");
        Log.d("OBJEK", tvtempprovinsii.getText().toString());
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call kabupatena = restServices.ListKabupaten(tvtempprovinsii.getText().toString());
//        Log.d("OBJEK", restServices.ListKabupaten(tvtempprovinsii.getText().toString()));
        kabupatena.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("OBJEK", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONKabupaten(jsonresponse);

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

    private void spinJSONKabupaten(String response){
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");
            JSONArray dataArray  = rrrr.getJSONArray("kabupaten");
            ArrayList<mKabupaten> listKabupaten = new ArrayList<mKabupaten>();
            listKabupaten.clear();
//            spinnerArrayAdapter.notifyDataSetChanged();
            for (int i = 0; i < dataArray.length(); i++) {

                mKabupaten spinnerModel = new mKabupaten();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setIdkota(dataobj.getString("idkota"));
                spinnerModel.setNamakota(dataobj.getString("namakota"));
                listKabupaten.add(spinnerModel);

            }
            valueIdKota.clear();
            valueKota.clear();
            for (int i = 0; i < listKabupaten.size(); i++){
                valueIdKota.add(listKabupaten.get(i).getIdkota().toString());
                valueKota.add(listKabupaten.get(i).getNamakota().toString());
            }
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueKota);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbkabupaten.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONKecamatan(){
        Log.d("OBJEK", "Jalan-----");
//        String id = "1";
        // REST LOGIN ------------------------------------------------------------------
//        tvtempprovinsii.setText("1");
        Log.d("OBJEK", tvtempkabupatenn.getText().toString());
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call kecamatana = restServices.ListKecamatan(tvtempkabupatenn.getText().toString());
//        Log.d("OBJEK", restServices.ListKabupaten(tvtempprovinsii.getText().toString()));
        kecamatana.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("OBJEK", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONKecamatan(jsonresponse);

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

    private void spinJSONKecamatan(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelKecArrayList = new ArrayList<>();
            goodModelKecArrayList.clear();
            JSONArray dataArray  = rrrr.getJSONArray("kecamatan");

            for (int i = 0; i < dataArray.length(); i++) {

                mKecamatan spinnerModel = new mKecamatan();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setIdkecamatan(dataobj.getString("idkecamatan"));
                spinnerModel.setNamakecamatan(dataobj.getString("namakecamatan"));

                goodModelKecArrayList.add(spinnerModel);

            }
            valueIdKecamatan.clear();
            valueKecamatan.clear();
            for (int i = 0; i < goodModelKecArrayList.size(); i++){
                valueIdKecamatan.add(goodModelKecArrayList.get(i).getIdkecamatan().toString());
                valueKecamatan.add(goodModelKecArrayList.get(i).getNamakecamatan().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueKecamatan);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbkecamatan.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONKelurahan(){
        Log.d("OBJEK", "Jalan-----");
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call kelurahann = restServices.ListKelurahan(tvtempkecamatann.getText().toString());
        kelurahann.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("OBJEK", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONKelurahan(jsonresponse);

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

    private void spinJSONKelurahan(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelKelArrayList = new ArrayList<>();
            goodModelKelArrayList.clear();
            JSONArray dataArray  = rrrr.getJSONArray("kelurahan");

            for (int i = 0; i < dataArray.length(); i++) {

                mKelurahan spinnerModel = new mKelurahan();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setIdkelurahan(dataobj.getString("idkelurahan"));
                spinnerModel.setNamakelurahan(dataobj.getString("namakelurahan"));

                goodModelKelArrayList.add(spinnerModel);

            }
            valueIdKelurahan.clear();
            valueKelurahan.clear();
            for (int i = 0; i < goodModelKelArrayList.size(); i++){
                valueIdKelurahan.add(goodModelKelArrayList.get(i).getIdkelurahan().toString());
                valueKelurahan.add(goodModelKelArrayList.get(i).getNamakelurahan().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueKelurahan);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbkelurahan.setAdapter(spinnerArrayAdapter);

//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONAgama(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call agama = restServices.ListAgama();

        agama.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONAgama(jsonresponse);

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

    private void spinJSONAgama(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelAgamaArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("agama");

            for (int i = 0; i < dataArray.length(); i++) {

                mAgama spinnerModel = new mAgama();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setId(dataobj.getString("id"));
                spinnerModel.setAgama(dataobj.getString("agama"));

                goodModelAgamaArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelAgamaArrayList.size(); i++){
                valueIdAgama.add(goodModelAgamaArrayList.get(i).getId().toString());
                valueAgama.add(goodModelAgamaArrayList.get(i).getAgama().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueAgama);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbagama.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONPendidikan(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call pendidikan = restServices.ListPendidikan();

        pendidikan.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONPendidikan(jsonresponse);

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

    private void spinJSONPendidikan(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelPendidikanArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("pendidikan");

            for (int i = 0; i < dataArray.length(); i++) {

                mPendidikan spinnerModel = new mPendidikan();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setId(dataobj.getString("id"));
                spinnerModel.setPendidikan(dataobj.getString("pendidikan"));

                goodModelPendidikanArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelPendidikanArrayList.size(); i++){
                valueIdPendidikan.add(goodModelPendidikanArrayList.get(i).getId().toString());
                valuePendidikan.add(goodModelPendidikanArrayList.get(i).getPendidikan().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valuePendidikan);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbpendidikan.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONPekerjaan(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call pekerjaan = restServices.ListPekerjaan();

        pekerjaan.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONPekerjaan(jsonresponse);

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

    private void spinJSONPekerjaan(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelPekerjaanArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("pekerjaan");

            for (int i = 0; i < dataArray.length(); i++) {

                mPekerjaan spinnerModel = new mPekerjaan();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setNama(dataobj.getString("nama"));

                goodModelPekerjaanArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelPekerjaanArrayList.size(); i++){
                valuePekerjaan.add(goodModelPekerjaanArrayList.get(i).getNama().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valuePekerjaan);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbpekerjaan.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONStatusPernikahan(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call statuspernikahan = restServices.ListStatusPernikahan();

        statuspernikahan.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONStatusPernikahan(jsonresponse);

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

    private void spinJSONStatusPernikahan(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelStatusPernikahanArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("pernikahan");

            for (int i = 0; i < dataArray.length(); i++) {

                mStatusPernikahan spinnerModel = new mStatusPernikahan();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setId(dataobj.getString("id"));
                spinnerModel.setStatusperkawinan(dataobj.getString("statusperkawinan"));

                goodModelStatusPernikahanArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelStatusPernikahanArrayList.size(); i++){
                valueIdPernikahan.add(goodModelStatusPernikahanArrayList.get(i).getId().toString());
                valuePernikahan.add(goodModelStatusPernikahanArrayList.get(i).getStatusperkawinan().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valuePernikahan);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbstatuspernikahan.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONSuku(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call suku = restServices.ListSuku();

        suku.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONSuku(jsonresponse);

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

    private void spinJSONSuku(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelSukuArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("etnis");

            for (int i = 0; i < dataArray.length(); i++) {

                mSuku spinnerModel = new mSuku();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setId(dataobj.getString("id"));
                spinnerModel.setNama_etnis(dataobj.getString("nama_etnis"));

                goodModelSukuArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelSukuArrayList.size(); i++){
                valueIdSuku.add(goodModelSukuArrayList.get(i).getId().toString());
                valueSuku.add(goodModelSukuArrayList.get(i).getNama_etnis().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueSuku);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbsuku.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONBahasa(){
        // REST LOGIN ------------------------------------------------------------------
        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call bahasa = restServices.ListBahasa();

        bahasa.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONBahasa(jsonresponse);

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

    private void spinJSONBahasa(String response){

        try {

            JSONObject obj = new JSONObject(response);
            JSONObject rrrr = obj.getJSONObject("response");

            goodModelBahasaArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("bahasa");

            for (int i = 0; i < dataArray.length(); i++) {

                mBahasa spinnerModel = new mBahasa();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setId(dataobj.getString("id"));
                spinnerModel.setBahasa_harian(dataobj.getString("bahasa_harian"));

                goodModelBahasaArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelBahasaArrayList.size(); i++){
                valueIdBahasa.add(goodModelBahasaArrayList.get(i).getId().toString());
                valueBahasa.add(goodModelBahasaArrayList.get(i).getBahasa_harian().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, valueBahasa);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbbahasa.setAdapter(spinnerArrayAdapter);
            spinnerArrayAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONHubunganPasien(){
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
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSONHubunganPasien(jsonresponse);

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
}