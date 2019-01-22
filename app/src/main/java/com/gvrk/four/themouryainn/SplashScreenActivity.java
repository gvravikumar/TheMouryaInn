package com.gvrk.four.themouryainn;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView iv2;
    Handler handler = new Handler();
    static String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        iv2 = (ImageView)findViewById(R.id.splashImageView);

        new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                iv2.setImageResource(R.drawable.tmilogo);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences getUser = getSharedPreferences("user_info",MODE_PRIVATE);
                        username = getUser.getString("username",null);
                        if(username != null){
                            Global.username = username;
//                            Global.user_profile_photo = Uri.parse(getUser.getString("userphoto",null));
                            Global.user_email = getUser.getString("useremail",null);
                            Global.user_phone_no = getUser.getString("userphone",null);
                            Toast.makeText(SplashScreenActivity.this,"Welcome "+username,Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                },1500);
            }
        }.start();

//        findViewById(R.id.pay).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
//                //callInstamojoPay("gvpraveen.ravi@gmail.com", "9514260290", "1020", "testing", "ravi kumar");
//            }
//        });
        // Call the function callInstamojo to start payment here
    }



}
