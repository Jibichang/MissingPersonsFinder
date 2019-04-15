package com.example.aomek.missingpersonsfinder.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;

import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.adapter.LostListAdapter;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.find.FoundLostActivity;
import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.add.AddLostActivity;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Member;

import java.util.ArrayList;
import java.util.List;

import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_EMAIL;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_ID;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PHONE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PLACE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class SettingActivity extends AppCompatActivity implements ItemClickListener {
    private static final String TAG = MainActivity.class.getName();
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostItemList;
    private TextView mNoResult;
    private View mDataLostView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mNoResult = findViewById(R.id.textView_no_result);
        mDataLostView = findViewById(R.id.list_my_lost);

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

        Button memberButton = findViewById(R.id.button_member);
        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, LoginAppActivity.class));
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
        loadData();
//        setupListView();
    }
    public void loadPhoneData(){
        getUserNameFormDB();
    }
    public void getUserNameFormDB(){
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
//        String name = "User";
        if (c.moveToFirst()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String phone = c.getString(c.getColumnIndex(COL_PHONE));
            String place = c.getString(c.getColumnIndex(COL_PLACE));
            String email = c.getString(c.getColumnIndex(COL_EMAIL));
            // send to instance viable
            Member item = new Member(id, name, email, place, phone);
            TextView userTextView = findViewById(R.id.textview_user);
            userTextView.setText(name);
        }
        c.close();
    }

    private void loadData(){
        showNoResult(false);
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

        Guest obGuest = new Guest();
        obGuest.setGuestId(selectableItem.getGuestId());
        Call<LostModel> call = retrofit.searchGuest(obGuest);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostItemList = new ArrayList<>();
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
                        String status = lost.get(i).getRegDate();

                        Lost item = new Lost(id, pname, fname, lname, gender, age,city, dis, sub, place, height,
                                shape, hairtype, haircolor, upperwaist, upperolor, lowerwaist,
                                lowercolor, skintone, type_id, status, detail_etc, special,date);
                        mLostItemList.add(item);
                    }
                    Lost.setLoadDataMyLost(mLostItemList);
                    setupListView();
                    showNoResult(false);

                }else {
                    showNoResult(true);
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                showNoResult(true);
//                Toast.makeText(getApplicationContext(), "Failure",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setupListView() {
        LostListAdapter adapter = new LostListAdapter(
                SettingActivity.this,
                R.layout.list_lost_small,
                Lost.getLoadDataMyLost()
        );
        ListView lv = findViewById(R.id.list_my_lost);
        lv.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showNoResult(final boolean show) {
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

            mNoResult.setVisibility(show ? View.VISIBLE : View.GONE);
            mNoResult.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mNoResult.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mNoResult.setVisibility(show ? View.VISIBLE : View.GONE);
            mDataLostView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
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
