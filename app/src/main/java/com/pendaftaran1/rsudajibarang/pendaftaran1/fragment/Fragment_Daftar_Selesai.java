package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Daftar_Selesai extends Fragment {

    public Fragment_Daftar_Selesai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_selesai, container, false);
        return view;
    }
}
