package com.it.rsudajibarang.siap_ceria_rsud.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import com.it.rsudajibarang.siap_ceria_rsud.R;
import com.it.rsudajibarang.siap_ceria_rsud.indexActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public Button barcode;
    public ImageView image;

    String teksbarcode;

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        barcode = (Button)view.findViewById(R.id.barcodeteks);
        image = (ImageView)view.findViewById(R.id.imageview);

        teksbarcode = indexActivity.getToken();
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String greeting = teksbarcode;
//                teksbarcode = teks.getText().toString();
                try
                {
                    BitMatrix bitMatrix = multiFormatWriter.encode(teksbarcode, BarcodeFormat.QR_CODE, 300,300);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

//    public void onClick(View v) {
//        String greeting = "Hello world!";
////        teksbarcode = aaaaa;
//        try                {
//            BitMatrix bitMatrix = multiFormatWriter.encode(greeting, BarcodeFormat.QR_CODE, 300,300);
//            BarcodeEncoder encoder = new BarcodeEncoder();
//            Bitmap bitmap = encoder.createBitmap(bitMatrix);
//            image.setImageBitmap(bitmap);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }

}
