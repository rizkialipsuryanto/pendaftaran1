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

import com.android.volley.Request;
//import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.app.AppController;
import com.pendaftaran1.rsudajibarang.pendaftaran1.constant.Base;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mKabupaten;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mProvinsi;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.ProvinsiService;
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
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import static android.R.layout.simple_spinner_item;

import static com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity.pDialog;
import static com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity.volleyerror;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Pasien_Baru extends Fragment {

    EditText pbnamaa,pbnika,pbtempatlahira,pbalamatktpa,pbayaha,pbibua,pbsuamia,pbistria,pbnmrtelpa;
    TextView tvtempprovinsii;
    Button btnpbdaftarr;
    Spinner pbspprovinsi;

    private ArrayList<mProvinsi> goodModelArrayList;
    private ArrayList<mKabupaten> goodModelKabArrayList;
    private ArrayList<String> playerNames = new ArrayList<String>();
    private Spinner sppbprovinsi,sppbjeniskelamin,sppbkabupaten;


    List<String> valueIdProvinsi = new ArrayList<String>();

    List<String> valueIdKota = new ArrayList<String>();
    List<String> valueKota = new ArrayList<String>();
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
        sppbprovinsi = (Spinner) view.findViewById(R.id.spinnerprov);
        sppbkabupaten = (Spinner) view.findViewById(R.id.spinnerkab);
        sppbjeniskelamin = (Spinner) view.findViewById(R.id.spinnerpbjk);

        fetchJSONProvinsi();

        btnpbdaftarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();

            }
        });

        sppbprovinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                tvtempprovinsii.setText(valueIdProvinsi.get(position));
                fetchJSONKabupaten();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        sppbjeniskelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toasty.success(getActivity(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
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

//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchJSONKabupaten(){
        // REST LOGIN ------------------------------------------------------------------

        RestServices restServices = ServiceGenerator.build().create(RestServices.class);
        Call kabupaten = restServices.ListKabupaten(tvtempprovinsii.getText().toString());

        kabupaten.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("Responsestring", response.body().toString());
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

            goodModelKabArrayList = new ArrayList<>();
            JSONArray dataArray  = rrrr.getJSONArray("kabupaten");

            for (int i = 0; i < dataArray.length(); i++) {

                mKabupaten spinnerModel = new mKabupaten();
                JSONObject dataobj = dataArray.getJSONObject(i);

                spinnerModel.setIdkota(dataobj.getString("idkota"));
                spinnerModel.setNamakota(dataobj.getString("namakota"));

                goodModelKabArrayList.add(spinnerModel);

            }

            for (int i = 0; i < goodModelKabArrayList.size(); i++){
                valueIdKota.add(goodModelKabArrayList.get(i).getIdkota().toString());
                valueKota.add(goodModelKabArrayList.get(i).getNamakota().toString());
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), simple_spinner_item, playerNames);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            sppbkabupaten.setAdapter(spinnerArrayAdapter);

//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}