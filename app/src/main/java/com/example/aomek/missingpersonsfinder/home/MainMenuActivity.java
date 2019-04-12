package com.example.aomek.missingpersonsfinder.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.find.FindMoreActivity;
import com.example.aomek.missingpersonsfinder.find.FoundLostActivity;
import com.example.aomek.missingpersonsfinder.find.SelecterActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.result.ResultLostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class MainMenuActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Set Database
        mHelper = new DatabaseHelper(MainMenuActivity.this);
        mDb = mHelper.getWritableDatabase();

        doCheckLogin();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_info);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Button goFound = findViewById(R.id.main_button_found);
        goFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FoundLostActivity.class);
                startActivity(i);
            }
        });

        Button goAdd = findViewById(R.id.main_button_add);
        goAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Lost.onStatusLogin){
                    startActivity(new Intent(getApplicationContext(), AddLostActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginAppActivity.class));
                }
            }
        });

        ImageButton find = findViewById(R.id.main_button_find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindMoreActivity.class));
            }
        });


    }

    public void doCheckLogin(){
//        mDb.delete(TABLE_NAME, null, null);
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.getCount() == 0) {
            Lost.onStatusLogin = false;
            Toast.makeText(getApplicationContext(), "Database empty" +c.getCount(), Toast.LENGTH_LONG).show();
            c.close();
        }else {
            Lost.onStatusLogin = true;
            Toast.makeText(getApplicationContext(), "Database not empty" +c.getCount(), Toast.LENGTH_LONG).show();
            c.close();
        }
    }



}
