package com.gvrk.four.themouryainn;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.telephony.CarrierConfigManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView restaurant, complimentarybreakfastcardview, poolcardview, traveldeskcardview, laundrycardview, servicecardview, paymentcardview, freewificardview, doctorcardview, profile;
    TextView username, usermail, userphone;
    ImageView profile_photo;

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        username = v.findViewById(R.id.username_tv);
        usermail = v.findViewById(R.id.usermail_tv);
        profile_photo = v.findViewById(R.id.profile_photo);
        Global.loadProfile(getActivity());
        if (Global.user_email != null) {
            username.setText(Global.username);
            usermail.setText(Global.user_email);
            Glide.with(this).load(Global.user_profile_photo).into(profile_photo);

        } else {
            v.findViewById(R.id.profile_view).setVisibility(View.GONE);
            profile_photo.setVisibility(View.INVISIBLE);
            username.setVisibility(View.INVISIBLE);
            usermail.setVisibility(View.INVISIBLE);
        }

        complimentarybreakfastcardview = v.findViewById(R.id.complimentarycardview);
        poolcardview = v.findViewById(R.id.poolcardview);
        traveldeskcardview = v.findViewById(R.id.traveldeskcardview);
        laundrycardview = v.findViewById(R.id.laundrycardview);
        servicecardview = v.findViewById(R.id.servicecardview);
        paymentcardview = v.findViewById(R.id.paymentcardview);
        freewificardview = v.findViewById(R.id.freewificardview);
        doctorcardview = v.findViewById(R.id.doctorcardview);
        return v;
    }
}
