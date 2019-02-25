package com.example.aomek.missingpersonsfinder.profile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.find.FoundLostActivity;
import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.find.SelecterActivity;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.Member;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_EMAIL;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_ID;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PHONE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PLACE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mHelper = new DatabaseHelper(SettingActivity.this);
        mDb = mHelper.getWritableDatabase();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_profile);

        Button editButton = findViewById(R.id.button_edit_pf);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(SettingActivity.this, EditProfileActivity.class));


            }
        });

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
        String name = "User";
        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            name = c.getString(c.getColumnIndex(COL_NAME));
            String phone = c.getString(c.getColumnIndex(COL_PHONE));
            String place = c.getString(c.getColumnIndex(COL_PLACE));
            String email = c.getString(c.getColumnIndex(COL_EMAIL));
            // send to instance viable
            Member item = new Member(id, name, email, place, phone);
        }
        c.close();
        TextView userTextView = findViewById(R.id.textview_user);
        userTextView.setText(name);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent i = new Intent(SettingActivity.this, MainActivity.class);
                            startActivity(i);
                            return true;
                        case R.id.navigation_found:
                            Intent j = new Intent(SettingActivity.this, FoundLostActivity.class);
                            startActivity(j);
                            return true;
                        case R.id.navigation_add:
                            Intent k = new Intent(SettingActivity.this, AddLostActivity.class);
                            startActivity(k);
                            return true;
                        case R.id.navigation_profile:
                            return true;
                    }
                    return false;
                }
            };
}
