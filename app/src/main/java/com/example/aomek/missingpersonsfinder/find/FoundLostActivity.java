package com.example.aomek.missingpersonsfinder.find;

import android.content.Intent;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;

public class FoundLostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_lost);

        Button skiptoDetail = findViewById(R.id.button_skip);
        skiptoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoundLostActivity.this, FoundLostDetailActivity.class);
                startActivity(i);
            }
        });

        Button cttoDetail = findViewById(R.id.button_next);
        cttoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoundLostActivity.this, FoundLostDetailActivity.class);
                startActivity(i);
            }
        });



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_found);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent i = new Intent(FoundLostActivity.this, MainActivity.class);
                            startActivity(i);
                            return true;
                        case R.id.navigation_found:
                            return true;
                        case R.id.navigation_add:
                            Intent k = new Intent(FoundLostActivity.this, AddLostActivity.class);
                            startActivity(k);
                            return true;
                        case R.id.navigation_profile:
                            Intent j = new Intent(FoundLostActivity.this, SettingActivity.class);
                            startActivity(j);
                            return true;
                    }
                    return false;
                }
            };
}
