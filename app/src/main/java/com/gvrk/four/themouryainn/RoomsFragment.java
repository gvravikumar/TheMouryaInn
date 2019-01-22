package com.gvrk.four.themouryainn;


import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment implements CustomInterface {

    FrameLayout fl;
    RecyclerView lv;
    rooms_adapter adapter;
    ArrayList<rooms_model> rooms_modelList = new ArrayList<>();
    InstamojoPay instamojoPay;
    paymentDoneInterface pdi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        rooms_modelList.clear();
        pdi = (paymentDoneInterface) getActivity();
        View v = inflater.inflate(R.layout.fragment_rooms, container, false);
        fl = v.findViewById(R.id.roomFragmentID);
        lv = v.findViewById(R.id.room_list);
        RecyclerView.LayoutManager mLM = new LinearLayoutManager(getActivity());
        lv.setItemAnimator(new DefaultItemAnimator());
        lv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        lv.setLayoutManager(mLM);
        rooms_modelList.add(new rooms_model("STANDARD NON A/C 2PAX", "Bathroom\t" +
                "Bottle of Water is provided in your room.\n" +
                "Cable channels\t" +
                "Dining table\n" +
                "Ceiling Fan\t" +
                "Flat-screen TV\n" +
                "Free toiletries\t" +
                "Linens\n" +
                "Mini Refrigerator\t" +
                "Safe\n" +
                "Shampoo\t" +
                "Shower\n" +
                "Soundproof\t" +
                "Telephone\n" +
                "Tile/Marble floor\t" +
                "Toilet\n" +
                "Toothbrush\t" +
                "Towels\n" +
                "Wake-up service\t" +
                "Daily Newspaper\n" +
                "Complimentary 20Min. Foot Massage\t" +
                "Complimentary Breakfast\n" +
                "Free Wi-Fi Internet\t" +
                "24 hrs Checkin / Checkout Facility\n" +
                "24 hrs. hot and cold water\t" +
                "Shaving Mirror\n" +
                "Daily Room cleaning", "1750", R.drawable.room1, "SUIT"));
        rooms_modelList.add(new rooms_model("STANDARD A/C 2PAX", "aaaaaaaaaaaaaaaa", "2200", R.drawable.room1, "SUIT"));
        rooms_modelList.add(new rooms_model("CLUB SUIT A/C", "aaaaaaaaaaaaaaaa", "4200", R.drawable.room1, "SUIT"));
        rooms_modelList.add(new rooms_model("SUIT A/C", "aaaaaaaaaaaaaaaa", "3300", R.drawable.room2, "SUIT"));
        rooms_modelList.add(new rooms_model("STANDARD NON A/C 1PAX", "aaaaaaaaaaaaaaaa", "2000", R.drawable.standardnonactwopax, "SUIT"));
        rooms_modelList.add(new rooms_model("STANDARD NON A/C 1PAX", "aaaaaaaaaaaaaaaa", "1600", R.drawable.standardactwopax, "SUIT"));
        adapter = new rooms_adapter(getActivity(), rooms_modelList, this);
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
        } else if (Global.roomstartingdate == null || Global.roomenddate == null) {
            Snackbar.make(fl, "Start & End dates of Booking are needed", 2000).show();
        } else if (email == null || phoneno == null) {
            Snackbar.make(fl, "Email/Phone No is required", 3000).setAction("Fill", new View.OnClickListener() {
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

    @Override
    public void checkAvailability(View v, String name) {
        ProgressBar pb = v.findViewById(R.id.checkingavailabilityprogressbar);
        pb.setVisibility(View.VISIBLE);
        TextView tv = (v.findViewById(R.id.noofroomsavailable));
        tv.setText(name);
    }

    public void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        Global.user_email = email;
        Global.user_phone_no = phone;
        Global.amount = amount;
        Global.purpose = purpose;
        Global.buyername = buyername;

        instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
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
                        Global.saveRoomDetails(getActivity(), Global.room_name, Global.roomprice, Global.roomstartingdate, Global.roomenddate, Global.roompaid, Global.roomoffersapplied, Global.roomimage);
                        pdi.movingFragment();
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
                        MyNotificationManager.getInstance(getActivity()).displayNotification("Greetings", "Hello how are you?");
                    }
                }).show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getActivity(), "Failed: " + reason, Toast.LENGTH_LONG).show();
            }
        };
    }
}