package com.example.aomek.missingpersonsfinder;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_ID;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

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

        Button findButton = findViewById(R.id.button_find);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListLostActivity.class);
                startActivity(i);
            }
        });

        ImageView settingImage = findViewById(R.id.imageview_setting);
        settingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

//        getName();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
//                finish();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPhoneData();
//        setupListView();
    }
    public void loadPhoneData(){
        getUserNameFormDB();
    }

    public void getUserNameFormDB(){
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        String namex = "User";
        if (c.moveToFirst()) {
            namex = c.getString(c.getColumnIndex(COL_NAME));
        }
        c.close();
        TextView userTextView = findViewById(R.id.textview_user);
        userTextView.setText(namex);
    }

}
