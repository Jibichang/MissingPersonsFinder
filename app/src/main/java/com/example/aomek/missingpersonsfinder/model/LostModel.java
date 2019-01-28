package com.example.aomek.missingpersonsfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Body;

public class LostModel {

    @SerializedName("body")
    @Expose
    private List<Lost> body = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    private LostModel[] lost;

    public LostModel[] getLost() {
        return lost;
    }

    public List<Lost> getBody() {
        return body;
    }

    public void setBody(List<Lost> body) {
        this.body = body;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
