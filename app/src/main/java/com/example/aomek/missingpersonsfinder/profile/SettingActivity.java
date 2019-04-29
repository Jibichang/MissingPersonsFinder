package com.example.aomek.missingpersonsfinder.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.adapter.LostListAdapter;
import com.example.aomek.missingpersonsfinder.find.SelecterActivity;
import com.example.aomek.missingpersonsfinder.home.SplashActivity;
import com.example.aomek.missingpersonsfinder.login.LoginAppActivity;
import com.example.aomek.missingpersonsfinder.model.Feedback;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.result.EditLostActivity;
import com.example.aomek.missingpersonsfinder.result.ResultLostActivity;
import com.example.aomek.missingpersonsfinder.result.ScrollingActivity;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_GUEST;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_ID;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_NAME;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PHONE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.COL_PLACE;
import static com.example.aomek.missingpersonsfinder.db.DatabaseHelper.TABLE_NAME;

public class SettingActivity extends AppCompatActivity implements ItemClickListener {
    private static final String TAG = SettingActivity.class.getName();
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostItemList;
    private TextView mNoResult;
    private View mDataLostView;
    private Button memberButton;

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
                if (Lost.onStatusLogin) {
                    startActivity(new Intent(SettingActivity.this, EditProfileActivity.class));
                } else {
                    startActivity(new Intent(SettingActivity.this, LoginAppActivity.class));
                }

            }
        });

        Button fbButton = findViewById(R.id.button_history);
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Lost.onStatusLogin) {
                    startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                } else {
                    startActivity(new Intent(SettingActivity.this, LoginAppActivity.class));
                }

            }
        });

        memberButton = findViewById(R.id.button_member);
        setTextMember();
        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Lost.onStatusLogin) {
                    showDialog();
//                    memberButton.setText("ออกจากระบบ");
                } else {
                    startActivity(new Intent(SettingActivity.this, LoginAppActivity.class));
                }
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

        if (Lost.onStatusLogin) {
            loadData();
        }else {
            showNoResult(true);
        }
        loadPhoneData();
//        setupListView();
    }

    public void loadPhoneData() {
        getUserNameFormDB();
    }

    private void setTextMember() {
        if (Lost.onStatusLogin) {
            memberButton.setText("ออกจากระบบ");
        } else {
            memberButton.setText("เข้าสู่ระบบ");
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(SettingActivity.this)
                .setTitle("ต้องการออกจากระบบใช่หรือไม่")
                .setMessage("เมื่ออกจากระบบผู้ใช้จะไม่สามารถประกาศตามหาบุคคลสูญหาย หรือแจ้งข้อมูลได้")
                .setPositiveButton("ออกจากระบบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogOut();
                    }
                })
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void showDialogDelete(@NonNull String msg) {
        new AlertDialog.Builder(SettingActivity.this)
                .setTitle("ต้องการลบข้อมูลนี้จากระบบใช่หรือไม่")
                .setMessage(msg)
                .setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteLost();
                    }
                })
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void deleteLost() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);

        Lost obLost = new Lost();
        obLost.setId(lostContent.getId());

        Call<Lost> call = retrofit.deleteLost(obLost);
        call.enqueue(new Callback<Lost>() {
            @Override
            public void onResponse(Call<Lost> call, Response<Lost> response) {
                if (response.body() != null) {
                    loadData();
                    Toast.makeText(getApplicationContext(), "ลบแล้ว",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ลบข้อมูลล้มเหลว",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getUserNameFormDB() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);
        String name = "User";
        if (c.moveToFirst()) {
            String gid = c.getString(c.getColumnIndex(COL_GUEST));
            name = c.getString(c.getColumnIndex(COL_NAME));
//            String phone = c.getString(c.getColumnIndex(COL_PHONE));
//            String place = c.getString(c.getColumnIndex(COL_PLACE));
//            String email = c.getString(c.getColumnIndex(COL_EMAIL));
//
            selectableItem.setGuestId(gid);
        }

        TextView userTextView = findViewById(R.id.textview_user);
        userTextView.setText(name);
        c.close();
    }

    private void LogOut() {
        mDb.delete(TABLE_NAME, null, null);
        Lost.onStatusLogin = false;
        selectableItem.setGuestId("");
        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
    }

    private void loadData() {
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
                        String status = lost.get(i).getStatus();
                        String guest = lost.get(i).getGuestId();
                        String image = lost.get(i).getPathImg();

                        Lost item = new Lost(id, pname, fname, lname, gender, age, city, dis, sub, place, height,
                                shape, hairtype, haircolor, upperwaist, upperolor, lowerwaist,
                                lowercolor, skintone, type_id, status, detail_etc, special, guest, date, image);
                        mLostItemList.add(item);
                    }
                    Lost.setLoadDataMyLost(mLostItemList);
                    setupListView();
                    showNoResult(false);

                } else {
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
        final LostListAdapter adapter = new LostListAdapter(
                SettingActivity.this,
                R.layout.list_lost_small,
                Lost.getLoadDataMyLost()
        );
        final SwipeMenuListView  lv = findViewById(R.id.list_my_lost);
        setSwipeListView(lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lost item = Lost.getLoadDataMyLost().get(position);
                lostContent.clearData();

                lostContent.setId(item.getId());
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
                lostContent.setHeight(item.getHeight());

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
                lostContent.setPathImg(item.getPathImg());

                Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
                intent.putExtra("mylost", true);
                startActivity(intent);
            }
        });

        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Lost item = Lost.getLoadDataMyLost().get(position);
                lostContent.setId(item.getId());
                lostContent.setFname(item.getFname());
                if (lostContent.getFname().isEmpty()) { lostContent.setFname("ไม่ระบุชื่อ"); }
                String msg = "ระบบจะทำการลบข้อมูลทั้งหมดของ " + lostContent.getFname() + " เมื่อกดยืนยันจะไม่สามารถดึงข้อมูลกลับมาได้อีก";
                switch (index){
                    case 0:
//                        Toast.makeText(getApplicationContext(), "ลบแล้ว 0 :" + lostContent.getId(), Toast.LENGTH_SHORT).show();
                        showDialogDelete(msg);
                        return false;
                    case 1:
//                        Toast.makeText(getApplicationContext(), "แก้ไขข้อมูล", Toast.LENGTH_SHORT).show();
                        lostContent.clearData();
                        selectableItem.clearData();

                        selectableItem.setId(item.getId());
                        selectableItem.setGuestId(item.getGuestId());

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
                        lostContent.setHeight(item.getHeight());

                        lostContent.setUpperwaist(item.getUpperwaist());
                        lostContent.setUppercolor(item.getUppercolor());
                        lostContent.setLowerwaist(item.getLowerwaist());
                        lostContent.setLowercolor(item.getLowercolor());

                        lostContent.setDetailEtc(item.getDetailEtc());
                        lostContent.setSpecial(item.getSpecial());
                        lostContent.setTypeId(item.getTypeId());
//                        lostContent.setGuestId(item.getGuestId());

                        lostContent.setStatus(item.getStatus());
                        lostContent.setRegDate(item.getRegDate());

                        selectableItem.setPathImg(item.getPathImg());

                        startActivity(new Intent(SettingActivity.this, EditLostActivity.class));
                        return false;
                        default:
                            return false;
                }
            }
        });
    }

    private void setSwipeListView(SwipeMenuListView lv) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(
                        new ColorDrawable(getResources().getColor(R.color.gray)));
                deleteItem.setWidth(220);
                deleteItem.setIcon(R.drawable.icons8bin3);
                deleteItem.setTitle("ลบ");
                deleteItem.setTitleSize(12);
                deleteItem.setTitleColor(getResources().getColor(R.color.red));
                menu.addMenuItem(deleteItem);

                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setBackground(
                        new ColorDrawable(getResources().getColor(R.color.color2)));
                editItem.setWidth(220);
                editItem.setIcon(R.drawable.icons8edit3);
                editItem.setTitle("แก้ไข");
                editItem.setTitleSize(12);
                editItem.setTitleColor(getResources().getColor(R.color.bback));
                menu.addMenuItem(editItem);
            }
        };

        // set creator
        lv.setMenuCreator(creator);
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
