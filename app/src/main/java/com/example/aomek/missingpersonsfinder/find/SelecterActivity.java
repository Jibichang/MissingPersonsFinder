package com.example.aomek.missingpersonsfinder.find;

import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.DetailListAdapter;
import com.example.aomek.missingpersonsfinder.home.SplashActivity;
import com.example.aomek.missingpersonsfinder.model.Details;

import java.util.ArrayList;
import java.util.List;

public class SelecterActivity extends AppCompatActivity {
    private static final String TAG = "SelecterActivity";

    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<String> mImageUrls = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecter);

//        rg = (RadioGroup) findViewById(R.id.RG);
//
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
//                        .getText().toString();
//                Toast.makeText(SelecterActivity.this, value, Toast.LENGTH_SHORT).show();
//            }
//        });
        getImages();
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recyclerView = findViewById(R.id.ReView_hairtype);
//        recyclerView.setLayoutManager(layoutManager);
//        DetailListAdapter adapter = new DetailListAdapter(this, mNames, mImageUrls);
//        recyclerView.setAdapter(adapter);

//        Hair Type List
        RecyclerView recyclerView_hairType = findViewById(R.id.ReView_hairtype);
        recyclerView_hairType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Details.setHairtype_list();
        DetailListAdapter hairType = new DetailListAdapter(this, Details.getHairtype_list(), Details.getHairtype_list_img());
        recyclerView_hairType.setAdapter(hairType);

//        Shape List
        RecyclerView recyclerView_Shape = findViewById(R.id.ReView_shape);
        recyclerView_Shape.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Details.setShape_list();
        DetailListAdapter Shape = new DetailListAdapter(this, Details.getShape_list(), Details.getShape_list_img());
        recyclerView_Shape.setAdapter(Shape);

//        Lower List
        RecyclerView recyclerView_Lower = findViewById(R.id.ReView_lower);
        recyclerView_Lower.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Details.setLowerwaist_list();
        DetailListAdapter Lower = new DetailListAdapter(this, Details.getLowerwaist_list(), Details.getLowerwaist_list_img());
        recyclerView_Lower.setAdapter(Lower);

//        Upper List
        RecyclerView recyclerView_Upper = findViewById(R.id.ReView_upper);
        recyclerView_Upper.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Details.setUpperwaist_list();
        DetailListAdapter Upper = new DetailListAdapter(this, Details.getUpperwaist_list(), Details.getUpperwaist_list_img());
        recyclerView_Upper.setAdapter(Upper);


    }

}
