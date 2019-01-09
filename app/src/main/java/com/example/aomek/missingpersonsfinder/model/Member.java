package com.example.aomek.missingpersonsfinder.model;

import java.util.Locale;

public class Member {

    private final long _id;
    private final String name;
    private final String password;
    private final String email;
    private final String place;
    private final String phone;


    public Member(long _id, String name, String password, String email,
                     String place, String phone) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.place = place;
        this.phone = phone;
    }

    public Member(long _id, String name, String email,
                  String place, String phone) {
        this._id = _id;
        this.name = name;
        this.password = null;
        this.email = email;
        this.place = place;
        this.phone = phone;
    }

    public Member(String password, String email) {
        this._id = 0;
        this.password = password;
        this.email = email;
        this.name = email;
        this.place = null;
        this.phone = null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)",
                this.name,
                this.email,
                this.place,
                this.phone
        );
        return msg;
    }
}
