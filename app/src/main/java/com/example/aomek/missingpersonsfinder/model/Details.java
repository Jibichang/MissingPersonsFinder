package com.example.aomek.missingpersonsfinder.model;

import com.example.aomek.missingpersonsfinder.R;

import java.util.ArrayList;
import java.util.Locale;

public class Details {

    private String detail;
    private static ArrayList<String> type = new ArrayList<String>();


    private static ArrayList<String> shape_list;
    private static ArrayList<Integer> shape_list_img;
    private static ArrayList<String> shape_list_code;

    private static ArrayList<String> hairtype_list;
    private static ArrayList<Integer> hairtype_list_img;
    private static ArrayList<String> hairtype_list_code;

    //    private static ArrayList<String> haircolor_list = new ArrayList<String>();
    private static ArrayList<String> height_list;
    private static ArrayList<String> weight_list = new ArrayList<String>();

    //    private static ArrayList<String> skintone_list = new ArrayList<String>();
    private static ArrayList<String> upperwaist_list;
    private static ArrayList<Integer> upperwaist_list_img;
    private static ArrayList<String> upperwaist_list_code;

    private static ArrayList<String> lowerwaist_list;
    private static ArrayList<Integer> lowerwaist_list_img;
    private static ArrayList<String> lowerwaist_list_code;

//    private static ArrayList<String> detail_etc_list = new ArrayList<String>();


    public Details(){

    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)"
        );
        return msg;
    }

    public static void setShape_list() {
//        shape_list_img.add(R.drawable.icons8no);
//        shape_list.add("ไม่ระบุ");
//        shape_list_code.add("S0");
        shape_list_img = new ArrayList<>();
        shape_list  = new ArrayList<>();
        shape_list_code = new ArrayList<>();

        shape_list_img.add(R.drawable.icon8shape1);
        shape_list.add("รูปร่างผอม");
        shape_list_code.add("S1");

        shape_list_img.add(R.drawable.icon8shape2);
        shape_list.add("รูปร่างท้วม");
        shape_list_code.add("S2");

        shape_list_img.add(R.drawable.icon8shape3);
        shape_list.add("รูปร่างเล็ก");
        shape_list_code.add("S3");
    }

    public static ArrayList<String> getShape_list() {
        return shape_list;
    }

    public static ArrayList<Integer> getShape_list_img() {
        return shape_list_img;
    }

    public static ArrayList<String> getShape_list_code() {
        return shape_list_code;
    }


    public static void setHairtype_list() {

//        hairtype_list_img.add(R.drawable.icons8no);
//        hairtype_list.add("ไม่ระบุ");
//        hairtype_list_code.add("HR0");
        hairtype_list_img = new ArrayList<>();
        hairtype_list = new ArrayList<>();
        hairtype_list_code = new ArrayList<>();

        hairtype_list_img.add(R.drawable.icon8hair1);
        hairtype_list.add("ผมเกรียนสั้น");
        hairtype_list_code.add("HT1");

        hairtype_list_img.add(R.drawable.icon8hair2);
        hairtype_list.add("ผมสั้น");
        hairtype_list_code.add("HT2");

        hairtype_list_img.add(R.drawable.icon8hair3);
        hairtype_list.add("ผมยาว");
        hairtype_list_code.add("HT3");

        hairtype_list_img.add(R.drawable.icon8hair4);
        hairtype_list.add("ศีรษะล้าน");
        hairtype_list_code.add("HT4");
    }

    public static ArrayList<String> getHairtype_list() {
        return hairtype_list;
    }

    public static ArrayList<Integer> getHairtype_list_img() {
        return hairtype_list_img;
    }

    public static ArrayList<String> getHairtype_list_code() {
        return hairtype_list_code;
    }


    public static void setHeight_list() {
        height_list = new ArrayList<>();
        height_list.add("-");
        height_list.add("ต่ำกว่า120");
        height_list.add("120-139");
        height_list.add("140-159");
        height_list.add("160-179");
        height_list.add("180ขึ้นไป");
    }

    public static ArrayList<String> getHeight_list() {
        return height_list;
    }

    public static void setUpperwaist_list() {

//        upperwaist_list_img.add(R.drawable.icons8no);
//        upperwaist_list.add("ไม่ระบุ");
//        upperwaist_list_code.add("UW0");

        upperwaist_list_img = new ArrayList<>();
        upperwaist_list = new ArrayList<>();
        upperwaist_list_code = new ArrayList<>();

        upperwaist_list_img.add(R.drawable.icon8upper1);
        upperwaist_list.add("เสื้อเชิ้ต");
        upperwaist_list_code.add("U01");

        upperwaist_list_img.add(R.drawable.icon8upper2);
        upperwaist_list.add("เสื้อยืด");
        upperwaist_list_code.add("U02");


        upperwaist_list_img.add(R.drawable.icon8upper3);
        upperwaist_list.add("เสื้อกล้าม");
        upperwaist_list_code.add("U03");

        upperwaist_list_img.add(R.drawable.icon8upper4);
        upperwaist_list.add("เสื้อแขนยาว");
        upperwaist_list_code.add("U04");

        upperwaist_list_img.add(R.drawable.icon8upper5);
        upperwaist_list.add("เดรส");
        upperwaist_list_code.add("U05");
    }

    public static ArrayList<String> getUpperwaist_list() {
        return upperwaist_list;
    }

    public static ArrayList<Integer> getUpperwaist_list_img() {
        return upperwaist_list_img;
    }

    public static ArrayList<String> getUpperwaist_list_code() {
        return upperwaist_list_code;
    }

    public static void setLowerwaist_list() {

//        lowerwaist_list_img.add(R.drawable.icons8no);
//        lowerwaist_list.add("ไม่ระบุ");
//        lowerwaist_list_code.add("LW0");

        lowerwaist_list_img = new ArrayList<>();
        lowerwaist_list = new ArrayList<>();
        lowerwaist_list_code = new ArrayList<>();

        lowerwaist_list_img.add(R.drawable.icon8lower1);
        lowerwaist_list.add("กางเกงขาสั้น");
        lowerwaist_list_code.add("L01");

        lowerwaist_list_img.add(R.drawable.icon8lower2);
        lowerwaist_list.add("กางเกงขายาว");
        lowerwaist_list_code.add("L02");

        lowerwaist_list_img.add(R.drawable.icon8lower3);
        lowerwaist_list.add("กระโปรงสั้น");
        lowerwaist_list_code.add("L03");

        lowerwaist_list_img.add(R.drawable.icon8lower4);
        lowerwaist_list.add("กระโปรงยาว");
        lowerwaist_list_code.add("L04");
    }

    public static ArrayList<String> getLowerwaist_list() {
        return lowerwaist_list;
    }

    public static ArrayList<Integer> getLowerwaist_list_img() {
        return lowerwaist_list_img;
    }

    public static ArrayList<String> getLowerwaist_list_code() {
        return lowerwaist_list_code;
    }

}
