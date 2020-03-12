package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Pasien_Baru extends Fragment {

    EditText pbnamaa,pbnika,pbtempatlahira,pbalamatktpa,pbayaha,pbibua,pbsuamia,pbistria,pbnmrtelpa;
    Button btnpbdaftarr;
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

        btnpbdaftarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String msg = edtMessageF.getText().toString();
                validasi();

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
}
