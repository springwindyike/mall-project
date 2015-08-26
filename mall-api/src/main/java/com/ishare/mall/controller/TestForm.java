package com.ishare.mall.controller;

import com.ishare.mall.core.status.Gender;

/**
 * Created by YinLin on 2015/8/26.
 * Description :
 * Version 1.0
 */
public class TestForm {
    private String id;
    private Gender gender;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
