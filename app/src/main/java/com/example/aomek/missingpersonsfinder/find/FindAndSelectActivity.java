package com.example.aomek.missingpersonsfinder.find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.home.SplashActivity;
import com.example.aomek.missingpersonsfinder.ui.findandselect.FindAndSelectFragment;

public class FindAndSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_and_select_activity);
        if (savedInstanceState == null) {

            Button addButton = findViewById(R.id.button2);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(FindAndSelectActivity.this, SplashActivity.class);
                    startActivity(i);
                }
            });

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FindAndSelectFragment.newInstance())
                    .commitNow();
        }


    }
}
