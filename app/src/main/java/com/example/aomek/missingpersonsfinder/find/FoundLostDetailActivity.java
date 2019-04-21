package com.example.aomek.missingpersonsfinder.find;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.home.MainMenuActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FoundLostDetailActivity extends AppCompatActivity implements ItemClickListener {
    EditText placeEdittext;

    Spinner ageSpinner, genderSpinner, placeSpinner, typeSpinner,  disSpinner, subSpinner;

    ArrayList<String> mlistDistrict = new ArrayList<>();
    ArrayList<String> mlistSubDistrict = new ArrayList<>();

    ImageButton uploadImage;
    ImageView pickImage;
    Intent intentLost;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_lost_detail);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_found);
//        navigation.setFocusable();

        setSpinner();
        setSpinnerDefaultCity();
        selectableItem.setPathImg("-");

        placeEdittext = findViewById(R.id.edittext_place_detail);
        uploadImage = findViewById(R.id.imageButton_upload_img);
        pickImage = findViewById(R.id.imageView_upload_result);

        genderSpinner   .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = genderSpinner.getSelectedItem().toString();
                switch (gender){
                    case "หญิง":
                        gender = "F";
                        break;
                    case "ชาย":
                        gender = "M";
                        break;
                }
                selectableItem.setGender(gender);
//                intentLost.putExtra("gender", gender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectableItem.setGender("F");
            }
        });
        ageSpinner      .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String age = ageSpinner.getSelectedItem().toString();
//                intentLost.putExtra("age", age);
                selectableItem.setAge(age);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        placeSpinner    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSpinnerDefaultCity();
                String city = placeSpinner.getSelectedItem().toString();
//                intentLost.putExtra("city", city);
                selectableItem.setCity(city);
//                Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();
                loadDistrict(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        disSpinner      .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subSpinner.setEnabled(false);
                String district = disSpinner.getSelectedItem().toString();
//                intentLost.putExtra("district", district);
                selectableItem.setDistrict(district);
//                Toast.makeText(getApplicationContext(), text,Toast.LENGTH_LONG).show();
                loadSubDistrict(district);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        subSpinner      .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String subdistrict = subSpinner.getSelectedItem().toString();
//                intentLost.putExtra("subdistrict", subdistrict);
                selectableItem.setSubdistrict(subdistrict);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        typeSpinner     .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int typeID = typeSpinner.getSelectedItemPosition();
//                intentLost.putExtra("type_id", typeID);
//                Toast.makeText(getApplicationContext(),"id : ",Toast.LENGTH_LONG).show();
                selectableItem.setTypeId(String.valueOf(typeID));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button nextButtonFound = findViewById(R.id.button_next);
        nextButtonFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Lost.onStatusLogin){
                    String place = placeEdittext.getText().toString();
                    selectableItem.setPlace(place);

                    Intent intentLost = new Intent(getApplicationContext(), SelecterActivity.class);
                    startActivity(intentLost);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginAppActivity.class));
                }

            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(imageIntent.ACTION_GET_CONTENT);
                startActivityForResult(imageIntent, IMG_REQUEST);

//                Toast.makeText(getApplicationContext(), "img " + IMG_REQUEST,
//                                    Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                pickImage.setImageBitmap(bitmap);
                pickImage.setVisibility(View.VISIBLE);
                imagetoString();
//                Toast.makeText(getApplicationContext(), "ok"+selectableItem.getImage(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void imagetoString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imgByte = byteArrayOutputStream.toByteArray();
        selectableItem.setPathImg(Base64.encodeToString(imgByte, Base64.DEFAULT));
    }

    public void setSpinnerDefaultCity(){
        mlistDistrict.add("-");
        mlistSubDistrict.add("-");
        setSpinnerDistrict();
        setSpinnerSubDistrict();
        disSpinner.setEnabled(false);
        subSpinner.setEnabled(false);
    }

    public void loadDistrict(String input){
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
        Lost obLost = new Lost();
        obLost.setDistrict(input);

        Call<LostModel> call = retrofit.searchDistrict(obLost);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();

                    mlistDistrict = new ArrayList<>();
                    mlistDistrict.clear();
                    mlistDistrict.add("-");
                    for (int i = 0; i < lost.size(); i++) {
                        String distinct = lost.get(i).getDistrict();
                        mlistDistrict.add(distinct);
                    }
                    setSpinnerDistrict();
                    disSpinner.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setSpinnerDistrict(){
        disSpinner = findViewById(R.id.spinner_place2);
        ArrayAdapter<String> adapterPlace2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mlistDistrict);
        disSpinner.setAdapter(adapterPlace2);
    }

    public void loadSubDistrict(String input){
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
        Lost obLost2 = new Lost();
        obLost2.setSubdistrict(input);

        Call<LostModel> call = retrofit.searchSubDistrict(obLost2);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {

                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();

                    mlistSubDistrict = new ArrayList<>();
                    mlistSubDistrict.clear();
                    mlistSubDistrict.add("-");
                    for (int i = 0; i < lost.size(); i++) {
                        String subDistrict = lost.get(i).getSubdistrict();
                        mlistSubDistrict.add(subDistrict);
                    }
                    setSpinnerSubDistrict();
                    subSpinner.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setSpinnerSubDistrict(){
        subSpinner = findViewById(R.id.spinner_place3);
        ArrayAdapter<String> adapterPlace3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mlistSubDistrict);
        subSpinner.setAdapter(adapterPlace3);
    }

    public void setSpinner(){
        ageSpinner = findViewById(R.id.spinner_age);
        Lost.setListAge();
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListAge());
        ageSpinner.setAdapter(adapterAge);

        genderSpinner = findViewById(R.id.spinner_gender);
        Lost.setListGender();
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Lost.getListGender());
        genderSpinner.setAdapter(adapterGender);

        placeSpinner = findViewById(R.id.spinner_place1);
//        Lost.setListplace();
        ArrayAdapter<String> adapterPlace = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Lost.getListplace());
        placeSpinner.setAdapter(adapterPlace);

        typeSpinner = findViewById(R.id.spinner_type);
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
                            startActivity( new Intent(getApplicationContext(), MainActivity.class));
                            return true;
                        case R.id.navigation_found:
//                            Intent i = new Intent(getApplicationContext(), FoundLostActivity.class);
//                            startActivity(new Intent(getApplicationContext(), FoundLostActivity.class));
                            return true;
                        case R.id.navigation_add:
                            Intent k = new Intent(getApplicationContext(), AddLostActivity.class);
                            startActivity(k);
                            return true;
                        case R.id.navigation_profile:
                            Intent j = new Intent(getApplicationContext(), SettingActivity.class);
                            startActivity(j);
                            return true;
                    }
                    return false;
                }
            };


}
