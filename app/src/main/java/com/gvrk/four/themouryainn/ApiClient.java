package com.gvrk.four.themouryainn;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by G V RAVI KUMAR on 6/3/2018.
 */

public interface ApiClient {
    String url = "https://whereismybus.000webhostapp.com/php/";

    @GET("RoomBookingList.php")
    void getBookingList(@Query("q")String param1, Callback<BookingListModel> param2);
    Call<List<BookingListModel>> getBookingList();
}
