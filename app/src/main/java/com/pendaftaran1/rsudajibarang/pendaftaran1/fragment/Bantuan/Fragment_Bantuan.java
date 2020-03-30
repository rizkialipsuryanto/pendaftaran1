package com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.Bantuan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pendaftaran1.rsudajibarang.pendaftaran1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Bantuan extends Fragment {

    ListView listView;
    TextView textView;
    String[] listItem;
    Button imgbtnwa;
    public Fragment_Bantuan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bantuan, container, false);

        listView=(ListView)view.findViewById(R.id.listViewbantuan);
        textView=(TextView)view.findViewById(R.id.tvbantuanlist);
        listItem = getResources().getStringArray(R.array.list_bantuan);
        imgbtnwa=(Button)view.findViewById(R.id.imgbuttonwa);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        imgbtnwa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wa();
            }
        });
        return view;
    }

    private void wa(){
        try {
            String headerReceiver = "";// Replace with your message.
            String bodyMessageFormal = "";// Replace with your message.
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+6285640769886"; //10 digit number
            Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + "" ) );
            startActivity ( intent );
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
