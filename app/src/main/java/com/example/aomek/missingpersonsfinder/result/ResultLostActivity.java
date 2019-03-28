package com.example.aomek.missingpersonsfinder.result;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultLostActivity extends AppCompatActivity implements ItemClickListener {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostItemList;
    private View mDataLostView;
    private View mAddView;
    private View mProgressView;
    private LostListAdapter adapterLost;
    private SwipeMenuListView lv;
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
//                mLostItemList.clear();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

//        Toast.makeText(getApplicationContext(),fname,Toast.LENGTH_LONG).show();

//        final Lost lostItem = mLostItemList.get(0);
        mDataLostView = findViewById(R.id.list_result);
        mAddView = findViewById(R.id.layout_add);

        mProgressView = findViewById(R.id.lost_progress);

        CountDownTimer countDownTimer = new CountDownTimer(20 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mAddView.setVisibility(View.GONE);
            }
            public void onFinish() {
                mAddView.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
        searchLostData();

//        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(
//                    final int position,
//                    SwipeMenu menu,
//                    int index) {
//                final Dialog dialog = new Dialog(getApplicationContext());
//                dialog.getWindow();
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.getWindow().setBackgroundDrawable(
//                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                dialog.setContentView(R.layout.dialog_del);
//                TextView dialog_title = (TextView)dialog.findViewById(R.id.dialog_title);
////                dialog_title.setText(String.valueOf("Delete List"));
//
////                TextView dialog_description = (TextView)dialog.findViewById(R.id.dialog_description);
////                dialog_description.setText(String.valueOf("You want delete this"+fname.get(position)+"?"));
//                Button buttonCancel = (Button)dialog.findViewById(R.id.button_cancle_del);
//                buttonCancel.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        dialog.cancel();
//                    }
//                });
//
//                Button buttonOK = (Button)dialog.findViewById(R.id.button_del);
//                buttonOK.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        //remove data in list
////                        name.remove(position);
////                        photo.remove(position);
//                        adapterLost.notifyDataSetChanged();  //updata listview
//
//                        dialog.cancel();
//                    }
//                });
//
//                dialog.show();
//
//                return false;
//            }
//        });

//        SwipeDismissListViewTouchListener touchListener =
//                new SwipeDismissListViewTouchListener(
//                        mDataLostView,
//                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
//                            @Override
//                            public boolean canDismiss(int position) {
//                                return true;
//                            }
//
//                            @Override
//                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
//                                for (int position : reverseSortedPositions) {
//
//                                    mLostItemList.remove(position);
////                                    lv.notifyDataSetChanged();
//
//                                }
//
//                            }
//                        });
//        mDataLostView.setOnTouchListener(touchListener);

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

    private void searchLostData(){
        showProgress(true);
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Lost.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofit = restAdapter.create(RetrofitAPI.class);
//        try {
//            JSONObject paramObject = new JSONObject();
////            paramObject.put("fname", "ธนโชติ");
//            paramObject.put("fname", "ธนโชติ");
//            paramObject.put("lname", "บุญ");
//            paramObject.put("gender", "M");
//            paramObject.put("city", "นครสวรรค์");
//            paramObject.put("height", "0");
////            paramObject.put("weight", "0");
//            paramObject.put("shape", "0");
//            paramObject.put("hairtype", "0");
//            paramObject.put("haircolor", "0");
//            paramObject.put("skintone", "0");
//            paramObject.put("type_id", "0");
//            paramObject.put("status", "0");
//            paramObject.put("detail_etc", " ");
////            Toast.makeText(getApplicationContext(), selectableItem.getFname(),selectableItem.getLname(),
//                selectableItem.getGender(),selectableItem.getAge(),selectableItem.getCity(),
//                selectableItem.getDistrict(),selectableItem.getSubdistrict(),selectableItem.getPlace(),
//                selectableItem.getHeight(),selectableItem.getShape(),selectableItem.getHairtype(),
//                selectableItem.getHaircolor(),selectableItem.getUpperrwaist(),selectableItem.getUppercolor(),
//                selectableItem.getLowerwaist(),selectableItem.getLowercolor(),
//                selectableItem.getSkintone(),selectableItem.getTypeId(),
//                "0",selectableItem.getDetailEtc(),selectableItem.getSpecial(),Toast.LENGTH_LONG).show();
//            strPOST = paramObject.toString();
//            TextView text = findViewById(R.id.textJSON);
//            text.setText(strPOST);
//            obLost = new Lost("ธนโชติ","บุญ","M","นครสวรรค์"
//                    ,"0","0","0","0","0",
//                    "0","0","0"," ");
////
//           //new Gson().toJson(obLost)
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//
//                    Toast.makeText(getApplicationContext(), selectableItem.getFname()+selectableItem.getLname()+
//                selectableItem.getGender()+selectableItem.getAge()+selectableItem.getCity()+
//                selectableItem.getDistrict()+selectableItem.getSubdistrict()+selectableItem.getPlace()+
//                selectableItem.getHeight()+selectableItem.getShape()+selectableItem.getHairtype()+
//                selectableItem.getHaircolor()+selectableItem.getUpperwaist()+selectableItem.getUppercolor()+
//                selectableItem.getLowerwaist()+selectableItem.getLowercolor()+
//                selectableItem.getSkintone()+selectableItem.getTypeId()+
//                "0"+selectableItem.getDetailEtc()+selectableItem.getSpecial(),Toast.LENGTH_LONG).show();

        Lost obLost = new Lost(
                selectableItem.getFname(),
                selectableItem.getLname(),
                selectableItem.getGender(),
                selectableItem.getAge(),
                selectableItem.getCity(),
                selectableItem.getDistrict(),
                selectableItem.getSubdistrict(),
                selectableItem.getPlace(),
                selectableItem.getHeight(),
                selectableItem.getShape(),
                selectableItem.getHairtype(),
                selectableItem.getHaircolor(),
                selectableItem.getUpperwaist(),
                selectableItem.getUppercolor(),
                selectableItem.getLowerwaist(),
                selectableItem.getLowercolor(),
                selectableItem.getSkintone(),
                selectableItem.getTypeId(),
                "0",
                selectableItem.getDetailEtc(),
                selectableItem.getSpecial(),
                selectableItem.getMode()
        );

        obLost.setMode(0);


        Lost obLost3 = new Lost("","",
                "M","","",
                "","","",
                "","","",
                "","","",
                "","",""," ",
                "0","","",0);
//        Lost obLost2 = new Lost();
        Call<LostModel> call = retrofit.searchLost(obLost);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostItemList = new ArrayList<>();
                    mLostItemList.clear();
                    for (int i = 0; i < lost.size(); i++) {
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

                        Lost item = new Lost(fname, lname, gender, age,city, dis, sub, place, height,
                                shape, hairtype, haircolor, upperwaist, upperolor, lowerwaist,
                                lowercolor, skintone, type_id, status, detail_etc, special,date);
                        mLostItemList.add(item);
                    }
//                    Toast.makeText(getApplicationContext(), " ok", Toast.LENGTH_LONG).show();
                    showProgress(false);
                    setupListView();
                }else {
                    showProgress(false);
//                    Toast.makeText(getApplicationContext(), " no" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

//        Call<LostModel> call = retrofit.searchLost(
//                ob.getFname()+ "",
//                ob.getLname() + " ",
//                ob.getGender() + "",
//                ob.getCity() + "",
//                ob.getHeight() + "",
//                ob.getShape() + "",
//                ob.getHairtype() + "",
//                ob.getHaircolor() + "",
//                ob.getSkintone() + "",
//                ob.getTypeId() + "",
//                ob.getStatus() + "",
//                ob.getDetailEtc() + " ผอม"
//                );

//        call.enqueue(new Callback<LostModel>() {
//            @Override
//            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
//                if (response.body() != null) {
//                    LostModel lostmodel = response.body();
//                    List<Lost> lost = lostmodel.getBody();
//                    mLostItemList = new ArrayList<>();
//                    for (int i = 0; i < lost.size(); i++) {
//                        String fname = lost.get(i).getFname();
//                        String lname = lost.get(i).getLname();
//                        String detail = lost.get(i).getDetailEtc();
//                        String date = lost.get(i).getRegDate();
//
//                        Toast.makeText(getApplicationContext(), lost.get(i).getDetailEtc(),Toast.LENGTH_LONG).show();
//
//                        Lost item = new Lost(fname, lname, detail, date);
//                        mLostItemList.add(item);
//                    }
////                    setupListView();
//                }
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Failure",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void setupListView() {
        lv = findViewById(R.id.list_result);
        adapterLost = new LostListAdapter(
                ResultLostActivity.this,
                R.layout.list_lost,
                mLostItemList
        );
        lv.setAdapter(adapterLost);
        setSwipeListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lost item = mLostItemList.get(position);

                Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
                intent.putExtra("stringLost", item.toString());
                startActivity(intent);
//                PhoneItem item = mPhoneItemList.get(position);
//
//                final PhoneItem phoneItem = mPhoneItemList.get(position);
//
//                switch (i) {
//                    case 0: // Edit
//                        Intent intent = new Intent(MainActivity.this, EditPhoneItemActivity.class);
//                        intent.putExtra("title", phoneItem.title);
//                        intent.putExtra("number", phoneItem.number);
//                        intent.putExtra("id", phoneItem._id);
//                        startActivity(intent);
//                        break;}
//
            }
        });

        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Toast.makeText(getApplicationContext(),"ลบแล้ว",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void setSwipeListView() {
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


}
