package com.pendaftaran1.rsudajibarang.pendaftaran1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(1000);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
//                toas.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashScreen.this, Login.class);
        startActivity(intent);
    }
}
