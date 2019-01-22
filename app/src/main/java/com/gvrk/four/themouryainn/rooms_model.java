package com.gvrk.four.themouryainn;

import android.graphics.drawable.Drawable;

/**
 * Created by G V RAVI KUMAR on 6/9/2018.
 */

public class rooms_model {

    String roomName,roomDesc, pricePerDay, type;
    int roomImage;

    public rooms_model(String roomName, String roomDesc, String pricePerDay, int roomImage, String type) {
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.pricePerDay = pricePerDay;
        this.roomImage = roomImage;
        this.type = type;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public int getRoomImage() {
        return roomImage;
    }

    public String getType(){
        return type;
    }
}
