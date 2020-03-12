package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

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
public class Fragment_Pasien_Lama extends Fragment {

    EditText plnorma, plnotelephona,plemaila;
    Button btnpldaftara;
    private Fragment fragment;
    private FragmentManager fragmentManager;
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
        btnpldaftara = (Button)view.findViewById(R.id.btnpldaftar);

        btnpldaftara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String msg = edtMessageF.getText().toString();
                validasi();

            }
        });
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
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment newFrame = new Fragment_Dftronline();
        fm.beginTransaction().replace(R.id.flMain, newFrame).commit();
    }
}
