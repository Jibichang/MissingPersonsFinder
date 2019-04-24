package com.example.aomek.missingpersonsfinder.find;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.aomek.missingpersonsfinder.model.Details;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.add.AddConfirmActivity;
import com.example.aomek.missingpersonsfinder.profile.SettingActivity;
import com.example.aomek.missingpersonsfinder.result.ResultLostActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SelecterActivity extends AppCompatActivity implements ItemClickListener {
    private static final String TAG = "SelecterActivity";
    Spinner heightSpinner;
    EditText etcEdittext, speEdittext;

    private Boolean isAddAct;
    private int pd;


    ImageView st1, st2, st3, st4, st5, st6;
    ImageView haircolor1, haircolor2, haircolor3, haircolor4, haircolor5, haircolor6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecter);

        final int padding = getResources().getDimensionPixelOffset(R.dimen.fui_button_inset_bottom);
        pd = padding;

        etcEdittext = findViewById(R.id.editText_etc);
        speEdittext = findViewById(R.id.editText_spe);

        initRecyclerView();
        setSpinnerHeight();
        initHairColor();
        initSkinTone();

//        Toast.makeText(getApplicationContext(), "ok"+selectableItem.getImage(), Toast.LENGTH_LONG).show();

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
//        isAddAct = intent.getBooleanExtra("isAddAct", false);
        isAddAct = getIntent().getBooleanExtra("isAddAct", false);

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setDetailEtc(etcEdittext.getText().toString());
                selectableItem.setSpecial(speEdittext.getText().toString());
//                Toast.makeText(SelecterActivity.this, "ff"+ isAddAct,Toast.LENGTH_LONG).show();
                if (isAddAct){
                    startActivity(new Intent(getApplicationContext(), AddConfirmActivity.class));
                }else {
                    showFilter();
                }

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



    }

    private void initHairColor(){
        // hair color
        haircolor1 = findViewById(R.id.haircolor1);
        haircolor2 = findViewById(R.id.haircolor2);
        haircolor3 = findViewById(R.id.haircolor3);
        haircolor4 = findViewById(R.id.haircolor4);
        haircolor5 = findViewById(R.id.haircolor5);
        haircolor6 = findViewById(R.id.haircolor6);
        haircolor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#D3D3D3");
//                haircolor6.setCropToPadding(true);

                haircolor6.setPadding(pd, pd, pd, pd);
                haircolor1.setPadding(0, 0, 0, 0);
                haircolor2.setPadding(0, 0, 0, 0);
                haircolor3.setPadding(0, 0, 0, 0);
                haircolor4.setPadding(0, 0, 0, 0);
                haircolor5.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ขาว-ดำ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        haircolor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#d2c799");
                haircolor1.setPadding(pd, pd, pd, pd);
                haircolor6.setPadding(0, 0, 0, 0);
                haircolor2.setPadding(0, 0, 0, 0);
                haircolor3.setPadding(0, 0, 0, 0);
                haircolor4.setPadding(0, 0, 0, 0);
                haircolor5.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ขาว", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        haircolor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#dab358");
                haircolor2.setPadding(pd, pd, pd, pd);
                haircolor1.setPadding(0, 0, 0, 0);
                haircolor6.setPadding(0, 0, 0, 0);
                haircolor3.setPadding(0, 0, 0, 0);
                haircolor4.setPadding(0, 0, 0, 0);
                haircolor5.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ทอง", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        haircolor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#885f4d");
                haircolor3.setPadding(pd, pd, pd, pd);
                haircolor1.setPadding(0, 0, 0, 0);
                haircolor2.setPadding(0, 0, 0, 0);
                haircolor6.setPadding(0, 0, 0, 0);
                haircolor4.setPadding(0, 0, 0, 0);
                haircolor5.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"น้ำตาลอ่อน", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        haircolor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#5c3f3b");
                haircolor4.setPadding(pd, pd, pd, pd);
                haircolor1.setPadding(0, 0, 0, 0);
                haircolor2.setPadding(0, 0, 0, 0);
                haircolor3.setPadding(0, 0, 0, 0);
                haircolor6.setPadding(0, 0, 0, 0);
                haircolor5.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"น้ำตาลเข้ม", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        haircolor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setHaircolor("#0b090a");
                haircolor5.setPadding(pd, pd, pd, pd);
                haircolor1.setPadding(0, 0, 0, 0);
                haircolor2.setPadding(0, 0, 0, 0);
                haircolor3.setPadding(0, 0, 0, 0);
                haircolor4.setPadding(0, 0, 0, 0);
                haircolor6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ดำ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    private void initSkinTone(){
        // skin tone
        st1 = findViewById(R.id.skintone1);
        st2 = findViewById(R.id.skintone2);
        st3 = findViewById(R.id.skintone3);
        st4 = findViewById(R.id.skintone4);
        st5 = findViewById(R.id.skintone5);
        st6 = findViewById(R.id.skintone6);
        st1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T1");
                st1.setPadding(pd, pd, pd, pd);
                st2.setPadding(0, 0, 0, 0);
                st3.setPadding(0, 0, 0, 0);
                st4.setPadding(0, 0, 0, 0);
                st5.setPadding(0, 0, 0, 0);
                st6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวขาวซีด", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        st2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T2");
                st2.setPadding(pd, pd, pd, pd);
                st1.setPadding(0, 0, 0, 0);
                st3.setPadding(0, 0, 0, 0);
                st4.setPadding(0, 0, 0, 0);
                st5.setPadding(0, 0, 0, 0);
                st6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวขาว", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        st3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T3");
                st3.setPadding(pd, pd, pd, pd);
                st2.setPadding(0, 0, 0, 0);
                st1.setPadding(0, 0, 0, 0);
                st4.setPadding(0, 0, 0, 0);
                st5.setPadding(0, 0, 0, 0);
                st6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวขาวอมเหลือง", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        st4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T4");
                st4.setPadding(pd, pd, pd, pd);
                st2.setPadding(0, 0, 0, 0);
                st3.setPadding(0, 0, 0, 0);
                st1.setPadding(0, 0, 0, 0);
                st5.setPadding(0, 0, 0, 0);
                st6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวสองสี", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        st5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T5");
                st5.setPadding(pd, pd, pd, pd);
                st2.setPadding(0, 0, 0, 0);
                st3.setPadding(0, 0, 0, 0);
                st4.setPadding(0, 0, 0, 0);
                st1.setPadding(0, 0, 0, 0);
                st6.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวน้ำตาลเข้ม", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        st6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectableItem.setSkintone("T6");
                st6.setPadding(pd, pd, pd, pd);
                st2.setPadding(0, 0, 0, 0);
                st3.setPadding(0, 0, 0, 0);
                st4.setPadding(0, 0, 0, 0);
                st5.setPadding(0, 0, 0, 0);
                st1.setPadding(0, 0, 0, 0);
                Snackbar.make(view,"ผิวคล้ำ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void showFilter(){
        String[] items = new String[]{
                "ชื่อ นามสกุล",
                "สถานที่",
                "ลักษณะทางกายภาพ",
                "ไม่เลือก"
        };

        new AlertDialog.Builder(SelecterActivity.this)
                .setTitle("เลือกความสำคัญของข้อมูล")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0: // name
                                selectableItem.setMode(0);
//                                dialogInterface.dismiss();
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
    }

    public void setSpinnerHeight(){
        heightSpinner = findViewById(R.id.spinner_height);
        Details.setHeight_list();
        ArrayAdapter<String> adapterHeigth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Details.getHeight_list());
        heightSpinner.setAdapter(adapterHeigth);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

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
//                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(){
                        codeColor = "-";
                        selectableItem.setUppercolor(codeColor);
//                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
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
//                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(){
                        codeColor = "-";
                        selectableItem.setLowercolor(codeColor);
//                        Toast.makeText(SelecterActivity.this, codeColor, Toast.LENGTH_SHORT).show();
                    }

                })
//                .setDefaultColorButton(Color.parseColor("#663300"))
                .setColumns(6)
                .show();
    }


}
