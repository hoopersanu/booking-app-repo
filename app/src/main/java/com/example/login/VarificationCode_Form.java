package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class VarificationCode_Form extends AppCompatActivity {

    private RequestQueue mQueue;

    TextView phoneNumber;
    EditText otpText1, otpText2, otpText3, otpText4;
    String otp;
    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification_code__form);

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);

        String userData  = getIntent().getExtras().getString("mode");

        String loginUserData = getIntent().getExtras().getString("userLoginData");

        // ----------------- [ If user login ] ----------------------------

        if(loginUserData != null) {

            Toast.makeText(this, "user Login", Toast.LENGTH_LONG).show();

            try {
                JSONObject loginUser = new JSONObject(loginUserData);
                JSONObject loginSuccess = loginUser.getJSONObject("success");
                otp = loginSuccess.getString("otp");

                JSONObject user = loginSuccess.getJSONObject("user");
                phoneNum = user.getString("phone");

                // ---- Assigning phone number to textview -------------
                phoneNumber.setText(phoneNum);

                Toast.makeText(this,otp, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //  --------------- [ If user sign up ] ----------------------------
        else if(userData != null) {

            Toast.makeText(this, "User Sign up", Toast.LENGTH_LONG).show();

            try {
                JSONObject jsonObj = new JSONObject(userData);
                JSONObject success = jsonObj.getJSONObject("success");
                phoneNum = success.getString("phone");
                String otp = success.getString("otp");

                // ---- Assigning phone number to textview -------------
                phoneNumber.setText(phoneNum);

                Toast.makeText(this,otp, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




        // reading otp from edittext
        otpText1 = (EditText) findViewById(R.id.otpEditText1);
        otpText2 = (EditText) findViewById(R.id.otpEditText2);
        otpText3 = (EditText) findViewById(R.id.otpEditText3);
        otpText4 = (EditText) findViewById(R.id.otpEditText4);

        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
    }


    // -------------- [ OTP Verification ] -----------------------
        public void btnOtpVerification (View view) {
            String url_post = "https://estate29.com/server2/api/otp-verification";

            otp = ((otpText1.getText().toString()) +
                   (otpText2.getText().toString()) +
                   (otpText3.getText().toString()) +
                   (otpText4.getText().toString()));

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                //  extracting user data after otp verification
                    try {

                     /*---------------------------------------------------------
                                 Getting Success Key From JSON Object
                    -----------------------------------------------------------*/

                        JSONObject jsonObj = new JSONObject(response);
                        JSONObject success = jsonObj.getJSONObject("success");
                        String loginToken = success.getString("token");
                        String loginStatus = success.getString("login");

                    /*---------------------------------------------------------
                                 Getting user Data inside success object
                    -----------------------------------------------------------*/

                        JSONObject userData = success.getJSONObject("user");
                        String userTypeCode = userData.getString("user_type_code");
                        String userType = userData.getString("user_type");
                        String accountStatus = userData.getString("account_status");
                        String userId = userData.getString("user_id");
                        String phoneNumber = userData.getString("phone");

                        String data = "";
                         data += "Phone : " +phoneNumber;
                         data += " Login Token : " +loginToken;
                         data += " Login Status : " +loginStatus;
                         data += " User Type Code : " +userTypeCode;
                         data += " User Type : " +userType;

                         String user_type = "";



                        /* ---------------------------------------------------------------
                                   Case 1 : If login status true
                        -----------------------------------------------------------------*/

                        boolean login_status = Boolean.parseBoolean(loginStatus);

                        if(login_status == true) {
                            int userTypeId = Integer.parseInt(userTypeCode);

                            // if user type is pro user
                                if(userTypeId == 1) {
                                    Toast.makeText(VarificationCode_Form.this, userType, Toast.LENGTH_SHORT).show();

                                    // Redirect to Category Selection Page as logged in user
                                        Intent intent = new Intent(VarificationCode_Form.this, ProductItems.class);
                                        intent.putExtra("token", loginToken);
                                        intent.putExtra("userTypeCode",userTypeCode);
                                        intent.putExtra("userType", userType);
                                        intent.putExtra("loginStatus", login_status);
                                        intent.putExtra("phone", phoneNumber);
                                        startActivity(intent);
                                }

                            //  if user type is normal user
                                else if(userTypeId == 0) {

                                    //  Redirect to Homepage as logged in user

                                        Intent intent = new Intent(VarificationCode_Form.this,HomePage.class);
                                        intent.putExtra("access_token", loginToken);
                                        intent.putExtra("user_type_code",userTypeCode);
                                        intent.putExtra("user_type", userType);
                                        intent.putExtra("user_phone", phoneNumber);

//                                        Toast.makeText(VarificationCode_Form.this, loginToken, Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                }
                         }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(VarificationCode_Form.this, error+ "", Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    String phoneNumber = phoneNum;
                    String otpNum = otp;

                    params.put("phone", phoneNumber);
                    params.put("otp", otpNum);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


//            userTypeCode = 1;
//            userType = "pro-user";
//
//            otp = ((otpText1.getText().toString()) + (otpText2.getText().toString()) + (otpText3.getText().toString()) + (otpText4.getText().toString()));
//            Toast.makeText(this, "otp" + otp + "Phone : "+phone, Toast.LENGTH_SHORT).show();
//
//            // ------------- Check if user type is Pro User ------------------
//            if (userType.equals("pro-user") && (userTypeCode == 1)) {
//
//                // Redirect to Category Selection Page as logged in user
//                Intent intent = new Intent(this, ProductItems.class);
//                startActivity(intent);
//            }
//
//            // ------------- Check if user type is user -----------------------
//            else if ((userType.equals("user")) && (userTypeCode == 0 )) {
//
//                //  Redirect to Homepage as logged in user
//                Intent intent = new Intent(this,HomePage.class);
//                startActivity(intent);
//            }
//
//            else if (st.equals("login")){
//                // If action is login then redirect to the login screen
//                Intent intent= new Intent(this,HomePage.class);
//                startActivity(intent);
//            }
//        }
}
