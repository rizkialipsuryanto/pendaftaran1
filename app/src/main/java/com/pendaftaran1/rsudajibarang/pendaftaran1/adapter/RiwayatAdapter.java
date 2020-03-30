package com.pendaftaran1.rsudajibarang.pendaftaran1.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Riwayat.FragmentRiwayatDetail;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mRiwayat;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<mRiwayat> dataModelArrayList;
    public static String KEY_ID = "id";


    private Context context;
    public RiwayatAdapter(Context context, ArrayList<mRiwayat> dataModelArrayList){

        this.context=context;
        inflater = LayoutInflater.from(context);
        this.dataModelArrayList = dataModelArrayList;
//        this.listener = listener;
    }

    @Override
    public RiwayatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_riwayat, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RiwayatAdapter.MyViewHolder holder, int position) {

        holder.tvtanggal.setText("Tanggal: "+dataModelArrayList.get(position).getTanggal());
        holder.tvpoli.setText("Poli: "+dataModelArrayList.get(position).getPoliklinik());
        holder.tvdokter.setText("Dokter: "+dataModelArrayList.get(position).getDokter());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvtanggal, tvpoli, tvdokter;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvtanggal = (TextView) itemView.findViewById(R.id.tvtanggallist);
            tvpoli = (TextView) itemView.findViewById(R.id.tvpolilist);
            tvdokter = (TextView) itemView.findViewById(R.id.tvdokterlist);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FragmentRiwayatDetail secondFragtry = new FragmentRiwayatDetail();
            Bundle mBundle = new Bundle();
            mBundle.putString(KEY_ID, "id");
//
            secondFragtry.setArguments(mBundle);
            FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.flMain, secondFragtry).commit();
//            Toasty.success(context, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();
            Toasty.success(context, "Next", Toast.LENGTH_SHORT).show();
        }
    }
}