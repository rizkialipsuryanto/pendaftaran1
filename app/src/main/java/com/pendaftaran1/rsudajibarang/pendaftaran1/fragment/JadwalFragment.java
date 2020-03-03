package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.adapter.PoliAdapter;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPoli;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends Fragment {

    private RecyclerView recyclerView;
    private PoliAdapter adapter;
    private ArrayList<mPoli> poliArrayList;

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
//        addData();

//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        poliArrayList = new ArrayList();
//        poliArrayList.add(new mPoli("Item 1", R.drawable.siapceria2, "#09A9FF"));
//        poliArrayList.add(new mPoli("Item 2", R.drawable.beer, "#3E51B1"));
//        poliArrayList.add(new mPoli("Item 3", R.drawable.ferrari, "#673BB7"));
//        poliArrayList.add(new mPoli("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
//        poliArrayList.add(new mPoli("Item 5", R.drawable.three_d, "#F94336"));
//        poliArrayList.add(new mPoli("Item 6", R.drawable.terraria, "#0A9B88"));
//
//        PoliAdapter adapter = new PoliAdapter(this, poliArrayList, this);
//        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
//        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
        return view;
    }

//    void addData(){
////        poliArrayList = new ArrayList<>();
////        poliArrayList.add(new mPoli("Dimas Maulana", "1414370309", "123456789"));
////        poliArrayList.add(new mPoli("Fadly Yonk", "1214234560", "987654321"));
////        poliArrayList.add(new mPoli("Ariyandi Nugraha", "1214230345", "987648765"));
////        poliArrayList.add(new mPoli("Aham Siswana", "1214378098", "098758124"));
////    }

}
