package com.example.aomek.missingpersonsfinder.adapter;

import android.view.View;

import com.example.aomek.missingpersonsfinder.model.Lost;

public interface ItemClickListener {
//    void onItemClick(View view, int position);

    //select item
    Lost selectableItem = new Lost();

    //keep detail text
    Lost selectableItemDetail = new Lost();

    //scrolling content
    Lost lostContent = new Lost();
}