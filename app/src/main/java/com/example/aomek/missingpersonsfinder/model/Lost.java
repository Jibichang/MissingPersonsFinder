package com.example.aomek.missingpersonsfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Lost {

    private static ArrayList<String> listtype = new ArrayList<String>();
    private static ArrayList<String> listgender = new ArrayList<String>();
    private static ArrayList<String> listage = new ArrayList<String>();
    private static ArrayList<String> listplace = new ArrayList<String>();
    private static ArrayList<String> listheight = new ArrayList<String>();

    private static List<Lost> loadDataMain;

    public static boolean onStatusLogin = true;
    public static boolean onStatusSearch = true;
    private static String BASE_URL = "https://a384e326.ngrok.io";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("subdistrict")
    @Expose
    private String subdistrict;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("shape")
    @Expose
    private String shape;
    @SerializedName("hairtype")
    @Expose
    private String hairtype;
    @SerializedName("haircolor")
    @Expose
    private String haircolor;
    @SerializedName("skintone")
    @Expose
    private String skintone;
    @SerializedName("upperwaist")
    @Expose
    private String upperwaist;
    @SerializedName("uppercolor")
    @Expose
    private String uppercolor;
    @SerializedName("lowerwaist")
    @Expose
    private String lowerwaist;
    @SerializedName("lowercolor")
    @Expose
    private String lowercolor;
    @SerializedName("detail_etc")
    @Expose
    private String detailEtc;
    @SerializedName("special")
    @Expose
    private String special;
    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("guest_id")
    @Expose
    private String guestId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reg_date")
    @Expose
    private String regDate;
    @SerializedName("mode")
    @Expose
    private int mode;

    public Lost() {
        this.fname = "";
        this.lname = "";
        this.gender = "M";
        this.age = "";

        this.place = "";
        this.city = "";
        this.district = "";
        this.subdistrict = "";

        this.height = "";
        this.shape = "";
        this.haircolor = "";
        this.hairtype = "";
        this.skintone = "";

        this.upperwaist = "";
        this.uppercolor = "";
        this.lowerwaist = "";
        this.lowercolor = "";

        this.detailEtc = "";
        this.special = "";
        this.status = "";
        this.typeId = "";

    }

    public Lost(String fname, String lname, String gender, String age,
                String city, String district, String subdistrict, String place, String height,
                String shape, String hairtype, String haircolor,
                String upperwaist, String uppercolor, String lowerwaist, String lowercolor, String skintone,
                String type_id, String status, String detail_etc, String special, Integer mode) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.age = age;
        this.place = place;
        this.subdistrict = subdistrict;
        this.district = district;
        this.city = city;
        this.height = height;
        this.shape = shape;
        this.hairtype = hairtype;
        this.haircolor = haircolor;
        this.skintone = skintone;
        this.upperwaist = upperwaist;
        this.uppercolor = uppercolor;
        this.lowerwaist = lowerwaist;
        this.lowercolor = lowercolor;
        this.detailEtc = detail_etc;
        this.special = special;
        this.typeId = type_id;
        this.status = status;
        this.mode = mode;
    }

    public Lost(String id, String pname, String fname, String lname, String gender, String age,
                String city, String district, String subdistrict, String place, String height,
                String shape, String hairtype, String haircolor,
                String upperwaist, String uppercolor, String lowerwaist, String lowercolor, String skintone,
                String type_id, String status, String detail_etc, String special, String regDate) {
        this.id = id;
        this.pname = pname;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.age = age;
        this.place = place;
        this.subdistrict = subdistrict;
        this.district = district;
        this.city = city;
        this.height = height;
        this.shape = shape;
        this.hairtype = hairtype;
        this.haircolor = haircolor;
        this.skintone = skintone;
        this.upperwaist = upperwaist;
        this.uppercolor = uppercolor;
        this.lowerwaist = lowerwaist;
        this.lowercolor = lowercolor;
        this.detailEtc = detail_etc;
        this.special = special;
        this.typeId = type_id;
        this.status = status;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "ชื่อ : %s " +
                        "นามสกลุ : %s\n" +
                        "เพศ : %s  " +
                        "อายุ : %s\n" +
                        "สถานที่ : %s " +
                        "%s " +
                        "%s " +
                        "%s\n" +
                        "ส่วนสูง : %s " +
                        "รูปร่าง : %s\n" +
                        "ผม : %s " +
                        "ผมสี : %s\n" +
                        "สีผิว : %s\n" +
                        "เสื้อผ้า : %s " +
                        "%s " +
                        "%s " +
                        "%s\n" +
                        "ลักษณะทางกายภาพ : %s " +
                        "%s\n" +
                        "ประเภทคนหาย : %s\n" +
//                        "%s\n" +
                        "%s\n",
                this.fname,
                this.lname,
                strGender(),
                this.age,
                this.city,
                this.district,
                this.subdistrict,
                this.place,

                this.height,
                this.shape,

                this.hairtype,
                this.haircolor,
                this.skintone,
                this.upperwaist,
                this.uppercolor,
                this.lowerwaist,
                this.lowercolor,

                this.detailEtc,
                this.special,
                strType(),
//                this.status,
                this.regDate

                );
        return msg;
    }



    public String strDetails(){
        String msg = String.format(
                Locale.getDefault(),
                "ส่วนสูง : %s " +
                        "รูปร่าง : %s\n" +
                        "ผม : %s " +
                        "ผมสี : %s\n" +
                        "สีผิว : %s\n" +
                        "เสื้อผ้า : %s " +
                        "%s " +
                        "%s " +
                        "%s\n" +
                        "ลักษณะทางกายภาพ : %s " +
                        "%s\n" +
                        "ประเภทคนหาย : %s",
//                        "%s\n" +
                this.fname,
                this.lname,
                strGender(),
                this.age,
                this.city,
                this.district,
                this.subdistrict,
                this.place,

                this.height,
                this.shape,

                this.hairtype,
                this.haircolor,
                this.skintone,
                this.upperwaist,
                this.uppercolor,
                this.lowerwaist,
                this.lowercolor,

                this.detailEtc,
                this.special,
                strType()
//                this.status,

        );
        return msg;
    }

    public String strType() {
        if (this.typeId.equals("1")) {return "ลักพาตัว";}
        else if (this.typeId.equals("2")) {return "เด็กพลัดหลง";}
        else if (this.typeId.equals("3")) {return "จิตเวท";}
        else if (this.typeId.equals("4")) {return "พัฒนาการทางสมองช้า";}
        else if (this.typeId.equals("5")) {return "แย้งความปกครองบุตร";}
        else if (this.typeId.equals("6")) {return "สุขภาพจิต";}
        else if (this.typeId.equals("7")) {return "อาการทางสมอง";}
        else if (this.typeId.equals("8")) {return "อาการทางสมอง หลงลืม";}
        else {return "ไม่ระบุ";}
    }

    public String strGender(){
        if(this.gender.equals("M")){ return "ชาย"; }
        else {  return "หญิง"; }
    }

//
//    public Lost(String pname, String fname, String lname, String gender, String age,
//                String place, String subdistrict, String district, String city, String height,
//                String weight, String shape, String hairtype, String haircolor, String skintone,
//                String upperrwaist, String uppercolor, String lowerwaist, String lowercolor,
//                String detailEtc, String special, String typeId, String guestId, String status,
//                String regDate) {
//        super();
//        this.pname = pname;
//        this.fname = fname;
//        this.lname = lname;
//        this.gender = gender;
//        this.age = age;
//        this.place = place;
//        this.subdistrict = subdistrict;
//        this.district = district;
//        this.city = city;
//        this.height = height;
//        this.weight = weight;
//        this.shape = shape;
//        this.hairtype = hairtype;
//        this.haircolor = haircolor;
//        this.skintone = skintone;
//        this.upperrwaist = upperrwaist;
//        this.uppercolor = uppercolor;
//        this.lowerwaist = lowerwaist;
//        this.lowercolor = lowercolor;
//        this.detailEtc = detailEtc;
//        this.special = special;
//        this.typeId = typeId;
//        this.guestId = guestId;
//        this.status = status;
//        this.regDate = regDate;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getHairtype() {
        return hairtype;
    }

    public void setHairtype(String hairtype) {
        this.hairtype = hairtype;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public String getSkintone() {
        return skintone;
    }

    public void setSkintone(String skintone) {
        this.skintone = skintone;
    }

    public String getUpperwaist() {
        return upperwaist;
    }

    public void setUpperwaist(String upperrwaist) {
        this.upperwaist = upperrwaist;
    }

    public String getUppercolor() {
        return uppercolor;
    }

    public void setUppercolor(String uppercolor) {
        this.uppercolor = uppercolor;
    }

    public String getLowerwaist() {
        return lowerwaist;
    }

    public void setLowerwaist(String lowerwaist) {
        this.lowerwaist = lowerwaist;
    }

    public String getLowercolor() {
        return lowercolor;
    }

    public void setLowercolor(String lowercolor) {
        this.lowercolor = lowercolor;
    }

    public String getDetailEtc() {
        return detailEtc;
    }

    public void setDetailEtc(String detail_etc) {
        this.detailEtc = detail_etc;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String type_id) {
        this.typeId = type_id;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getMode() {
        return mode;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Lost(String fname, String lname, String detail, String date) {
        this.fname = fname;
        this.lname = lname;
        this.detailEtc = detail;
        this.regDate = date;
    }


    public static void setListType() {
        listtype.clear();
        listtype.add("ไม่ระบุ");
        listtype.add("ลักพาตัว");
        listtype.add("เด็กพลัดหลง");
        listtype.add("จิตเวท");
        listtype.add("พัฒนาการทางสมองช้า");
        listtype.add("แย้งความปกครองบุตร");
        listtype.add("สุขภาพจิต");
        listtype.add("อาการทางสมอง");
        listtype.add("อาการทางสมอง หลงลืม");
    }

    public static ArrayList<String> getListtype() {
        return listtype;
    }

    public static void setListGender() {
        listgender.clear();
        listgender.add("หญิง");
        listgender.add("ชาย");
    }

    public static ArrayList<String> getListGender() {
        return listgender;
    }

    public static void setListAge() {
        listage.clear();
        listage.add("-");
        listage.add("0-10");
        listage.add("11-15");
        listage.add("16-18");
        listage.add("19-30");
        listage.add("30-60");
        listage.add("60+");
    }

    public static ArrayList<String> getListAge() {
        return listage;
    }

    public static void setListplace() {
        listage.clear();
        listplace.add("-");
        listplace.add("กรุงเทพมหานคร");
        listplace.add("สมุทรปราการ");
        listplace.add("นนทบุรี");
        listplace.add("ปทุมธานี");
        listplace.add("พระนครศรีอยุธยา");
        listplace.add("อ่างทอง");
        listplace.add("ลพบุรี");
        listplace.add("สิงห์บุรี");
        listplace.add("ชัยนาท");
        listplace.add("สระบุรี");
        listplace.add("ชลบุรี");
        listplace.add("ระยอง");
        listplace.add("จันทบุรี");
        listplace.add("ตราด");
        listplace.add("ฉะเชิงเทรา");
        listplace.add("ปราจีนบุรี");
        listplace.add("นครนายก");
        listplace.add("สระแก้ว");
        listplace.add("นครราชสีมา");
        listplace.add("บุรีรัมย์");
        listplace.add("สุรินทร์");
        listplace.add("ศรีสะเกษ");
        listplace.add("อุบลราชธานี");
        listplace.add("ยโสธร");
        listplace.add("ชัยภูมิ");
        listplace.add("อำนาจเจริญ");
        listplace.add("บึงกาฬ");
        listplace.add("หนองบัวลำภู");
        listplace.add("ขอนแก่น");
        listplace.add("อุดรธานี");
        listplace.add("เลย");
        listplace.add("หนองคาย");
        listplace.add("มหาสารคาม");
        listplace.add("ร้อยเอ็ด");
        listplace.add("กาฬสินธุ์");
        listplace.add("สกลนคร");
        listplace.add("นครพนม");
        listplace.add("มุกดาหาร");
        listplace.add("เชียงใหม่");
        listplace.add("ลำพูน");
        listplace.add("ลำปาง");
        listplace.add("อุตรดิตถ์");
        listplace.add("แพร่");
        listplace.add("น่าน");
        listplace.add("พะเยา");
        listplace.add("เชียงราย");
        listplace.add("แม่ฮ่องสอน");
        listplace.add("นครสวรรค์");
        listplace.add("อุทัยธานี");
        listplace.add("กำแพงเพชร");
        listplace.add("ตาก");
        listplace.add("สุโขทัย");
        listplace.add("พิษณุโลก");
        listplace.add("พิจิตร");
        listplace.add("เพชรบูรณ์");
        listplace.add("ราชบุรี");
        listplace.add("กาญจนบุรี");
        listplace.add("สุพรรณบุรี");
        listplace.add("นครปฐม");
        listplace.add("สมุทรสาคร");
        listplace.add("สมุทรสงคราม");
        listplace.add("เพชรบุรี");
        listplace.add("ประจวบคีรีขันธ์");
        listplace.add("นครศรีธรรมราช");
        listplace.add("กระบี่");
        listplace.add("พังงา");
        listplace.add("ภูเก็ต");
        listplace.add("สุราษฎร์ธานี");
        listplace.add("ระนอง");
        listplace.add("ชุมพร");
        listplace.add("สงขลา");
        listplace.add("สตูล");
        listplace.add("ตรัง");
        listplace.add("พัทลุง");
        listplace.add("ปัตตานี");
        listplace.add("ยะลา");
        listplace.add("นราธิวาส");
        Collections.sort(listplace);
    }

    public static ArrayList<String> getListplace() {
        return listplace;
    }

    public static void setListHeight() {
        listheight.clear();
        listheight.add("-");
    }

    public static ArrayList<String> getListHeight() {
        return listheight;
    }

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public void clearData() {
        this.fname = "";
        this.lname = "";
        this.gender = "M";
        this.age = "";

        this.place = "";
        this.city = "";
        this.district = "";
        this.subdistrict = "";

        this.height = "";
        this.shape = "";
        this.haircolor = "";
        this.hairtype = "";
        this.skintone = "";

        this.upperwaist = "";
        this.uppercolor = "";
        this.lowerwaist = "";
        this.lowercolor = "";

        this.detailEtc = "";
        this.special = "";
        this.status = "";
        this.typeId = "";
    }

    public static List<Lost> getLoadDataMain() {
        return loadDataMain;
    }

    public static void setLoadDataMain(List<Lost> loadDataMain) {
        Lost.loadDataMain  = new ArrayList<>();
        Lost.loadDataMain = loadDataMain;
    }
}
