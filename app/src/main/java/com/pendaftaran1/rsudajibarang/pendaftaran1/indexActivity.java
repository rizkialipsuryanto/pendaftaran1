package com.pendaftaran1.rsudajibarang.pendaftaran1;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.HomeFragment;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.JadwalFragment;
import com.pendaftaran1.rsudajibarang.pendaftaran1.fragment.ProfilFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class indexActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    public static final String TAG_TOKEN = "token";
    public static String token;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new JadwalFragment();
                    break;
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
                case R.id.navigation_notifications:
                    fragment = new ProfilFragment();
                    break;
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
        token = getIntent().getStringExtra(TAG_TOKEN);

        Toast.makeText(getApplicationContext(),token, Toast.LENGTH_LONG).show();
    }
    public static String getToken() {
        return token;
    }
}
