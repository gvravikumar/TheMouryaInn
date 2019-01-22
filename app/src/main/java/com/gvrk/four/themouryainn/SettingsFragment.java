package com.gvrk.four.themouryainn;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    CardView wifi, feedback, complaint, contactUs, aboutUs, exit, signout;
    TextView tv;
    FirebaseAuth mAuth;
    FrameLayout fl;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        fl = v.findViewById(R.id.settings_fragment_layout);
        wifi = v.findViewById(R.id.freewifi_cv);
        feedback = v.findViewById(R.id.feedback_cv);
        complaint = v.findViewById(R.id.complaint_cv);
        contactUs = v.findViewById(R.id.contactUs_cv);
        aboutUs = v.findViewById(R.id.aboutUs_cv);
        signout = v.findViewById(R.id.signOut_cv);
        exit = v.findViewById(R.id.exit_cv);
        tv = v.findViewById(R.id.signTV);

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SharedPreferences getUser = getActivity().getSharedPreferences("user_info", getActivity().MODE_PRIVATE);
        if(getUser.getString("username",null) !=null){
            tv.setText("Sign Out");
        }else{
            tv.setText("Sign In");
        }

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!Global.isInternetOn(getActivity())) {
                    Toast.makeText(getActivity(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
                } else {
                    if (Global.user_email == null) {
                        Snackbar.make(fl, "Login Page will be loaded",2000).setAction("Login", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            }
                        }).show();
                    } else {
                        mAuth.signOut();

                        // Google sign out
                        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        AlertDialog.Builder signout = new AlertDialog.Builder(getActivity());
                                        signout.setMessage("Are you sure to sign out")
                                                .setTitle("Sign Out")
                                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .setPositiveButton("Sign me out", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        SharedPreferences getUser = getActivity().getSharedPreferences("user_info", getActivity().MODE_PRIVATE);
                                                        SharedPreferences.Editor ed = getUser.edit();
                                                        ed.putString("username", null);
                                                        ed.putString("username",null);
                                                        ed.putString("userphoto",null);
                                                        ed.putString("useremail",null);
                                                        ed.putString("userphone",null);
                                                        ed.commit();
                                                        Snackbar.make(v.getRootView().findViewById(R.id.settings_fragment_layout), "logged out", Snackbar.LENGTH_SHORT).show();
                                                        // Snackbar.make(v.getRootView().findViewById(R.id.settings_fragment_layout),"Logged Out Successfully",Snackbar.LENGTH_SHORT).show();
                                                        getActivity().finish();
                                                        startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                                    }
                                                })
                                                .show();
                                    }
                                });
                    }
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }
}
