package com.example.aomek.missingpersonsfinder.result;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.adapter.LostListAdapter;
import com.example.aomek.missingpersonsfinder.adapter.SwipeDismissListViewTouchListener;
import com.example.aomek.missingpersonsfinder.add.AddConfirmActivity;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Feedback;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultLostActivity extends AppCompatActivity implements ItemClickListener {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostResultItemList;
    private View mDataLostView;
    private View mAddView;
    private View mProgressView;
//    private LostListAdapter adapterLost;
//    private SwipeMenuListView lv;
//    private String strPOST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_lost);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                selectableItem.clearData();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


//        final Lost lostItem = mLostItemList.get(0);
        mDataLostView = findViewById(R.id.list_result);
        mAddView = findViewById(R.id.layout_add);

        mProgressView = findViewById(R.id.lost_progress);

        CountDownTimer countDownTimer = new CountDownTimer(5 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mAddView.setVisibility(View.GONE);
            }

            public void onFinish() {
                mAddView.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        Button addAfterFind = findViewById(R.id.button_add_after_find);
        addAfterFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Lost.onStatusFound){
                    selectableItem.setStatus("1");
                    startActivity(new Intent(getApplicationContext(), AddConfirmActivity.class));
                }else {
                    selectableItem.setStatus("0");
                    startActivity(new Intent(getApplicationContext(), AddConfirmActivity.class));
                }
            }
        });
//        Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
//
//        intent.putExtra("fname", lostItem.getFname());
//        intent.putExtra("lname", lostItem.getLname());
//
//        intent.putExtra("age", lostItem.getAge());
//        intent.putExtra("gender", lostItem.getGender());
//
//        intent.putExtra("city", lostItem.getCity());
//        intent.putExtra("district", lostItem.getDistrict());
//        intent.putExtra("subdistrict", lostItem.getSubdistrict());
//        intent.putExtra("place", lostItem.getPlace());
//
//        intent.putExtra("hairtype", lostItem.getHairtype());
//        intent.putExtra("haircolor", lostItem.getHaircolor());
//
//        intent.putExtra("upperwaist", lostItem.getUpperwaist());
//        intent.putExtra("uppercolor", lostItem.getUppercolor());
//        intent.putExtra("lowerwaist", lostItem.getLowerwaist());
//        intent.putExtra("lowercolor", lostItem.getLowercolor());
//
//        intent.putExtra("detail_etc", lostItem.getDetailEtc());
//        intent.putExtra("special", lostItem.getSpecial());
//        intent.putExtra("type_id", lostItem.getTypeId());
//        intent.putExtra("date", lostItem.getRegDate());
//
//
//        String fname = intent.getStringExtra("fname");
//        String lname = intent.getStringExtra("lname");
//
//        String age = intent.getStringExtra("age");
//        String gender = intent.getStringExtra("gender");
//
//        String city = intent.getStringExtra("city");
//        String district = intent.getStringExtra("district");
//        String subdistrict = intent.getStringExtra("subdistrict");
//        String place = intent.getStringExtra("place");
//
//        String hairtype = intent.getStringExtra("hairtype");
//        String haircolor = intent.getStringExtra("haircolor");
//
//        String upperwaist = intent.getStringExtra("upperwaist");
//        String uppercolor = intent.getStringExtra("uppercolor");
//        String lowerwaist = intent.getStringExtra("lowerwaist");
//        String lowercolor = intent.getStringExtra("lowercolor");
//
//        String detail_etc = intent.getStringExtra("detail_etc");
//        String special = intent.getStringExtra("special");
//        String type_id = intent.getStringExtra("type_id");
//        String date = intent.getStringExtra("date");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
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

        searchLostData();
    }

    private void searchLostData() {
        showProgress(true);
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
//                    Toast.makeText(getApplicationContext(), selectableItem.getFname()+selectableItem.getLname()+
//                selectableItem.getGender()+selectableItem.getAge()+selectableItem.getCity()+
//                selectableItem.getDistrict()+selectableItem.getSubdistrict()+selectableItem.getPlace()+
//                selectableItem.getHeight()+selectableItem.getShape()+selectableItem.getHairtype()+
//                selectableItem.getHaircolor()+selectableItem.getUpperwaist()+selectableItem.getUppercolor()+
//                selectableItem.getLowerwaist()+selectableItem.getLowercolor()+
//                selectableItem.getSkintone()+selectableItem.getTypeId()+
//                "0"+selectableItem.getDetailEtc()+selectableItem.getSpecial(),Toast.LENGTH_LONG).show();
        if (!Lost.onStatusLogin){
            selectableItem.setGuestId("");
        }

        Lost obLost = new Lost(
                ""+selectableItem.getFname(),
                ""+selectableItem.getLname(),
                ""+selectableItem.getGender(),
                ""+selectableItem.getAge(),
                ""+selectableItem.getCity(),
                ""+selectableItem.getDistrict(),
                ""+selectableItem.getSubdistrict(),
                ""+selectableItem.getPlace(),
                ""+selectableItem.getHeight(),
                ""+selectableItem.getShape(),
                ""+selectableItem.getHairtype(),
                ""+selectableItem.getHaircolor(),
                ""+selectableItem.getUpperwaist(),
                ""+selectableItem.getUppercolor(),
                ""+selectableItem.getLowerwaist(),
                ""+selectableItem.getLowercolor(),
                ""+selectableItem.getSkintone(),
                ""+selectableItem.getTypeId(),
                "0",
                ""+selectableItem.getDetailEtc(),
                ""+selectableItem.getSpecial(),
                selectableItem.getMode(),
                ""+selectableItem.getGuestId()

        );

        Call<LostModel> call = retrofit.searchLost(obLost);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostResultItemList = new ArrayList<>();
//                    mLostItemList.clear();
                    for (int i = 0; i < lost.size(); i++) {
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
                        String status = lost.get(i).getStatus();
                        String guest = lost.get(i).getGuestId();

                        Lost item = new Lost(id, pname, fname, lname, gender, age, city, dis, sub, place, height,
                                shape, hairtype, haircolor, upperwaist, upperolor, lowerwaist,
                                lowercolor, skintone, type_id, status, detail_etc, special, guest, date);
                        mLostResultItemList.add(item);
                    }
//                    Toast.makeText(getApplicationContext(), " ok", Toast.LENGTH_LONG).show();
                    showProgress(false);
                    setupListView();

                } else {
                    showProgress(false);
//                    Toast.makeText(getApplicationContext(), " no" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showProgress(false);
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupListView() {
        final LostListAdapter adapterLost = new LostListAdapter(
                ResultLostActivity.this,
                R.layout.list_lost,
                mLostResultItemList
        );
        final SwipeMenuListView lv = findViewById(R.id.list_result);
        setSwipeListView(lv);
        lv.setAdapter(adapterLost);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lost item = mLostResultItemList.get(position);

                lostContent.setFname(item.getFname());
                lostContent.setLname(item.getLname());
                lostContent.setAge(item.getAge());
                lostContent.setGender(item.getGender());

                lostContent.setCity(item.getCity());
                lostContent.setDistrict(item.getDistrict());
                lostContent.setSubdistrict(item.getSubdistrict());
                lostContent.setPlace(item.getPlace());

                lostContent.setShape(item.getShape());
                lostContent.setHairtype(item.getHairtype());
                lostContent.setHaircolor(item.getHaircolor());
                lostContent.setSkintone(item.getSkintone());

                lostContent.setUpperwaist(item.getUpperwaist());
                lostContent.setUppercolor(item.getUppercolor());
                lostContent.setLowerwaist(item.getLowerwaist());
                lostContent.setLowercolor(item.getLowercolor());

                lostContent.setDetailEtc(item.getDetailEtc());
                lostContent.setSpecial(item.getSpecial());
                lostContent.setTypeId(item.getTypeId());
                lostContent.setGuestId(item.getGuestId());

                lostContent.setStatus(item.getStatus());
                lostContent.setRegDate(item.getRegDate());

                Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
//                intent.putExtra("stringLost", item.toString());
                startActivity(intent);
            }
        });
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (Lost.onStatusLogin){
                    //                Toast.makeText(getApplicationContext(), "ลบแล้ว" + position + " : "+ index, Toast.LENGTH_SHORT).show();
                    String id =  mLostResultItemList.get(position).getId();
                    String gid = selectableItem.getGuestId();

                    addFeedback(gid, id);
                    Toast.makeText(getApplicationContext(), "ลบแล้ว", Toast.LENGTH_SHORT).show();

                    mLostResultItemList.remove(position);
                    adapterLost.notifyDataSetChanged();
                    lv.invalidateViews();
                    lv.setAdapter(adapterLost);
                    return false;
                }else {
                    Toast.makeText(getApplicationContext(), "กรุณาเข้าสู่ระบบ", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
        });
    }

    private void setSwipeListView(SwipeMenuListView lv) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(
                        new ColorDrawable(getResources().getColor(R.color.gray)));
                // set item width
                deleteItem.setWidth(300);
                // set a icon
                deleteItem.setIcon(R.drawable.icons8bin);
                // set item title
                deleteItem.setTitle("ไม่แสดงอีก");
                // set item title fontsize
                deleteItem.setTitleSize(14);
                // set item title font color
                deleteItem.setTitleColor(getResources().getColor(R.color.red));
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        lv.setMenuCreator(creator);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mDataLostView.setVisibility(show ? View.GONE : View.VISIBLE);
            mDataLostView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mDataLostView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mDataLostView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void addFeedback(String gID, String ID) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

        Feedback obFB = new Feedback(gID, ID);

        Call<Feedback> call = retrofit.addFeedback(obFB);
        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                if (response.code() == 201) {
                    Feedback feedback = response.body();
//                    Toast.makeText(getApplicationContext(), " yes" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "เก็บข้อมูลล้มเหลว", Toast.LENGTH_LONG).show();
            }
        });
    }



}
