package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    public String myString = "Test Token key";
    public String token, userType;
    public String userTypeCode;
    public String loginStatus;
    public String phoneNumber;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        /*-------------------------------------------------------------------
                     Getting User Logged in details from API
        * ------------------------------------------------------------------- */

        token = getIntent().getExtras().getString("access_token");
        userType = getIntent().getExtras().getString("user_type");
        userTypeCode = getIntent().getExtras().getString("user_type_code");
        phoneNumber = getIntent().getExtras().getString("user_phone");


        //  --------- Creating value in sharedPreferences --------------------

        this.saveUserDataPreference(token, phoneNumber);

        BottomNavigationView navigationView = findViewById(R.id.btn_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                // -------------- If user is logged in ----------------------
//                if(loginStatus == true) {

                        //------------------ Home Fragment -----------------
                        if (id==R.id.home){
                            HomeFragment fragment = new HomeFragment();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame_layout,fragment);
                            fragmentTransaction.commit();
                        }

                        //  -------------- Favourite fragment ------------------
                        if (id==R.id.favorite){

                            // create favourite fragment
                            FavoriteFragment fragment = new FavoriteFragment();
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame_layout,fragment);
                            fragmentTransaction.commit();
                        }

                        //  -------------- Profile fragment ------------------
                        if (id==R.id.profile){
                            if(token != null) {

                         //  Toast.makeText(HomePage.this, "User Logged in : "+ userType, Toast.LENGTH_SHORT).show();
                                int user_type_code = Integer.parseInt(userTypeCode);
                                if(user_type_code == 0) {

                                    getUserDataPreference();


                                    UserLoggedInFragment user_fragment = new UserLoggedInFragment();
                                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.frame_layout,user_fragment);
                                    fragmentTransaction.commit();
                                }
                                else if(user_type_code == 1) {

                                }
                            }

                            else {
                                ProfileFragment fragment = new ProfileFragment();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame_layout,fragment);
                                fragmentTransaction.commit();
                            }

//                            ProfileFragment fragment = new ProfileFragment();
//                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.frame_layout,fragment);
//                            fragmentTransaction.commit();
                        }
//                }
                return true;
            }
        });
        //so the default fragemnt is our home fragment
        navigationView.setSelectedItemId(R.id.home);
    }


//--------------------- [ Save token key using shared preference ] ------------------------
        public void saveUserDataPreference(String token, String phone) {
            sharedPreferences = getSharedPreferences("SaveUserData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userToken", token);
            editor.putString("userPhone", phone);

            editor.apply();
        }

//-----------------------[ Return User Data ] -----------------------------
//        public String[] getUserDataPreference() {
//
//            String user[] = new String[2];
//
//            SharedPreferences result = getSharedPreferences("SaveUserData", Context.MODE_PRIVATE);
//
//            String token = result.getString("userToken", "Token not found");
//            String phone = result.getString("userPhone", "Phone no not found");
//
//            user[0] = phone;
//            user[1] = token;
//
//            Toast.makeText(this, user[1], Toast.LENGTH_SHORT).show();
//            return user;
//        }



        public void getUserDataPreference() {

            SharedPreferences result = getSharedPreferences("SaveUserData", Context.MODE_PRIVATE);

            String token = result.getString("userToken", "Token not found");
            String phone = result.getString("userPhone", "Phone no not found");

            Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();

        }
}
