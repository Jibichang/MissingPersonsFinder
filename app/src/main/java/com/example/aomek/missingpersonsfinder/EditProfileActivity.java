package com.example.aomek.missingpersonsfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button addButton = findViewById(R.id.button_bar_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, AddLostActivity.class);
                startActivity(i);
            }
        });

        Button foundButton = findViewById(R.id.button_bar_found);
        foundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, FoundLostActivity.class);
                startActivity(i);
            }
        });

        Button mainButton = findViewById(R.id.button_bar_main);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }
}