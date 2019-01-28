package com.example.aomek.missingpersonsfinder.retrofit;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.LostModel;
import com.example.aomek.missingpersonsfinder.model.UserGH;
import com.example.aomek.missingpersonsfinder.model.newMember;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
//    @GET("member/getMember.php?id=197")
//    newMember getLost();
    //member/getMember.php?id=197

    @GET("/users/{username}")
    Call<UserGH> getUser(@Path("username") String username);

    @GET("/mpm/api/persons/read_one.php")
    Call<Lost> getLost();

    @GET("/mpm/api/persons/read_one.php")
    Call<LostModel> getLostModel();


//    @GET("KrooKlon/api/member/getMember.php?id={id}")
//    Call<newMember> getUser(@Path("id") String id);


}
