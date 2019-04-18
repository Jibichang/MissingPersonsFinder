package com.example.aomek.missingpersonsfinder.adapter;

import android.view.View;

import com.example.aomek.missingpersonsfinder.model.Lost;

public interface ItemClickListener {
//    void onItemClick(View view, int position);
    public Lost selectableItem = new Lost();
    public Lost selectableItemDetail = new Lost();
}