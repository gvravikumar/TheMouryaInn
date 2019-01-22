package com.gvrk.four.themouryainn;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by G V RAVI KUMAR on 5/28/2018.
 */

public class Global {

    public static String username = null;
    public static String user_email = null;
    public static String user_phone_no = null;
    public static String user_profile_photo = null;

    public static String amount = null;
    public static String buyername = null;

    public static String user_type = null;
    public static String purpose = "booking rooms";

    public static String rstname = null;
    public static String rstprice = null;
    public static String rststartingdate = null;
    public static String rstenddate = null;
    public static String rstpaid = null;
    public static String rstoffersapplied = null;
    public static int rstimage;

    public static String room_name = null;
    public static String roomtype = null;
    public static String roomprice = null;
    public static String roomstartingdate = null;
    public static String roomenddate = null;
    public static String roompaid = null;
    public static String roomoffersapplied = null;
    public static int roomimage;

    public static void loadProfile(Context c) {
        SharedPreferences abc = c.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        username = abc.getString("username", null);
        user_email = abc.getString("useremail", null);
        user_phone_no = abc.getString("userphone", null);
        user_profile_photo = abc.getString("userphoto", null);
    }

    public static void updateProfile(Context c, String username, String usermail, String phone, String uri) {
        SharedPreferences up = c.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor abc = up.edit();
        abc.putString("username", username);
        abc.putString("useremail", usermail);
        abc.putString("userphone", phone);
        if (user_profile_photo == null) {
            abc.putString("userphoto", uri);
        }
        abc.commit();
    }

    public static void loadRoomDetails(Context c) {
        SharedPreferences abc = c.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        roomtype = abc.getString("roomtype", null);
        roomprice = abc.getString("roomprice", null);
        roomstartingdate = abc.getString("roomstartingdate", null);
        roomenddate = abc.getString("roomenddate", null);
        roompaid = abc.getString("roompaid", null);
        roomimage = abc.getInt("roomimage", 1);
        roomoffersapplied = abc.getString("roomoffersapplied", null);
    }

    public static void saveRoomDetails(Context c, String roomtype, String roomprice, String roomstartingdate, String roomenddate, String roompaid, String roomoffersapplied, int roomimage) {
        SharedPreferences up = c.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor abc = up.edit();
        abc.putString("roomtype", roomtype);
        abc.putString("roomprice", roomprice);
        abc.putString("roomstartingdate", roomstartingdate);
        abc.putString("roomenddate", roomenddate);
        abc.putString("roompaid", roompaid);
        abc.putString("roomoffersapplied", roomoffersapplied);
        abc.putInt("roomimage", roomimage);
        abc.commit();
    }

    public static void reset() {
        username = null;
        user_email = null;
        user_phone_no = null;
        user_profile_photo = null;

        amount = null;
        buyername = null;

        user_type = null;
        purpose = "booking rooms";

        rstname = null;
        rstprice = null;
        rststartingdate = null;
        rstenddate = null;
        rstpaid = null;
        rstoffersapplied = null;
        rstimage = 0;

        room_name = null;
        roomtype = null;
        roomprice = null;
        roomstartingdate = null;
        roomenddate = null;
        roompaid = null;
        roomoffersapplied = null;
        roomimage = 0;
    }

    public static final boolean isInternetOn(Context c) {
        ConnectivityManager connec = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}
