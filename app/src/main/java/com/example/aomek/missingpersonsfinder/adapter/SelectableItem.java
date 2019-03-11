package com.example.aomek.missingpersonsfinder.adapter;

import com.example.aomek.missingpersonsfinder.model.Details;

public class SelectableItem extends Details {
    private boolean isSelected = false;

    public SelectableItem(Details item,boolean isSelected) {
//        super(item.getName(),item.getSurname());
        this.isSelected = isSelected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

