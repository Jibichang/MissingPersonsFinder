package com.example.aomek.missingpersonsfinder.retrofit;
import com.example.aomek.missingpersonsfinder.model.Feedback;
import com.example.aomek.missingpersonsfinder.model.Guest;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.model.UserGH;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RetrofitAPI {

//    @GET("/users/{username}")
//    Call<UserGH> getUser(@Path("username") String username);

    @GET("/plost/api/persons/read_one.php")
    Call<Lost> getLost();

    @GET("/plost/api/persons/read_one.php")
    Call<LostModel> getLostModel();

    //Search
    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/search.php")
    Call<LostModel> searchLost(@Body Lost body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/read_one.php")
    Call<LostModel> searchName(@Body Lost body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/searchDis.php")
    Call<LostModel> searchDistrict(@Body Lost body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/searchSub.php")
    Call<LostModel> searchSubDistrict(@Body Lost body);

    // create lost
    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/create.php")
    Call<Lost> createLost(@Body Lost body);

    // delete lost
    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/delete.php")
    Call<Lost> deleteLost(@Body Lost body);

    // update lost
    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/update.php")
    Call<Lost> updateLost(@Body Lost body);

    // Feedback
    @Headers("Content-Type: application/json")
    @POST("/plost/api/feedback/create.php")
    Call<Feedback> addFeedback(@Body Feedback body);

    // read Feedback
    @Headers("Content-Type: application/json")
    @POST("/plost/api/feedback/read.php")
    Call<LostModel> readFeedback(@Body Feedback body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/feedback/delete_all.php")
    Call<LostModel> deleteAllFeedback(@Body Feedback body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/feedback/delete.php")
    Call<LostModel> deleteFeedback(@Body Feedback body);

    // Guest Lost
    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/read_guest.php")
    Call<LostModel> searchGuest(@Body Guest body);

    // Login
    @Headers("Content-Type: application/json")
    @POST("/plost/api/member/guest_login.php")
    Call<Guest> Login(@Body Guest body);

    // Register
    @Headers("Content-Type: application/json")
    @POST("/plost/api/member/create.php")
    Call<Guest> Register(@Body Guest body);

    // Update User
    @Headers("Content-Type: application/json")
    @POST("/plost/api/member/update_user.php")
    Call<Guest> updateUser(@Body Guest body);

    // contact User
    @Headers("Content-Type: application/json")
    @POST("/plost/api/member/get_contact.php")
    Call<Guest> contactUser(@Body Guest body);

    @Headers("Content-Type: application/json")
    @POST("/plost/api/persons/update_status.php")
    Call<Lost> approveLost(@Body Lost body);

    //upload image
    @POST("/plost/api/imgupload/upload.php")
    Call<Lost> uploadImage(@Body Lost body);

    //image from url
    @GET
    Call<ResponseBody> fetchCaptcha(@Url String url);

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
