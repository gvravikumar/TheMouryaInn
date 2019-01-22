package com.gvrk.four.themouryainn;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by G V RAVI KUMAR on 6/2/2018.
 */

public class rooms_adapter extends RecyclerView.Adapter<rooms_adapter.MyViewHolder> {

    Context _context;
    ArrayList<rooms_model> _rooms_modelList;
    CustomInterface customInterface;
    boolean toggle = false;

    public rooms_adapter(@NonNull Activity context, ArrayList<rooms_model> rooms_modelList, CustomInterface interfaceCall) {
        this._context = context;
        this._rooms_modelList = rooms_modelList;
        customInterface = interfaceCall;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rooms_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roomName, roomDescripton, roomPrice;
        public EditText startDate, endDate;
        CardView cv;
        Button bookRoom, checkingavailabilitybutton;
        LinearLayout expand_description_view, checkingavailabilitylayout, roomDateLayout;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            cv = itemView.findViewById(R.id.roomCardView);
            iv = itemView.findViewById(R.id.roomImageView);
            roomDescripton = itemView.findViewById(R.id.book_room_description);
            expand_description_view = itemView.findViewById(R.id.expand_description_view);
            roomDateLayout = itemView.findViewById(R.id.roomdateLayout);
            startDate = itemView.findViewById(R.id.roomstartdate);
            endDate = itemView.findViewById(R.id.roomendate);
            checkingavailabilitylayout = itemView.findViewById(R.id.checkingavailabilitylayout);
            checkingavailabilitybutton = itemView.findViewById(R.id.checkingavailabilitybutton);
            roomPrice = itemView.findViewById(R.id.room_price);
            bookRoom = itemView.findViewById(R.id.book_room_button);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.roomName.setText(_rooms_modelList.get(position).getRoomName());
        Glide.with(_context).load(_rooms_modelList.get(position).getRoomImage()).into(holder.iv);
        holder.roomDescripton.setText(_rooms_modelList.get(position).getRoomDesc());
        holder.startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        holder.startDate.setText(sdf.format(myCalendar.getTime()));
                        Global.roomstartingdate = sdf.format(myCalendar.getTime());
                    }

                };

                DatePickerDialog dpd = new DatePickerDialog(_context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });
        holder.endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        holder.endDate.setText(sdf.format(myCalendar.getTime()));
                        Global.roomenddate = sdf.format(myCalendar.getTime());
                    }

                };

                DatePickerDialog dpd = new DatePickerDialog(_context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });
        holder.checkingavailabilitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInterface.checkAvailability(holder.checkingavailabilitylayout, _rooms_modelList.get(position).getRoomName());
            }
        });

        Global.roomprice = _rooms_modelList.get(position).getPricePerDay();
        holder.roomPrice.setText(_rooms_modelList.get(position).getPricePerDay());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Global.roomenddate = null;
                Global.roomstartingdate = null;

                if (!toggle) {
                    holder.expand_description_view.setVisibility(View.VISIBLE);
                    toggle = true;
                } else {
                    holder.expand_description_view.setVisibility(View.GONE);
                    toggle = false;
                }
            }
        });

        holder.bookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.loadProfile(_context);
                Global.amount = _rooms_modelList.get(position).getPricePerDay();
                Global.room_name = _rooms_modelList.get(position).getRoomName();
                customInterface.initPayment(Global.user_email, Global.user_phone_no, Global.amount, Global.purpose, Global.username);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _rooms_modelList.size();
    }

}
