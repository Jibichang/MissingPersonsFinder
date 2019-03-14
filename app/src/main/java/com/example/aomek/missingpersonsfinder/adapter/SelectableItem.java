package com.example.aomek.missingpersonsfinder.adapter;

import com.example.aomek.missingpersonsfinder.model.Lost;

public class SelectableItem extends Lost {
    private boolean isSelected = false;

    public SelectableItem(Lost item, boolean isSelected) {
//        super(item.getName(),item.getSurname());
        super(item.getFname(),item.getLname(),item.getGender(),item.getCity(),item.getHeight(),
                item.getShape(),item.getHairtype(),item.getHaircolor(),item.getSkintone(),
                item.getDetailEtc(),item.getTypeId(),item.getStatus(),item.getRegDate());
        this.isSelected = isSelected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}

