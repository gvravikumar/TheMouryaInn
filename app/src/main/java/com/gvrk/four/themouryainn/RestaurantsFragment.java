package com.gvrk.four.themouryainn;


import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment implements CustomInterface {

    FrameLayout fl;
    RecyclerView lv;
    restaurants_adapter adapter;
    ArrayList<restaurants_model> list = new ArrayList<>();
    InstamojoPay instamojoPay = new InstamojoPay();
    IntentFilter filter;
    paymentDoneInterface pdi;

    String aahar = "AAhar Restaurant \n A pure vegetarian restaurant serving authentic south Indian, north Indian @ Chinese dishes. It shall be a “treasure of tastes” and a variety of lipsmacking dishes at your choice to choose…",
            garden = "Garden Restaurant \n A venue for the pleasant events like get-together, family groups meet, birthday, anniversaries and other joyful events. It is a place at the poolside, surrounded by a melody of water flow to cheer the children and family on the evening or under flood lights…",
            darbar = "Darbar Hall A/C \n Banquet hall for conference, seminar, get to-gether parties with all facilities and services like digital podium, lcd, ohp and slide projector…etc.",
            vibhu = "Vibhu \n Full fledged to suit marriages, anniversaries, corporate gatherings, product launching, award ceremonies and other social functions…etc with capacity of 1000 pax…",
            shreearya = "Shree Aarya A/C \n Well decorated hall for all functions and marriages with capacity 1000 pax…";

    int[] aaharimage = new int[]{R.drawable.aahar1, R.drawable.aahar2, R.drawable.aahar3, R.drawable.aahar4, R.drawable.aahar5},
            gardenimage = new int[]{R.drawable.garden1, R.drawable.garden2}, darbarimage = new int[]{R.drawable.darbar1, R.drawable.darbar2},
            vibhuimage = new int[]{R.drawable.vibhu1, R.drawable.vibhu2, R.drawable.vibhu3, R.drawable.vibhu4, R.drawable.vibhu5, R.drawable.vibhu6},
            shreearyaimage = new int[]{R.drawable.shreearya1, R.drawable.shreearya2, R.drawable.shreearya3, R.drawable.shreearya4};

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        Global.rststartingdate = null;
        Global.rstenddate = null;
        pdi = (paymentDoneInterface) getActivity();
        View v = inflater.inflate(R.layout.fragment_restaurants, container, false);
        lv = v.findViewById(R.id.restaurants_list);
        fl = v.findViewById(R.id.fragment_restaurant);
        RecyclerView.LayoutManager mLM = new LinearLayoutManager(getActivity());
        lv.setItemAnimator(new DefaultItemAnimator());
        lv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        lv.setLayoutManager(mLM);
        list.clear();

        list.add(new restaurants_model(aahar, "1125", "AC", "AAhar Restaurant", aaharimage));
        list.add(new restaurants_model(garden, "1125", "non AC", "Garden Restaurant", gardenimage));
        list.add(new restaurants_model(darbar, "1125", "AC", "Darbar Restaurant", darbarimage));
        list.add(new restaurants_model(shreearya, "1125", "AC", "ShreeArya Restaurant", shreearyaimage));
        list.add(new restaurants_model(vibhu, "1125", "non AC", "Vibhu Restaurant", vibhuimage));
        adapter = new restaurants_adapter(getActivity(), list, this);
        lv.setAdapter(adapter);
        return v;
    }

    @Override
    public void initPayment(String email, String phoneno, String amount, String purpose, String username) {
        final View v1 = getActivity().getLayoutInflater().inflate(R.layout.fill_details, null);
        final EditText e = v1.findViewById(R.id.noprofileemail);
        final EditText p = v1.findViewById(R.id.noprofilephone);
        final EditText u = v1.findViewById(R.id.noprofileusername);
        if (!Global.isInternetOn(getActivity())) {
            Snackbar.make(fl, "Please check Internet Connection", Snackbar.LENGTH_SHORT).show();
        } else if (Global.rststartingdate == null || Global.rstenddate == null) {
            Snackbar.make(fl, "Start & End dates of Booking are needed", 2000).show();
        } else if (email == null || phoneno == null) {
            Snackbar.make(fl, "Email/Phone No is missing", 3000).setAction("Fill", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder abc = new AlertDialog.Builder(getActivity());
                    final AlertDialog ad = abc.create();
                    ad.setView(v1);
                    ad.show();
                    if(Global.user_email != null){
                        e.setText(Global.user_email);
                        e.setEnabled(false);
                    }
                    if(Global.user_phone_no != null){
                        p.setText(Global.user_phone_no);
                        p.setEnabled(false);
                    }
                    if(Global.username != null){
                        u.setText(Global.username);
                        u.setEnabled(false);
                    }
                    v1.findViewById(R.id.savePaymentButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (p.getText().toString().trim().length() > 0) {
                                Global.user_phone_no = ((EditText) v1.findViewById(R.id.noprofilephone)).getText().toString().trim();
                            } else {
                                v1.findViewById(R.id.phoneNoError).setVisibility(View.VISIBLE);
                            }
                            if (e.getText().toString().trim().length() > 0) {
                                Global.user_email = ((EditText) v1.findViewById(R.id.noprofileemail)).getText().toString().trim();
                            } else {
                                v1.findViewById(R.id.emailError).setVisibility(View.VISIBLE);
                            }
                            if (u.getText().toString().trim().length() > 0) {
                                Global.username = ((EditText) v1.findViewById(R.id.noprofileusername)).getText().toString().trim();
                            } else {
                                v1.findViewById(R.id.usernameError).setVisibility(View.VISIBLE);
                            }
                            if (Global.user_phone_no != null && Global.user_email != null && Global.username != null) {
                                Global.updateProfile(getActivity(), Global.username, Global.user_email, Global.user_phone_no, Global.user_profile_photo);
                                ad.dismiss();
                                callInstamojoPay(Global.user_email, Global.user_phone_no, Global.amount, Global.purpose, Global.username);
                            }
                        }
                    });
                    v1.findViewById(R.id.PaymentButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (p.getText().toString().trim().length() > 0) {
                                Global.user_phone_no = ((EditText) v1.findViewById(R.id.noprofilephone)).getText().toString().trim();
                                v1.findViewById(R.id.phoneNoError).setVisibility(View.GONE);
                            } else {
                                v1.findViewById(R.id.phoneNoError).setVisibility(View.VISIBLE);
                            }
                            if (e.getText().toString().trim().length() > 0) {
                                Global.user_email = ((EditText) v1.findViewById(R.id.noprofileemail)).getText().toString().trim();
                                v1.findViewById(R.id.emailError).setVisibility(View.GONE);
                            } else {
                                v1.findViewById(R.id.emailError).setVisibility(View.VISIBLE);
                            }
                            if (u.getText().toString().trim().length() > 0) {
                                Global.username = ((EditText) v1.findViewById(R.id.noprofileusername)).getText().toString().trim();
                                v1.findViewById(R.id.usernameError).setVisibility(View.GONE);
                            } else {
                                v1.findViewById(R.id.usernameError).setVisibility(View.VISIBLE);
                            }
                            if (Global.user_phone_no != null && Global.user_email != null && Global.username != null) {
                                ad.dismiss();
                                callInstamojoPay(Global.user_email, Global.user_phone_no, Global.amount, Global.purpose, Global.username);
                            }
                        }
                    });
                }
            }).show();
        } else if (Global.user_phone_no != null && Global.user_email != null && Global.username != null) {
            callInstamojoPay(Global.user_email, Global.user_phone_no, Global.amount, Global.purpose, Global.username);
        }
    }

    public void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {

        filter = new IntentFilter("ai.devsupport.instamojo");
        getActivity().registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(getActivity(), pay, listener);
    }

    InstapayListener listener;

    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                getActivity().unregisterReceiver(instamojoPay);
                Log.v("response", response);
                Snackbar.make(fl, "Payment Success", 3000).setAction("View Room Details", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Global.saveRoomDetails(getActivity(),Global.rstname,Global.rstprice,Global.rststartingdate,Global.rstenddate,Global.rstpaid,Global.rstoffersapplied,Global.rstimage);
                        pdi.movingFragment();
                    }
                }).show();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationManager mNotificationManager =
                            (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
                    mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                    mChannel.enableLights(true);
                    mChannel.setLightColor(Color.RED);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    mNotificationManager.createNotificationChannel(mChannel);
                }
                MyNotificationManager.getInstance(getActivity()).displayNotification("The Mourya Inn", "Congratulations Room has been successfully booked");
                Global.reset();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getActivity(), "Failed: " + reason, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void checkAvailability(View v, String name) {
        ProgressBar pb = v.findViewById(R.id.checkingrestaurantavailabilityprogressbar);
        pb.setVisibility(View.VISIBLE);
        TextView tv = (v.findViewById(R.id.restaurant_availability));
        tv.setText(name);
    }
}