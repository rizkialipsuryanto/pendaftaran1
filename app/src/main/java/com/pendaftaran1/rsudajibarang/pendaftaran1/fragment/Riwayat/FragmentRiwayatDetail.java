package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Riwayat;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRiwayatDetail extends Fragment {

    public static String KEY_ID = "id";
    String getid, teksbarcode;
    public ImageView image;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    public FragmentRiwayatDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat_detail, container, false);
        getid = getArguments().getString(KEY_ID);

        image = (ImageView)view.findViewById(R.id.imageviewBCRiwayat);
        barcode();
        return view;
    }

    public void barcode() {
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(getid, BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
