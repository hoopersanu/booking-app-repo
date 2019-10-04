package com.example.login;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    View v;
    Button btn_Register;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Toast.makeText(getActivity(), userData[1], Toast.LENGTH_SHORT).show();

//        Activity activity = getActivity();
//        if(activity instanceof HomePage){
//            HomePage homePage = (HomePage) activity;
//            homePage.getUserDataPreference();
//        }


//        HomePage homePage = (HomePage) getActivity();
//
//        homePage.getUserDataPreference();

        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_profile, container, false);


        btn_Register = (Button)v.findViewById(R.id.loginScreenBtn);
       // btn_Register.setOnClickListener(this);


        btn_Register.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }

        });
        return v;
    }

}


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.loginScreenBtn:
//
//                break;
//        }
//    }


