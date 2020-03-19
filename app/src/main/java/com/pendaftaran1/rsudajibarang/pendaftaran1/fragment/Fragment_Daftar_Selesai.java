package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pendaftaran1.rsudajibarang.pendaftaran1.R;
import com.pendaftaran1.rsudajibarang.pendaftaran1.indexActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Daftar_Selesai extends Fragment {

    public static String KEY_JENIS_PASIEN = "jenis_pasien";
    public static String KEY_HUBUNGAN = "hubungan";
    public static String KEY_NORM = "norm";
    public static String KEY_TGLLAHIR = "tgl_lahir";
    public static String KEY_NOTELP = "notelp";
    public static String KEY_EMAIL = "email";
    public static String KEY_TANGGAL = "tanggal";
    public static String KEY_CARABAYAR = "carabayar";
    public static String KEY_CARABAYARNAMA = "carabayarnama";
    public static String KEY_BPJS = "bpjs";
    public static String KEY_RUJUKAN = "rujukan";
    public static String KEY_POLI = "poliklinik";
    public static String KEY_POLINAMA = "polikliniknama";
    public static String KEY_DOKTER = "dokter";
    public static String KEY_DOKTERNAMA = "dokternama";
    public static String KEY_BOOKINGCODE = "bookingcode";

    String getjenispasien,gethubungan,getnorm,gettgllahir,getnotelp,getemail,gettanggal,getcarabayar,getbpjs,getrujukan,getpoli,
            token,getcarabayarnama,getpolinama,getdokter,getdokternama, all, getbookingcode;
    TextView tvdaftarselesaitanggal,tvdaftarselesaipoli;

    public ImageView image;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    public Fragment_Daftar_Selesai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_selesai, container, false);
        getjenispasien = getArguments().getString(KEY_JENIS_PASIEN);
        gethubungan = getArguments().getString(KEY_HUBUNGAN);
        getnorm = getArguments().getString(KEY_NORM);
        gettgllahir = getArguments().getString(KEY_TGLLAHIR);
        getnotelp = getArguments().getString(KEY_NOTELP);
        getemail = getArguments().getString(KEY_EMAIL);
        gettanggal = getArguments().getString(KEY_TANGGAL);
        getcarabayar = getArguments().getString(KEY_CARABAYAR);
        getcarabayarnama = getArguments().getString(KEY_CARABAYARNAMA);
        getbpjs = getArguments().getString(KEY_BPJS);
        getrujukan = getArguments().getString(KEY_RUJUKAN);
        getpoli = getArguments().getString(KEY_POLI);
        getpolinama = getArguments().getString(KEY_POLINAMA);
        getdokter = getArguments().getString(KEY_DOKTER);
        getdokternama = getArguments().getString(KEY_DOKTERNAMA);
        getbookingcode = getArguments().getString(KEY_BOOKINGCODE);
        token = indexActivity.getToken();

        tvdaftarselesaitanggal = (TextView) view.findViewById(R.id.tvdaftarselesaitanggal);
        tvdaftarselesaipoli = (TextView) view.findViewById(R.id.tvdaftarselesaipoli);

        tvdaftarselesaitanggal.setText(gettanggal);
        tvdaftarselesaipoli.setText(getpolinama);

        image = (ImageView)view.findViewById(R.id.imageviewBCDaftarSelesai);

        barcode();
        return view;
    }

    public void barcode() {
        String all;
        all = getjenispasien+getnorm+gethubungan+gettgllahir+getnotelp+getemail+gettanggal+getcarabayarnama+getbpjs+getrujukan+getpolinama+getdokternama;
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(getbookingcode, BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
