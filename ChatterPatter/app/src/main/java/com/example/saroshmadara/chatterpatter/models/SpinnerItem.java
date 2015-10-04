package com.example.saroshmadara.chatterpatter.models;

/**
 * Created by Sarosh Madara on 03-10-2015.
 */
public class SpinnerItem {
    private int drawableResID;


    public int getDrawableResID() {
        return drawableResID;
    }

    public void setDrawableResID(int drawableResID) {
        this.drawableResID = drawableResID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name,email;

    public SpinnerItem(String name, int drawableResID, String email) {
        this.name = name;
        this.drawableResID = drawableResID;
        this.email = email;
    }
}
