package com.example.aomek.missingpersonsfinder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.button_bar_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddLostActivity.class);
                startActivity(i);
            }
        });

        Button findmoreButton = findViewById(R.id.button_find_more);
        findmoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FindMoreActivity.class);
                startActivity(i);
            }
        });

        Button foundButton = findViewById(R.id.button_bar_found);
        foundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FoundLostActivity.class);
                startActivity(i);
            }
        });

    }
}
