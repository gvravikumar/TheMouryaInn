package com.gvrk.four.themouryainn;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by G V RAVI KUMAR on 6/2/2018.
 */

public class restaurants_adapter extends RecyclerView.Adapter<restaurants_adapter.MyViewHolder> {

    Context _context;
    boolean toggle = false;
    CustomInterface rstInterface;
    ArrayList<restaurants_model> _restaurants_modelList;

    public restaurants_adapter(@NonNull Activity context, ArrayList<restaurants_model> restaurants_modelList, CustomInterface interfaceCall) {
        this._context = context;
        this.rstInterface = interfaceCall;
        this._restaurants_modelList = restaurants_modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurants_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MyViewHolder mvh = holder;
        final int temp_position = position;

        holder.restaurantname.setText(_restaurants_modelList.get(position).get_restaurant_name() + " " + _restaurants_modelList.get(position).get_restaurant_type());

        final ImageView[] ivarray = new ImageView[_restaurants_modelList.get(position).getDrawables().length];
        for (int i = 0; i < _restaurants_modelList.get(position).getDrawables().length; i++) {
            ivarray[i] = new ImageView(this._context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5, 5, 5, 5);
            lp.width = 500;
            lp.height = 300;
            ivarray[i].setLayoutParams(lp);
            ivarray[i].setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(_context).load(_restaurants_modelList.get(position).getDrawables()[i]).into(ivarray[i]);
            if (ivarray[i].getParent() != null) {
                ((ViewGroup) ivarray[i].getParent()).removeView(ivarray[i]);
            }
            holder.ll.addView(ivarray[i]);
        }

        holder.res_desc.setText(_restaurants_modelList.get(position).get_restaurant_description());
        Global.amount = _restaurants_modelList.get(position).get_restaurant_price();
        holder.res_price.setText("Price/Day :  " + _restaurants_modelList.get(position).get_restaurant_price());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Global.rstenddate = null;
                Global.rststartingdate = null;

                if (!toggle) {
                    mvh.desc_view.setVisibility(View.VISIBLE);
                    toggle = true;
                } else {
                    mvh.desc_view.setVisibility(View.GONE);
                    toggle = false;
                }
            }
        });

        holder.startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(java.util.Calendar.YEAR, year);
                        myCalendar.set(java.util.Calendar.MONTH, monthOfYear);
                        myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        holder.startDate.setText(sdf.format(myCalendar.getTime()));
                        Global.rststartingdate = sdf.format(myCalendar.getTime());
                    }
                };
                DatePickerDialog dpd = new DatePickerDialog(_context, date, myCalendar
                        .get(java.util.Calendar.YEAR), myCalendar.get(java.util.Calendar.MONTH),
                        myCalendar.get(java.util.Calendar.DAY_OF_MONTH));
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
                        myCalendar.set(java.util.Calendar.YEAR, year);
                        myCalendar.set(java.util.Calendar.MONTH, monthOfYear);
                        myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        holder.endDate.setText(sdf.format(myCalendar.getTime()));
                        Global.rstenddate = sdf.format(myCalendar.getTime());
                    }
                };
                DatePickerDialog dpd = new DatePickerDialog(_context, date, myCalendar
                        .get(java.util.Calendar.YEAR), myCalendar.get(java.util.Calendar.MONTH),
                        myCalendar.get(java.util.Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });

        holder.res_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rstInterface.checkAvailability(mvh.checkingll, _restaurants_modelList.get(temp_position).get_restaurant_name());
            }
        });

        holder.res_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.loadProfile(_context);
                Global.rstname = _restaurants_modelList.get(position).get_restaurant_name();
                Global.rstprice = _restaurants_modelList.get(position).get_restaurant_price();
                Global.rstimage = _restaurants_modelList.get(position).getDrawables()[position];

                rstInterface.initPayment(Global.user_email, Global.user_phone_no, Global.amount, "booking Restaurant", Global.username);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurantname, availability, res_desc, res_price;
        public EditText startDate, endDate;
        public LinearLayout ll, desc_view, checkingll, dateLayout;
        public Button res_availability, res_book;

        public MyViewHolder(View view) {
            super(view);
            restaurantname = view.findViewById(R.id.restaurantName);
            availability = view.findViewById(R.id.restaurant_availability);
            res_desc = view.findViewById(R.id.book_restaurant_description);
            res_price = view.findViewById(R.id.rest_room_price);
            desc_view = view.findViewById(R.id.expand_restaurantdescription_view);
            ll = view.findViewById(R.id.aaharcardview);
            res_availability = view.findViewById(R.id.checkingrestaurantavailabilitybutton);
            res_book = view.findViewById(R.id.book_restaurant_button);
            checkingll = view.findViewById(R.id.checkingrstavailabilitylayout);
            dateLayout = view.findViewById(R.id.dateLayout);
            startDate = view.findViewById(R.id.rststartdate);
            endDate = view.findViewById(R.id.rstendate);
        }
    }

    @Override
    public int getItemCount() {
        return _restaurants_modelList.size();
    }
}