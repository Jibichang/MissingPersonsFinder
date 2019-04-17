package com.example.aomek.missingpersonsfinder.home;

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
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_GUEST;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class SplashActivity extends AppCompatActivity implements ItemClickListener {
    private List<Lost> mLostItemList;
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Set Database
        mHelper = new DatabaseHelper(SplashActivity.this);
        mDb = mHelper.getWritableDatabase();

        getUserFormDB();
        loadData();

//        CountDownTimer countDownTimer = new CountDownTimer(3 * 1000, 1000) {
//            public void onTick(long millisUntilFinished) {
//
//            }
//            public void onFinish() {
////                        mAddView.setVisibility(View.VISIBLE);
//                Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
//                startActivity(i);
//            }
//        };
//        countDownTimer.start();
        // set place list
        Lost.setListplace();
    }

    private void loadData(){
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
        Call<LostModel> call = retrofit.getLostModel();
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostItemList = new ArrayList<>();
                    for (int i = 0; i < 15; i++) {
                        String id = lost.get(i).getId();
                        String pname = lost.get(i).getPname();
                        String fname = lost.get(i).getFname();
                        String lname = lost.get(i).getLname();
                        String gender = lost.get(i).getGender();
                        String age = lost.get(i).getAge();

                        String place = lost.get(i).getPlace();
                        String sub = lost.get(i).getSubdistrict();
                        String dis = lost.get(i).getDistrict();
                        String city = lost.get(i).getCity();

                        String height = lost.get(i).getHeight();
                        String shape = lost.get(i).getShape();
                        String hairtype = lost.get(i).getHairtype();
                        String haircolor = lost.get(i).getHaircolor();

                        String upperwaist = lost.get(i).getUpperwaist();
                        String upperolor = lost.get(i).getUppercolor();
                        String lowerwaist = lost.get(i).getLowerwaist();
                        String lowercolor = lost.get(i).getLowercolor();

                        String skintone = lost.get(i).getSkintone();
                        String type_id = lost.get(i).getTypeId();
                        String detail_etc = lost.get(i).getDetailEtc();
                        String special = lost.get(i).getSpecial();

                        String date = lost.get(i).getRegDate();
                        String status = lost.get(i).getRegDate();
                        String guest = lost.get(i).getGuestId();

                        Lost item = new Lost(id, pname, fname, lname, gender, age,city, dis, sub, place, height,
                                shape, hairtype, haircolor, upperwaist, upperolor, lowerwaist,
                                lowercolor, skintone, type_id, status, detail_etc, special, guest, date);
                        mLostItemList.add(item);

                    }
                    Lost.setLoadDataMain(mLostItemList);
                    Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
                    startActivity(i);

                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getUserFormDB() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.getCount() > 0){
            if (c.moveToFirst()) {
                String gid = c.getString(c.getColumnIndex(COL_GUEST));
                selectableItem.setGuestId(gid);
            }
        }else {
            Lost.onStatusLogin = false;
        }
        c.close();
    }
}
