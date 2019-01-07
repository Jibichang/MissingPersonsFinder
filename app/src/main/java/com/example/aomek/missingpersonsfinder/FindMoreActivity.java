package com.example.aomek.missingpersonsfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more);

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindMoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button mainButton = findViewById(R.id.button_bar_main);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindMoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button addButton = findViewById(R.id.button_bar_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindMoreActivity.this, AddLostActivity.class);
                startActivity(i);
            }
        });
    }
}
