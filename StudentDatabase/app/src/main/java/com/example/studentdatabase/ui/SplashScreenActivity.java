package com.example.studentdatabase.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentdatabase.R;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences = getSharedPreferences("prefers",MODE_PRIVATE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                boolean alreadyLogin;
                alreadyLogin = preferences.getBoolean("isLogin", false);

                if(alreadyLogin) {
                    startActivity(new Intent(SplashScreenActivity.this, BottomNavActivity.class));
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, loginActivity.class));
                }

                finish();

            }
        }, 3000);
    }
}
