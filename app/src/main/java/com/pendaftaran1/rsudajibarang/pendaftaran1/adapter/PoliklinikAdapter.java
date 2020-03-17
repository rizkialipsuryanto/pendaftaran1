package com.pendaftaran1.rsudajibarang.pendaftaran1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Fragment_poli;
import com.pendaftaran1.rsudajibarang.pendaftaran1.helper.ServiceGenerator;
import com.pendaftaran1.rsudajibarang.pendaftaran1.model.mPoliklinik;
import com.pendaftaran1.rsudajibarang.pendaftaran1.service.RestServices;

import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        TextView tvname;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            tvname = (TextView) itemView.findViewById(R.id.txt_poli);
//
//            itemView.setClickable(true);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            showDialogPoli();
//        }
//
//        public void showDialogPoli(){
//            String poli,teksbarcode;
////            teksbarcode = Fragment_poli.();
//            poli = "poli anak";
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(itemView.getRootView().getContext());
//            // set title dialog
//            alertDialogBuilder.setTitle("ANDA MEMILIH "+tvname.getText().toString()+"?");
//
//            // set pesan dari dialog
//            alertDialogBuilder
//                    .setMessage("Klik Ya untuk pilih dokter!")
//                    .setIcon(R.mipmap.ic_launcher)
//                    .setCancelable(false)
//                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int id) {
//                            // jika tombol diklik, maka akan menutup activity ini
////                        getActivity().finish();
////                        nextFragment();
//                            daftarFragmentPoli();
//                        }
//                    })
//                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // jika tombol ini diklik, akan menutup dialog
//                            // dan tidak terjadi apa2
//                            dialog.cancel();
//                        }
//                    });
//
//            // membuat alert dialog dari builder
//            AlertDialog alertDialog = alertDialogBuilder.create();
//
//            // menampilkan alert dialog
//            alertDialog.show();
//        }
//    }

}