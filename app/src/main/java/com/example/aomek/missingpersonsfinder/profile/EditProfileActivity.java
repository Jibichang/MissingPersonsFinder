package com.example.aomek.missingpersonsfinder.profile;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mHelper = new DatabaseHelper(EditProfileActivity.this);
        mDb = mHelper.getWritableDatabase();
        defaultUser();

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditProfileActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    public void defaultUser(){
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(COL_GUEST));
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
        }
        c.close();
    }
}
