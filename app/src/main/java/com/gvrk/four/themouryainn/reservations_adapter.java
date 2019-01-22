package com.gvrk.four.themouryainn;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G V RAVI KUMAR on 6/2/2018.
 */

public class reservations_adapter extends ArrayAdapter<BookingListModel> {

    Context c;
    List<BookingListModel> reservationsModels;

    public reservations_adapter(@NonNull Context context, @NonNull List<BookingListModel> objects) {
        super(context, R.layout.custom_reservations_layout, objects);
        this.c = context;
        this.reservationsModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_reservations_layout = li.inflate(R.layout.custom_reservations_layout, null);
        if(reservationsModels.size() > 0){
            //Log.v("reser view",reservationsModels.get(0).getRoomtype_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roomtype)).setText(reservationsModels.get(position).getRoomtype_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roomprice)).setText(reservationsModels.get(position).getRoomprice_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roomstartdate)).setText(reservationsModels.get(position).getRoomstartingdate_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roomenddate)).setText(reservationsModels.get(position).getRoomenddate_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roompaymentstatus)).setText(reservationsModels.get(position).getRoompaid_bookinglistmodel());
            ((TextView)custom_reservations_layout.findViewById(R.id.reservations_roomoffersapplied)).setText(reservationsModels.get(position).getRoomoffersapplied_bookinglistmodel());
            ((ImageView)custom_reservations_layout.findViewById(R.id.rstBookedIV)).setImageResource(reservationsModels.get(position).getRoomimage_bookinglistmodel());
        }
        return custom_reservations_layout;
    }
}
