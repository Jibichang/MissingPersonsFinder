package com.example.aomek.missingpersonsfinder.result;

import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.db.DatabaseHelper;
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

public class ResultLostActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Lost> mLostItemList;
//    private String strPOST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_lost);

        searchLostData();
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
////            Toast.makeText(getApplicationContext(), paramObject.toString(),Toast.LENGTH_LONG).show();
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
        Lost obLost = new Lost("ธนโชติ","บุญ","M","นครสวรรค์"
                ,"0","0","0","0","0",
                "0","0","0"," ");

        Call<LostModel> call = retrofit.searchLost(obLost);
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
                        String detail = lost.get(i).getDetailEtc();
                        String date = lost.get(i).getRegDate();

                        Toast.makeText(getApplicationContext(), lost.get(i).getDetailEtc(), Toast.LENGTH_LONG).show();

                        Lost item = new Lost(fname, lname, detail, date);
                        mLostItemList.add(item);
                    }
//                    setupListView();
                }
                else {

                    if(response.code() == 400){
                        Toast.makeText(getApplicationContext(), "Failure response body NULL but Http OK 400", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Failure response body NULL and status : " + response.code(), Toast.LENGTH_LONG).show();
                    }
//                        LostModel lostmodel = response.body();
//                        List<Lost> lost = lostmodel.getBody();
//                        Toast.makeText(getApplicationContext(), "Failure response body NULL", Toast.LENGTH_LONG).show();
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
}
