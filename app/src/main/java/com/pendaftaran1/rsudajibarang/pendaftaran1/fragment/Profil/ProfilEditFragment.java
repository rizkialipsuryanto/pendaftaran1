package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Profil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilEditFragment extends Fragment {

    EditText firstname, lastname,nik, alamat, nohp,email,password;
    public ProfilEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil_edit, container, false);
        firstname = (EditText) view.findViewById(R.id.etProfilEditFirstname);
        lastname = (EditText) view.findViewById(R.id.etProfilEditLastname);
        nik = (EditText) view.findViewById(R.id.etProfilEditNik);
        alamat = (EditText) view.findViewById(R.id.etProfilEditAlamat);
        nohp = (EditText) view.findViewById(R.id.etProfilEditNoHp);
        email = (EditText)view.findViewById(R.id.etProfilEditEmail);
        password = (EditText)view.findViewById(R.id.etProfilEditPassword);
        return view;
    }
}
