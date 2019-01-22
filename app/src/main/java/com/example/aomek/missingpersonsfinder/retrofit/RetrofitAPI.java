package com.example.aomek.missingpersonsfinder.retrofit;
import com.example.aomek.missingpersonsfinder.model.Lost;
import com.example.aomek.missingpersonsfinder.model.UserGH;
import com.example.aomek.missingpersonsfinder.model.newMember;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
//    @GET("member/getMember.php?id=197")
//    newMember getLost();
    //member/getMember.php?id=197

    @GET("/users/{username}")
    Call<UserGH> getUser(@Path("username") String username);

    @GET("/missing-person-master/lost.json")
    Call<Lost> getLost();

//    @GET("KrooKlon/api/member/getMember.php?id={id}")
//    Call<newMember> getUser(@Path("id") String id);


}
