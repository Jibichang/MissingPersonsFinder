package com.example.aomek.missingpersonsfinder.retrofit;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.model.UserGH;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
//    @GET("member/getMember.php?id=197")
    //member/getMember.php?id=197

    @GET("/users/{username}")
    Call<UserGH> getUser(@Path("username") String username);

    @GET("/plost/api/persons/read_one.php")
    Call<Lost> getLost();

    @GET("/plost/api/persons/read_one.php")
    Call<LostModel> getLostModel();

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/search.php")
    Call<LostModel> searchLost(@Body Lost body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/read_one.php")
    Call<LostModel> searchName(@Body Lost body);

    //            @Field("fname") String fname,
//            @Field("lname") String lname,
//            @Field("gender") String gender,
//            @Field("city") String city,
//            @Field("height") String height,
//            @Field("shape") String shape,
//            @Field("hairtype") String hairtype,
//            @Field("haircolor") String haircolor,
//            @Field("skintone") String skintone,
//            @Field("type_id") String type_id,
//            @Field("status") String status,
//            @Field("detail_etc") String detail_etc

//    public void searchLost(
//            @Field("name") String name,
//            @Field("username") String username,
//            @Field("password") String password,
//            @Field("email") String email,
//            Callback<Response> callback);



}
