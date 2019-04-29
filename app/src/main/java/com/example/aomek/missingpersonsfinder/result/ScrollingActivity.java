package com.example.aomek.missingpersonsfinder.result;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;
import com.example.aomek.missingpersonsfinder.retrofit.DownloadImageFromInternet;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity implements ItemClickListener {
    private TextView fnameTextView, lnameTextView, ageTextView, genderTextView, cityTextView,
            disTextView, subDisTextView, placeTextView, detailTextView, typeTextView;

    private ImageView skinImg, hairImg, upperImg, lowerImg, imageLost;
    //TODO get image lost from database
    private String tel;
    private Guest obContact = new Guest();
    private FloatingActionButton fab;
    private Button iFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Intent mylost = getIntent();
        Boolean onStatusMylost = mylost.getBooleanExtra("mylost", false);
        fab = (FloatingActionButton) findViewById(R.id.tel);
        iFound = findViewById(R.id.button_i_found);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ข้อมูลบุคคลสูญหาย");
        setSupportActionBar(toolbar);

        getContact();
        setTextView();

        if (onStatusMylost) {
            fab.setEnabled(false);
            iFound.setVisibility(View.VISIBLE);
        }

        iFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveLost();
            }
        });


    }
    private void getContact() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
        Guest obGuest = new Guest();

        obGuest.setGuestId(lostContent.getGuestId());

        Call<Guest> call = retrofit.contactUser(obGuest);
        call.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {
                if (response.body() != null) {
                    obContact = response.body();

                    tel = obContact.getPhone();
//                    Toast.makeText(getApplicationContext(), " ok" + obContact.getPhone(), Toast.LENGTH_LONG).show();
                }else {
                    tel = "";
                }
                telUser();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                tel = "";
                telUser();
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void approveLost() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
        Lost obLost = new Lost();

        obLost.setId(lostContent.getId());

        Call<Lost> call = retrofit.approveLost(obLost);
        call.enqueue(new Callback<Lost>() {
            @Override
            public void onResponse(Call<Lost> call, Response<Lost> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "ยืนยันแล้ว", Toast.LENGTH_LONG).show();
                    iFound.setVisibility(View.GONE);

                    Intent goSetting = new Intent(ScrollingActivity.this, SettingActivity.class);
//                    goSetting.putExtra("approveID", lostContent.getId());
                    startActivity(goSetting);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void telUser(){

        tel = obContact.getPhone();
//        if (tel.isEmpty()){
//            fab.setEnabled(false);
////            fab.setBackgroundColor(getResources().getColor(R.color.gray2));
//        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regexStr = "^[0-9]*$";
//                Toast.makeText(getApplicationContext(), " ok" + tel, Toast.LENGTH_LONG).show();

                if(tel.matches(regexStr) && !tel.isEmpty()){
                    Intent intent = new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:" + tel)
                    );
                    startActivity(intent);
//                    Snackbar.make(view, "ไม่มีข้อมูลการติดต่อทางโทรศัพท์", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "ไม่มีข้อมูลการติดต่อทางโทรศัพท์" + tel, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void setTextView(){
        fnameTextView = findViewById(R.id.text_result_fname);
        lnameTextView = findViewById(R.id.text_result_lname);
        ageTextView = findViewById(R.id.text_result_age);
        genderTextView = findViewById(R.id.text_result_gender);

        cityTextView = findViewById(R.id.text_result_city);
        disTextView = findViewById(R.id.text_result_district);
        subDisTextView = findViewById(R.id.text_result_subdistrict);
        placeTextView = findViewById(R.id.text_result_place);

        detailTextView = findViewById(R.id.text_result_detail);
        typeTextView = findViewById(R.id.text_result_type);
//        statusTextView = findViewById(R.id.text_status);

        skinImg = findViewById(R.id.img_skin);
        hairImg = findViewById(R.id.img_hair_color);
        upperImg = findViewById(R.id.img_upper);
        lowerImg = findViewById(R.id.img_lower);
        imageLost = findViewById(R.id.imageView_lost_table);

        fnameTextView.setText(lostContent.getFname());
        lnameTextView.setText(lostContent.getLname());
        ageTextView.setText(lostContent.getAge());
        genderTextView.setText(lostContent.strGender());

        cityTextView.setText(lostContent.getCity());
        disTextView.setText(lostContent.getDistrict());
        subDisTextView.setText(lostContent.getSubdistrict());
        placeTextView.setText(lostContent.getPlace());

        detailTextView.setText(setStrDetails());
        typeTextView.setText(lostContent.strType());

        setSkintone();
        setULHcolor();
//        if (lostContent.getStatus().equals("1")){
//            statusTextView.setText("พบบุคคลสูญหายแล้ว");
//        }

        String Path = lostContent.getPathImg();
        String imgURL = Lost.getBASE_URL()+ "/plost/api/imgupload/" + Path;

        new DownloadImageFromInternet(imageLost).execute(imgURL);
//        imageLost.setImageBitmap(lostContent.getImage());

    }

    private boolean isHEXcolor(@NonNull String str){
        String hex = str.substring(0, 1);
        return (hex.equals("#"));
    }

    private void setSkintone(){
        String skin = lostContent.getSkintone();
        switch (skin){
            case "T1" :
                skinImg.setImageResource(R.color.T1);
                break;
            case "T2" :
                skinImg.setImageResource(R.color.T2);
                break;
            case "T3" :
                skinImg.setImageResource(R.color.T3);
                break;
            case "T4" :
                skinImg.setImageResource(R.color.T4);
                break;
            case "T5" :
                skinImg.setImageResource(R.color.T5);
                break;
            case "T6" :
                skinImg.setImageResource(R.color.T6);
                break;

            default:
//                skinImg.setImageResource(R.color.white);
                break;
        }
    }

    private void setULHcolor(){
        String upperColor = lostContent.getUppercolor();
        if (!upperColor.isEmpty()){
            if (isHEXcolor(upperColor)){
                upperImg.setBackgroundColor(Color.parseColor(upperColor));
            }
        }


        String lowerColor = lostContent.getLowercolor();
        if (!lowerColor.isEmpty()){
            if (isHEXcolor(lowerColor) && !lowerColor.isEmpty()){
                lowerImg.setBackgroundColor(Color.parseColor(lowerColor));
            }
        }


        String hairColor = lostContent.getHaircolor();
        if (!hairColor.isEmpty()){
            if (isHEXcolor(hairColor)){
                if (hairColor.equals("#D3D3D3")){
                    hairImg.setImageResource(R.drawable.haircolor_bandw);
                } else {
                    hairImg.setBackgroundColor(Color.parseColor(hairColor));
                }
            }
        }

    }

    private String setStrDetails(){
        String msg = "";
        //TODO get text value not code from database
        if (!lostContent.getHairtype().isEmpty()) {
            msg += "ผม : " + lostContent.getHairtype() + "\n";
        }
        if (!lostContent.getShape().isEmpty()) {
            msg += "รูปร่าง : " + lostContent.getShape() + "\n";
        }
        if (!lostContent.getHeight().isEmpty()) {
            msg += "ส่วนสูง : " + lostContent.getHeight() + "\n";
        }
        if (!lostContent.getUpperwaist().isEmpty() && !lostContent.getUpperwaist().equals("U00")) {
            msg += "เสื้อผ้า : " + lostContent.getUpperwaist();
            if (!lostContent.getLowerwaist().isEmpty() && !lostContent.getLowerwaist().equals("L00")) {
                msg += " " + lostContent.getLowerwaist() + "\n";
            }
        }
        if (!lostContent.getLowerwaist().isEmpty() && !lostContent.getLowerwaist().equals("L00")) {
            msg += "เสื้อผ้า : " + lostContent.getLowerwaist() + "\n";
        }
        if (!lostContent.getDetailEtc().isEmpty() && lostContent.getDetailEtc().equals("-")){
            msg += "ลักษณะ : " + lostContent.getDetailEtc() + "\n";
        }
        if (!lostContent.getSpecial().isEmpty() && lostContent.getSpecial().equals("-")){
            msg += "ลักษณะชี้เฉพาะ : " + lostContent.getSpecial();
        }

        return msg;
    }

}
