package com.example.aomek.missingpersonsfinder.result;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.example.aomek.missingpersonsfinder.adapter.ItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aomek.missingpersonsfinder.R;

public class ScrollingActivity extends AppCompatActivity implements ItemClickListener {
    private TextView fnameTextView;
    private TextView lnameTextView;
    private TextView ageTextView;
    private TextView genderTextView;

    private TextView cityTextView;
    private TextView disTextView;
    private TextView subDisTextView;
    private TextView placeTextView;

    private TextView detailTextView;
    private TextView typeTextView;
    private TextView statusTextView;

    private ImageView skinImg;
    private ImageView hairImg;
    private ImageView upperImg;
    private ImageView lowerImg;
    //TODO get image lost from database
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ข้อมูลบุคคลสูญหาย");
        setSupportActionBar(toolbar);

        setTextView();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.tel);
        tel = "";
        //TODO call me maybe 
//        if (tel.isEmpty()){
//            fab.setEnabled(false);
////            fab.setBackgroundColor(getResources().getColor(R.color.gray2));
//        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regexStr = "^[0-9]*$";

                if(tel.matches(regexStr) && !tel.isEmpty()){
                    Intent intent = new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:" + tel)
                    );
                    startActivity(intent);
                }
                else{

                     Snackbar.make(view, "ไม่มีข้อมูลการติดต่อทางโทรศัพท์", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            }
        });


    }

    private void setTextView(){
        fnameTextView = findViewById(R.id.text_result_fname);
        lnameTextView = findViewById(R.id.text_result_lname);
        ageTextView = findViewById(R.id.text_result_age);
        genderTextView = findViewById(R.id.text_result_gender);

        cityTextView = findViewById(R.id.text_result_city);
        disTextView = findViewById(R.id.text_result_district);
        subDisTextView = findViewById(R.id.text_result_subdistrict);
        placeTextView = findViewById(R.id.text_result_place);

        detailTextView = findViewById(R.id.text_result_detail);
        typeTextView = findViewById(R.id.text_result_type);
        statusTextView = findViewById(R.id.text_status);

        skinImg = findViewById(R.id.img_skin);
        hairImg = findViewById(R.id.img_hair_color);
        upperImg = findViewById(R.id.img_upper);
        lowerImg = findViewById(R.id.img_lower);

        fnameTextView.setText(lostContent.getFname());
        lnameTextView.setText(lostContent.getLname());
        ageTextView.setText(lostContent.getAge());
        genderTextView.setText(lostContent.strGender());

        cityTextView.setText(lostContent.getCity());
        disTextView.setText(lostContent.getDistrict());
        subDisTextView.setText(lostContent.getSubdistrict());
        placeTextView.setText(lostContent.getPlace());

        detailTextView.setText(setStrDetails());
        typeTextView.setText(lostContent.strType());

        setSkintone();
        setULHcolor();

        if (lostContent.getStatus().equals("1")){
            statusTextView.setText("พบบุคคลสูญหายแล้ว");
        }

    }

    private boolean isHEXcolor(String str){
        String hex = str.substring(0, 1);
        return (hex.equals("#"));
    }

    private void setSkintone(){
        String skin = lostContent.getSkintone();
        switch (skin){
            case "T1" :
                skinImg.setImageResource(R.color.T1);
                break;
            case "T2" :
                skinImg.setImageResource(R.color.T2);
                break;
            case "T3" :
                skinImg.setImageResource(R.color.T3);
                break;
            case "T4" :
                skinImg.setImageResource(R.color.T4);
                break;
            case "T5" :
                skinImg.setImageResource(R.color.T5);
                break;
            case "T6" :
                skinImg.setImageResource(R.color.T6);
                break;

            default:
//                skinImg.setImageResource(R.color.white);
                break;
        }
    }

    private void setULHcolor(){
        String upperColor = lostContent.getUppercolor();
        if (isHEXcolor(upperColor)){
            upperImg.setBackgroundColor(Color.parseColor(upperColor));
        }

        String lowerColor = lostContent.getLowercolor();
        if (isHEXcolor(lowerColor)){
            lowerImg.setBackgroundColor(Color.parseColor(lowerColor));
        }

        String hairColor = lostContent.getHaircolor();
        if (isHEXcolor(hairColor)){
            if (hairColor.equalsIgnoreCase("#D3D3D3")){
                hairImg.setImageResource(R.drawable.haircolor_bandw);
            } else {
                hairImg.setBackgroundColor(Color.parseColor(hairColor));
            }
        }
    }

    private String setStrDetails(){
        //TODO get text value not code from database
        String msg = "รูปร่าง : " + lostContent.getShape() + "\n"
                + "ผม : " + lostContent.getHairtype() + "\n"
                + "เสื้อผ้า : " + lostContent.getUpperwaist()
                + lostContent.getLowerwaist();
        return msg;



//        String msg = "รูปร่าง : " + selectableItemDetail.getShape()+ " สีผิว : " + selectableItem.getSkintone() + "\n"
//                + "ผม : " + selectableItemDetail.getHairtype() + " " + selectableItem.getHaircolor() + "\n"
//                + "เสื้อผ้า : " + selectableItemDetail.getUpperwaist() + " " + selectableItem.getUppercolor() +"\n"
//                + selectableItemDetail.getLowerwaist() + " " + selectableItem.getLowercolor() +"\n";
//        return msg;
    }

}
