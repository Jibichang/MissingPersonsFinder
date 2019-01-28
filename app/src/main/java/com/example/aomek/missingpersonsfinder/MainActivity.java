package com.example.aomek.missingpersonsfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.adapter.LostListAdapter;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.model.UserGH;
import com.example.aomek.missingpersonsfinder.model.newMember;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.internal.http.StatusLine;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        setSpinnerType();
        setSpinnerPlace();


        Retrofit restAdapter = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
                .baseUrl("https://269b2268.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
//        Call call = retrofit.getUser("jibichang");

//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                if (response.body() != null) {
//                    UserGH userx = (UserGH) response.body();
//                    Toast.makeText(getApplicationContext(), "Name : " + userx.getLogin(),Toast.LENGTH_LONG).show();
//                }
//            }
//            //Toast.makeText(getApplicationContext(), "Name : " + userx.getEmail() +"user "+ userx.getUsername(),Toast.LENGTH_LONG).show();
//            //
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//            }
//        });
        Call<LostModel> call = retrofit.getLostModel();
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostItemList = new ArrayList<>();
//                    Lost lost = (Lost) response.body();
//                    Toast.makeText(getApplicationContext(), "OK" + lost.get(),Toast.LENGTH_LONG).show();
                    for (int i = 0; i < lost.size(); i++) {
                        String fname = lost.get(i).getFname();
                        String lname = lost.get(i).getLname();
                        String detail = lost.get(i).getDetail();
                        String date = lost.get(i).getRegDate();

                        Lost item = new Lost(fname, lname, detail, date);
                        mLostItemList.add(item);
                    }
                    setupListView();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure",Toast.LENGTH_LONG).show();
            }
        });


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

    public void setSpinnerType(){
        Spinner typeSpinner = findViewById(R.id.spinner_type);
        Lost.setListType();
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListtype());
        typeSpinner.setAdapter(adapterType);
    }
    public void setSpinnerPlace(){
        Spinner placeSpinner = findViewById(R.id.spinner_place);
//        Lost.setListplace();

        ArrayAdapter<String> adapterPlace = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Lost.getListplace());
        placeSpinner.setAdapter(adapterPlace);
    }

    private void setupListView() {
        LostListAdapter adapter = new LostListAdapter(
                MainActivity.this,
                R.layout.list_lost,
                mLostItemList
        );
        ListView lv = findViewById(R.id.listview_lost);
        lv.setAdapter(adapter);
    }


//    public class HttpAsyncTask extends AsyncTask<Void, Void, newMember> {
//        @Override
//        protected newMember doInBackground(Void... params) {
//
//            RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint("http://10.0.2.2/KrooKlon/api/")
//                    .build();
//
//            Retrofit retrofit = restAdapter.create(Retrofit.class);
//            newMember shot = retrofit.getLost();
//
//            return shot;
//        }
//
//        @Override
//        protected void onPostExecute(newMember shot) {
//
//            Toast.makeText(getApplicationContext(),
//                    "Name : " + shot.getEmail(),
//                    Toast.LENGTH_LONG).show();
//            super.onPostExecute(shot);
//        }
//    }

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
