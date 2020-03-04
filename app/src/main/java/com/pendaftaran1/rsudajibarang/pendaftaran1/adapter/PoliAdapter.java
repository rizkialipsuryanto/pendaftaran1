package com.pendaftaran1.rsudajibarang.pendaftaran1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPoli;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PoliAdapter extends RecyclerView.Adapter<PoliAdapter.PoliViewHolder>{
    private ArrayList<mPoli> dataList;

    public PoliAdapter(ArrayList<mPoli> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PoliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_poli, parent, false);
        return new PoliViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PoliViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PoliViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama;

        public PoliViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_poli);
        }
    }
}
