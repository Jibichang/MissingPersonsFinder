package com.example.aomek.missingpersonsfinder.model;

import java.util.ArrayList;
import java.util.Locale;

public class Details {

    private String detail;
    private static ArrayList<String> shape_list = new ArrayList<String>();
    private static ArrayList<String> shape_list_img = new ArrayList<String>();
    private static ArrayList<String> hairtype_list = new ArrayList<String>();
    private static ArrayList<String> hairtype_list_img = new ArrayList<String>();
//    private static ArrayList<String> haircolor_list = new ArrayList<String>();
    private static ArrayList<String> height_list = new ArrayList<String>();
    private static ArrayList<String> weight_list = new ArrayList<String>();
//    private static ArrayList<String> skintone_list = new ArrayList<String>();
    private static ArrayList<String> upperwaist_list = new ArrayList<String>();
    private static ArrayList<String> upperwaist_list_img = new ArrayList<String>();
    private static ArrayList<String> lowerwaist_list = new ArrayList<String>();
    private static ArrayList<String> lowerwaist_list_img = new ArrayList<String>();
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
        shape_list_img.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        shape_list.add("Havasu Falls");

        shape_list_img.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        shape_list.add("Trondheim");

        shape_list_img.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        shape_list.add("Portugal");

        shape_list_img.add("https://i.redd.it/j6myfqglup501.jpg");
        shape_list.add("Rocky Mountain National Park");

    }

    public static ArrayList<String> getShape_list() {
        return shape_list;
    }

    public static ArrayList<String> getShape_list_img() {
        return shape_list_img;
    }

    public static void setHairtype_list() {
        hairtype_list_img.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        hairtype_list.add("Havasu Falls");

        hairtype_list_img.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        hairtype_list.add("Trondheim");

        hairtype_list_img.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        hairtype_list.add("Portugal");

        hairtype_list_img.add("https://i.redd.it/j6myfqglup501.jpg");
        hairtype_list.add("Rocky Mountain National Park");


        hairtype_list_img.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        hairtype_list.add("Mahahual");

        hairtype_list_img.add("https://i.redd.it/k98uzl68eh501.jpg");
        hairtype_list.add("Frozen Lake");


        hairtype_list_img.add("https://i.redd.it/glin0nwndo501.jpg");
        hairtype_list.add("White Sands Desert");

        hairtype_list_img.add("https://i.redd.it/obx4zydshg601.jpg");
        hairtype_list.add("Austrailia");

        hairtype_list_img.add("https://i.imgur.com/ZcLLrkY.jpg");
        hairtype_list.add("Washington");
    }

    public static ArrayList<String> getHairtype_list() {
        return hairtype_list;
    }

    public static ArrayList<String> getHairtype_list_img() {
        return hairtype_list_img;
    }

    public static void setHeight_list() {

    }

    public static void setUpperwaist_list() {
        upperwaist_list_img.add("https://i.redd.it/k98uzl68eh501.jpg");
        upperwaist_list.add("Frozen Lake");


        upperwaist_list_img.add("https://i.redd.it/glin0nwndo501.jpg");
        upperwaist_list.add("White Sands Desert");
    }

    public static ArrayList<String> getUpperwaist_list() {
        return upperwaist_list;
    }

    public static ArrayList<String> getUpperwaist_list_img() {
        return upperwaist_list_img;
    }

    public static void setLowerwaist_list() {
        lowerwaist_list_img.add("https://i.redd.it/k98uzl68eh501.jpg");
        lowerwaist_list.add("Frozen Lake");


        lowerwaist_list_img.add("https://i.redd.it/glin0nwndo501.jpg");
        lowerwaist_list.add("White Sands Desert");
    }

    public static ArrayList<String> getLowerwaist_list() {
        return lowerwaist_list;
    }

    public static ArrayList<String> getLowerwaist_list_img() {
        return lowerwaist_list_img;
    }
}
