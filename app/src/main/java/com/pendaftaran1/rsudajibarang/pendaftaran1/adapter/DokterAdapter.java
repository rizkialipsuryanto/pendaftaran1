package com.pendaftaran1.rsudajibarang.pendaftaran1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mDokter;

import java.util.ArrayList;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<mDokter> dataModelArrayList;
    public static String KEY_ID = "id";
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(mDokter item, int posisi);
    }

    //, OnItemClickListener listener
    private Context context;
    public DokterAdapter(Context context, ArrayList<mDokter> dataModelArrayList, OnItemClickListener listener){

        this.context=context;
        inflater = LayoutInflater.from(context);
        this.dataModelArrayList = dataModelArrayList;
        this.listener = listener;
    }

    @Override
    public DokterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_poli, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DokterAdapter.MyViewHolder holder, int position) {

        holder.bind(dataModelArrayList.get(position), position, listener);
        holder.posisi=position;
        holder.tvname.setText(dataModelArrayList.get(position).getNAMADOKTER());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public int posisi;
        TextView tvname;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvname = (TextView) itemView.findViewById(R.id.txt_poli);
            itemView.setClickable(true);
//            itemView.setOnClickListener(this);
        }

        public void bind(final mDokter item, final int position, final OnItemClickListener listener) {
            if(listener!=null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAGI", String.valueOf(position));
                        listener.onItemClick(item, position);
                    }
                });
            }
        }

    }
}
