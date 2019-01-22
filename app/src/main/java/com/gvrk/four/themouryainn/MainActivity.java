package com.gvrk.four.themouryainn;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements paymentDoneInterface{

    ViewPager vp;
    BottomNavigationView navigation;
    Fragment home_fragment, reservations_fragment, rooms_fragment, restaurants_fragment, settings_fragment;
    MenuItem previousMenuItem;
    boolean doubleBackToExit = false;
    ViewPagerAdapter vpa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpa = new ViewPagerAdapter(getSupportFragmentManager());

        vp = (ViewPager)findViewById(R.id.fragment_container);
        vp.addOnPageChangeListener(mOnViewPagerPageListener);
        loadViewPagerFragment(vp);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private ViewPager.OnPageChangeListener mOnViewPagerPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            switch (position) {
//                case 0:
//                    getSupportActionBar().setTitle("Services Offered");
//                    //services fragment
//                    break;
//                case 1:
//                    getSupportActionBar().setTitle("Book Restaurants");
//                    break;
//                case 2:
//                    getSupportActionBar().setTitle("Book Rooms");
//                    break;
//                case 3:
//                    getSupportActionBar().setTitle("Reservations/Offers");
//                    break;
//                case 4:
//                    getSupportActionBar().setTitle("Settings");
//                    break;
//            }
        }

        @Override
        public void onPageSelected(int position) {
            if (previousMenuItem != null) {
                previousMenuItem.setChecked(false);
            } else {
                navigation.getMenu().getItem(position).setChecked(false);
            }
            navigation.getMenu().getItem(position).setChecked(true);
            previousMenuItem = navigation.getMenu().getItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_menu:
                    vp.setCurrentItem(0);
                    //getSupportActionBar().setTitle("Services Offered");
                    //services fragment
                    break;
                case R.id.restaurants_menu:
                    vp.setCurrentItem(1);
//                    getSupportActionBar().setTitle("Book Restaurants");
                    break;
                case R.id.rooms_menu:
                    //vp.setCurrentItem(2);
//                    getSupportActionBar().setTitle("Book Rooms");
                    break;
                case R.id.reservations_menu:
                    vp.setCurrentItem(3);
//                    getSupportActionBar().setTitle("Reservations/Offers");
                    break;
                case R.id.settings_menu:
                    vp.setCurrentItem(4);
//                    getSupportActionBar().setTitle( "Settings");
                    break;
            }
            return false;
        }
    };

    public void loadViewPagerFragment(ViewPager vp) {
        home_fragment = new HomeFragment();
        restaurants_fragment = new RestaurantsFragment();
        rooms_fragment = new RoomsFragment();
        reservations_fragment = new ReservationsFragment();
        settings_fragment = new SettingsFragment();


        vpa.addFragment(home_fragment);
        vpa.addFragment(restaurants_fragment);
        vpa.addFragment(rooms_fragment);
        vpa.addFragment(reservations_fragment);
        vpa.addFragment(settings_fragment);
        vp.setAdapter(vpa);
    }

    @Override
    public void movingFragment() {
        vp.setCurrentItem(3,true);
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExit){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExit = true;
        Toast.makeText(this, "Please Click Back again to Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              doubleBackToExit = false;
            }
        },2000);
    }
}
