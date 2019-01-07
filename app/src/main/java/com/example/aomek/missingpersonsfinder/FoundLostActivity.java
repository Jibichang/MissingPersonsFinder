package com.example.aomek.missingpersonsfinder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FoundLostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_lost);

        Button mainButton = findViewById(R.id.button_bar_main);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoundLostActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button addButton = findViewById(R.id.button_bar_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoundLostActivity.this, AddLostActivity.class);
                startActivity(i);
            }
        });

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoundLostActivity.this, FoundLostDetailActivity.class);
                startActivity(i);
            }
        });


    }
}
