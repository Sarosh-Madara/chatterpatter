package com.example.saroshmadara.chatterpatter.models;

/**
 * Created by Sarosh Madara on 28-09-2015.
 */
public class DrawerItem {
    String ItemName;
    int imgResID;
    String title;
    boolean isSpinner;

    public boolean isSpinner() {
        return isSpinner;
    }

    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }
    public DrawerItem(boolean isSpinner) {
        this(null, 0);
        this.isSpinner = isSpinner;
    }

    public DrawerItem(String title) {
        this(null, 0);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}
