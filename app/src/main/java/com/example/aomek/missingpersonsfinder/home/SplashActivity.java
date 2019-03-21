package com.example.aomek.missingpersonsfinder.home;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.model.Lost;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CountDownTimer countDownTimer = new CountDownTimer(3 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
//                        mAddView.setVisibility(View.VISIBLE);
                Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(i);
            }
        };
        countDownTimer.start();
        // set place list
        Lost.setListplace();
    }
}
