package com.example.aomek.missingpersonsfinder.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.find.FindMoreActivity;
import com.example.aomek.missingpersonsfinder.find.FoundLostActivity;
import com.example.aomek.missingpersonsfinder.find.SelecterActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.result.ResultLostActivity;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_GUEST;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class MainMenuActivity extends AppCompatActivity implements ItemClickListener {
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
                if(Lost.onStatusLogin){
                    startActivity(new Intent(getApplicationContext(), FoundLostActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginAppActivity.class));
                }
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

        Button mainfind = findViewById(R.id.main_button_find);
        mainfind.setOnClickListener(new View.OnClickListener() {
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
//            Toast.makeText(getApplicationContext(), "Database empty" +c.getCount(), Toast.LENGTH_LONG).show();
            c.close();
        }else {
            if (c.moveToFirst()){
                String id = c.getString(c.getColumnIndex(COL_GUEST));
                selectableItem.setGuestId(id);
            }
            Lost.onStatusLogin = true;
//            Toast.makeText(getApplicationContext(), "Database not empty" +c.getCount(), Toast.LENGTH_LONG).show();
            c.close();
        }
    }

}
