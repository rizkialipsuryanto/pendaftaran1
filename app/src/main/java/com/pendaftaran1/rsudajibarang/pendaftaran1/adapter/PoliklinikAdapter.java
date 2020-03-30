package com.pendaftaran1.rsudajibarang.pendaftaran1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPoliklinik;

import java.util.ArrayList;

public class PoliklinikAdapter extends RecyclerView.Adapter<PoliklinikAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<mPoliklinik> dataModelArrayList;
    public static String KEY_ID = "id";
    private final OnItemClickListener listener;

//    public PoliklinikAdapter(FragmentActivity activity, ArrayList<mPoliklinik> goodModelPoliklinikArrayList) {
//    }

    public interface OnItemClickListener {
        void onItemClick(mPoliklinik item, int posisi);
    }

//, OnItemClickListener listener
    private Context context;
    public PoliklinikAdapter(Context context, ArrayList<mPoliklinik> dataModelArrayList, OnItemClickListener listener){

        this.context=context;
        inflater = LayoutInflater.from(context);
        this.dataModelArrayList = dataModelArrayList;
        this.listener = listener;
    }

    @Override
    public PoliklinikAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_poli, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PoliklinikAdapter.MyViewHolder holder, int position) {

        holder.bind(dataModelArrayList.get(position), position, listener);
        holder.posisi=position;
        holder.tvname.setText(dataModelArrayList.get(position).getNama());
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

        public void bind(final mPoliklinik item, final int position, final OnItemClickListener listener) {
            if(listener!=null) {
//                tvname.setText(item.getNama());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("OBJEK", String.valueOf(position));
//                        Log.e("OBJEK", tvname.getText().toString());
                        listener.onItemClick(item, position);
                    }
                });
            }
        }

    }


}
