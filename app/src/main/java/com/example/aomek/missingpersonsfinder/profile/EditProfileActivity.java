package com.example.aomek.missingpersonsfinder.profile;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_EMAIL;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_GUEST;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PHONE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PLACE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class EditProfileActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    private EditText nameEditText;
    private EditText placeEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText passEditText;

    private String GUEST_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mHelper = new DatabaseHelper(EditProfileActivity.this);
        mDb = mHelper.getWritableDatabase();

        if (Lost.onStatusLogin) {
            defaultUser();
        } else {
            Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_LONG).show();
        }

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this, SettingActivity.class));
            }
        });

        Button saveEditProfile = findViewById(R.id.button_save);
        saveEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String pass = passEditText.getText().toString();
                String place = placeEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                if (pass == "" || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่าน", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสผ่าน"+name, Toast.LENGTH_LONG).show();
                    updateUser(GUEST_ID, name, pass, place, phone);
                }

            }
        });
    }

    public void updateUser(String guest_id, String name, String password, String place, String phone) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

//        password = password.trim();
        place = place.trim();
        phone = phone.trim();

        Guest obGuest = new Guest(guest_id, name, password, place, phone);
        Call<Guest> call = retrofit.updateUser(obGuest);
        call.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "บันทึกการเปลี่ยนแปลงแล้ว", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditProfileActivity.this, SettingActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกรหัสให้ถูกต้อง", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "แก้ไขข้อมูลล้มเหลว", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void defaultUser() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            GUEST_ID = c.getString(c.getColumnIndex(COL_GUEST));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String phone = c.getString(c.getColumnIndex(COL_PHONE));
            String place = c.getString(c.getColumnIndex(COL_PLACE));
            String email = c.getString(c.getColumnIndex(COL_EMAIL));

            nameEditText = findViewById(R.id.edittext_edit_user);
            nameEditText.setText(name);

            placeEditText = findViewById(R.id.edittext_edit_place);
            placeEditText.setText(place);

            phoneEditText = findViewById(R.id.edittext_edit_tel);
            phoneEditText.setText(phone);

            emailEditText = findViewById(R.id.edittext_edit_email);
            emailEditText.setText(email);

            passEditText = findViewById(R.id.edittext_edit_password);
        }
        c.close();
    }
}
