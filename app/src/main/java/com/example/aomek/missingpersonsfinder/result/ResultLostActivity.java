package com.example.aomek.missingpersonsfinder.result;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.adapter.LostListAdapter;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.retrofit.RetrofitAPI;

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
//    private String strPOST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_lost);


        searchLostData();


//        Toast.makeText(getApplicationContext(),fname,Toast.LENGTH_LONG).show();

//        final Lost lostItem = mLostItemList.get(0);
        mDataLostView = findViewById(R.id.list_result);

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

                    Toast.makeText(getApplicationContext(), selectableItem.getFname()+selectableItem.getLname()+
                selectableItem.getGender()+selectableItem.getAge()+selectableItem.getCity()+
                selectableItem.getDistrict()+selectableItem.getSubdistrict()+selectableItem.getPlace()+
                selectableItem.getHeight()+selectableItem.getShape()+selectableItem.getHairtype()+
                selectableItem.getHaircolor()+selectableItem.getUpperwaist()+selectableItem.getUppercolor()+
                selectableItem.getLowerwaist()+selectableItem.getLowercolor()+
                selectableItem.getSkintone()+selectableItem.getTypeId()+
                "0"+selectableItem.getDetailEtc()+selectableItem.getSpecial(),Toast.LENGTH_LONG).show();

        Lost obLost = new Lost(
                selectableItem.getFname()+" ",
                selectableItem.getLname()+" ",
                selectableItem.getGender()+ "",
                selectableItem.getAge()+" ",
                selectableItem.getCity()+" ",
                selectableItem.getDistrict()+" ",
                selectableItem.getSubdistrict()+" ",
                selectableItem.getPlace()+" ",
                selectableItem.getHeight()+" ",
                selectableItem.getShape()+" ",
                selectableItem.getHairtype()+" ",
                selectableItem.getHaircolor()+" ",
                selectableItem.getUpperwaist()+" ",
                selectableItem.getUppercolor()+" ",
                selectableItem.getLowerwaist()+" ",
                selectableItem.getLowercolor()+" ",
                selectableItem.getSkintone()+" ",
                selectableItem.getTypeId()+" ",
                "0",
                selectableItem.getDetailEtc()+" ",
                selectableItem.getSpecial()+" ");
        Lost obLost3 = new Lost("","",
                "M","","",
                "","","",
                "","","",
                "","","",
                "","",""," ",
                "0","","");
//        Lost obLost2 = new Lost();
        Call<LostModel> call = retrofit.searchLost(obLost3);
        call.enqueue(new Callback<LostModel>() {
            @Override
            public void onResponse(Call<LostModel> call, Response<LostModel> response) {
                if (response.body() != null) {
                    LostModel lostmodel = response.body();
                    List<Lost> lost = lostmodel.getBody();
                    mLostItemList = new ArrayList<>();
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
                    Toast.makeText(getApplicationContext(), " ok", Toast.LENGTH_LONG).show();
                    setupListView();
                }else {
                    Toast.makeText(getApplicationContext(), " no" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
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
        LostListAdapter adapter = new LostListAdapter(
                ResultLostActivity.this,
                R.layout.list_lost,
                mLostItemList
        );
        ListView lv = findViewById(R.id.list_result);
        lv.setAdapter(adapter);
    }


}
