package com.gvrk.four.themouryainn;

import android.graphics.drawable.Drawable;

/**
 * Created by G V RAVI KUMAR on 6/2/2018.
 */

public class restaurants_model {

    String _restaurant_description, _restaurant_price, _restaurant_type, _restaurant_name;
    int[] drawables;

    public restaurants_model(String room_description, String price, String type, String name, int[] drawables) {
        this._restaurant_description = room_description;
        this._restaurant_price = price;
        this._restaurant_type = type;
        this._restaurant_name = name;
        this.drawables = drawables;
    }

    public String get_restaurant_description() {
        return _restaurant_description;
    }

    public String get_restaurant_price() {
        return _restaurant_price;
    }

    public String get_restaurant_type() {
        return _restaurant_type;
    }

    public String get_restaurant_name() {
        return _restaurant_name;
    }

    public int[] getDrawables() {
        return drawables;
    }
}
