package com.gvrk.four.themouryainn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationsFragment extends Fragment {

    ListView reseravationsListView;
    List<BookingListModel> list;
    reservations_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View reservations_view = inflater.inflate(R.layout.fragment_reservations, container, false);
        reseravationsListView = reservations_view.findViewById(R.id.reservations_list);
        list = new ArrayList<>();
        Global.loadRoomDetails(getActivity());
        list.add(new BookingListModel(Global.roomtype, Global.roomprice, Global.roomstartingdate, Global.roomenddate, Global.roompaid, Global.roomoffersapplied, Global.roomimage));
        adapter = new reservations_adapter(getActivity(), list);
        reseravationsListView.setAdapter(adapter);
        return reservations_view;

//        //Creating a retrofit object
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiClient.url)
//                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
//                .build();
//
//        //creating the api interface
//        ApiClient api = retrofit.create(ApiClient.class);
//
//        //now making the call object
//        //Here we are using the api method that we created inside the api interface
//        Call<List<BookingListModel>> call = api.getBookingList();
//
//        //then finally we are making the call using enqueue()
//        //it takes callback interface as an argument
//        //and callback is having two methods onRespnose() and onFailure
//        //if the request is successfull we will get the correct response and onResponse will be executed
//        //if there is some error we will get inside the onFailure() method
//
//        call.enqueue(new Callback<List<BookingListModel>>() {
//            @Override
//            public void onResponse(Call<List<BookingListModel>> call, Response<List<BookingListModel>> response) {
//                list = response.body();
//                Log.v("reservations",list.get(0).getRoompaid_bookinglistmodel());
//                Log.v("reservations",response.message());
//            }
//
//            @Override
//            public void onFailure(Call<List<BookingListModel>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.v("reservations",t.getMessage());
//            }
//        });

    }
}
