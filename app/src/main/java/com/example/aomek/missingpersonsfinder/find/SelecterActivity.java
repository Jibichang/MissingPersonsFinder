package com.example.aomek.missingpersonsfinder.find;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import petrov.kristiyan.colorpicker.ColorPicker;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aomek.missingpersonsfinder.R;
import com.example.aomek.missingpersonsfinder.adapter.DetailListAdapter;
import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.example.aomek.missingpersonsfinder.home.MainActivity;
import com.example.aomek.missingpersonsfinder.model.Details;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.result.ResultLostActivity;

import java.util.ArrayList;

public class SelecterActivity extends AppCompatActivity implements ItemClickListener {
    private static final String TAG = "SelecterActivity";
    Spinner heightSpinner;
    EditText etcEdittext;

    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<String> mImageUrls = new ArrayList<String>();
    private final boolean isUpper = true;
    public Lost itemSelect = new Lost();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecter);

        etcEdittext = findViewById(R.id.editText_etc);

        initRecyclerView();
        setSpinnerHeight();

        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String height = heightSpinner.getSelectedItem().toString();
                selectableItem.setHeight(height);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = new String[]{
                        "ข้อมูลส่วนตัว",
                        "สถานที่",
                        "ลักษณะทางกายภาพ",
                        "ไม่เลือก"
                };

                new AlertDialog.Builder(SelecterActivity.this)
                        .setTitle("เลือกความสำคัญของข้อมูล")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String detail_etc = etcEdittext.getText().toString();
                                selectableItem.setDetailEtc(detail_etc);
                                switch (i) {
                                    case 0: // name
                                        selectableItem.setMode(0);
                                        startActivity( new Intent(SelecterActivity.this, ResultLostActivity.class));
                                        break;
                                    case 1: // city
                                        selectableItem.setMode(1);
                                        startActivity( new Intent(SelecterActivity.this, ResultLostActivity.class));
                                        break;
                                    case 2: // detail
                                        selectableItem.setMode(2);
                                        startActivity( new Intent(SelecterActivity.this, ResultLostActivity.class));
                                        break;
                                    case 3: // no
                                        selectableItem.setMode(3);
                                        startActivity( new Intent(SelecterActivity.this, ResultLostActivity.class));
                                        break;
                                }
                            }
                        })
                        .show();

//                return true;

//                String detail_etc = etcEdittext.getText().toString();
//                selectableItem.setDetailEtc(detail_etc);
////
////                Toast.makeText(SelecterActivity.this, selectableItem.getHairtype()+ " : "+ selectableItem.getShape()
////                        + " : "+ selectableItem.getUpperwaist() + " : "+ selectableItem.getLowerwaist(), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(SelecterActivity.this, ResultLostActivity.class);
//                startActivity(i);
            }
        });

        Button selectColorUpper = findViewById(R.id.button_color_upper);
        selectColorUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickColorUpper();
            }
        });

        Button selectColorLower = findViewById(R.id.button_color_lower);
        selectColorLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickColorLower();
            }
        });

        // hair color
        ImageView haircolor0 = findViewById(R.id.haircolor1);
        haircolor0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#0000000");
            }
        });

        // skin tone
        ImageView st1 = findViewById(R.id.skintone1);
        st1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T1");
            }
        });
        ImageView st2 = findViewById(R.id.skintone2);
        st2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T2");
            }
        });
        ImageView st3 = findViewById(R.id.skintone3);
        st3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T3");
            }
        });
        ImageView st4 = findViewById(R.id.skintone4);
        st4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T4");
            }
        });
        ImageView st5 = findViewById(R.id.skintone5);
        st5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T5");
            }
        });
        ImageView st6 = findViewById(R.id.skintone6);
        st6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T6");
            }
        });

    }

    public void setSpinnerHeight(){
        heightSpinner = findViewById(R.id.spinner_height);
        Lost.setListHeight();
        ArrayAdapter<String> adapterHeigth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Lost.getListHeight());
        heightSpinner.setAdapter(adapterHeigth);
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
        recyclerView_hairType.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        Details.setHairtype_list();
        DetailListAdapter hairType = new DetailListAdapter(this, Details.getHairtype_list(),
                Details.getHairtype_list_img(),Details.getHairtype_list_code());
        recyclerView_hairType.setAdapter(hairType);

//        Shape List
        RecyclerView recyclerView_Shape = findViewById(R.id.ReView_shape);
        recyclerView_Shape.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        Details.setShape_list();
        DetailListAdapter Shape = new DetailListAdapter(this, Details.getShape_list(),
                Details.getShape_list_img(), Details.getShape_list_code());
        recyclerView_Shape.setAdapter(Shape);

//        Lower List
        RecyclerView recyclerView_Lower = findViewById(R.id.ReView_lower);
        recyclerView_Lower.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        Details.setLowerwaist_list();
        DetailListAdapter Lower = new DetailListAdapter(this, Details.getLowerwaist_list(),
                Details.getLowerwaist_list_img(), Details.getLowerwaist_list_code());
        recyclerView_Lower.setAdapter(Lower);

//        Upper List
        RecyclerView recyclerView_Upper = findViewById(R.id.ReView_upper);
        recyclerView_Upper.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        Details.setUpperwaist_list();
        DetailListAdapter Upper = new DetailListAdapter(this, Details.getUpperwaist_list(),
                Details.getUpperwaist_list_img(), Details.getUpperwaist_list_code());
        recyclerView_Upper.setAdapter(Upper);
    }

    private ArrayList<String> arrayColor(){
        ArrayList<String> color = new ArrayList<>();
        color.add("#e6194B");
        color.add("#f58231");
        color.add("#ffe119");
        color.add("#bfef45");
        color.add("#3cb44b");
        color.add("#42d4f4");
        color.add("#751aff");
        color.add("#f359eb");
        color.add("#a9a9a9");
        color.add("#FFFFFF");
        color.add("#0d0d0d");
        color.add("#663300");

        return color;
    }

    private void pickColorUpper(){
        final ColorPicker colorPicker = new ColorPicker(SelecterActivity.this);

        colorPicker.setColors(arrayColor())
                .setColumns(5)
                .setTitle("กรุณาเลือกสีของเครื่องแต่งกาย")
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    String codeColor;

                    @Override
                    public void onChooseColor(int position,int color) {
                        codeColor = String.format("#%06X", 0xFFFFFF & color);
                        if (codeColor.equals("#000000")){
                            codeColor = "-";
                        }
                        selectableItem.setUppercolor(codeColor);
                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(){
                        codeColor = "-";
                        selectableItem.setUppercolor(codeColor);
                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                })
//                .setDefaultColorButton(Color.parseColor("#663300"))
                .setColumns(6)
                .show();
    }

    private void pickColorLower(){
        final ColorPicker colorPicker = new ColorPicker(SelecterActivity.this);

        colorPicker.setColors(arrayColor())
                .setColumns(5)
                .setTitle("กรุณาเลือกสีของเครื่องแต่งกาย")
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    String codeColor;
                    @Override
                    public void onChooseColor(int position,int color) {
                        codeColor = String.format("#%06X", 0xFFFFFF & color);
                        if (codeColor.equals("#000000")){
                            codeColor = "-";
                        }
                        selectableItem.setLowercolor(codeColor);
                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(){
                        codeColor = "-";
                        selectableItem.setLowercolor(codeColor);
                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                })
//                .setDefaultColorButton(Color.parseColor("#663300"))
                .setColumns(6)
                .show();
    }


}
