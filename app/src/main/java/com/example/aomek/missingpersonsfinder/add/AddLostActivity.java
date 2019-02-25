package com.example.aomek.missingpersonsfinder.add;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.aomek.missingpersonsfinder.find.FoundLostActivity;
import com.example.aomek.missingpersonsfinder.find.SelecterActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;

public class AddLostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost);

        setSpinnerAge();
        setSpinnerGender();
        setSpinnerPlace();
        setSpinnerType();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_add);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Lost.onStatusLogin){
                    startActivity(new Intent(AddLostActivity.this, SelecterActivity.class));
                } else {
                    startActivity(new Intent(AddLostActivity.this, LoginAppActivity.class));
                }

            }
        });


    }
    public void setSpinnerAge(){
        Spinner ageSpinner = findViewById(R.id.spinner_age);
        Lost.setListAge();
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListAge());
        ageSpinner.setAdapter(adapterAge);
    }

    public void setSpinnerGender(){
        Spinner genderSpinner = findViewById(R.id.spinner_gender);
        Lost.setListGender();
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListGender());
        genderSpinner.setAdapter(adapterGender);
    }

    public void setSpinnerPlace(){
        Spinner placeSpinner = findViewById(R.id.spinner_place1);
//        Lost.setListplace();
        ArrayAdapter<String> adapterPlace = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Lost.getListplace());
        placeSpinner.setAdapter(adapterPlace);
    }

    public void setSpinnerType(){
        Spinner typeSpinner = findViewById(R.id.spinner_type);
        Lost.setListType();
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListtype());
        typeSpinner.setAdapter(adapterType);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent i = new Intent(AddLostActivity.this, MainActivity.class);
                            startActivity(i);
                            return true;
                        case R.id.navigation_found:
                            Intent j = new Intent(AddLostActivity.this, FoundLostActivity.class);
                            startActivity(j);
                            return true;
                        case R.id.navigation_add:
                            return true;
                        case R.id.navigation_profile:
                            Intent k = new Intent(AddLostActivity.this, SettingActivity.class);
                            startActivity(k);
                            return true;
                    }
                    return false;
                }
            };


}
