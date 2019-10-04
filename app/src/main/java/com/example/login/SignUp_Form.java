package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp_Form extends AppCompatActivity {

    private String userType;
    private String tempPhone;
    private Button btnSingup;

    private EditText phone;



    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__form);

        phone = (EditText) findViewById(R.id.textPhone);
        btnSingup = (Button) findViewById(R.id.singupButton);
        btnSingup.setClickable(true);
    }

    public void btnsignup(View view) {
        if((userType != null) && (phone.length() == 10)) {
            btnSingup.setClickable(true);
            userSignUp();
        }
    }


    public void btn_Login(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btn_user(View view) {
        userType = "user";
    }

    public void btn_Admin(View view) {
        userType = "professional";
    }


    //---------------------------- [ User Sign Up ] -----------------------------------
    private  void userSignUp() {

        String url_post = "https://estate29.com/server2/api/register";
        Toast.makeText(this, "Method called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //------------------------ Redirecting to next activity ---------------------
            Intent intent = new Intent(SignUp_Form.this,VarificationCode_Form.class);

            intent.putExtra("mode", response);
            startActivity(intent);
                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp_Form.this, error+ "", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String tempPhone = phone.getText().toString().trim();
                String user_type = userType;

                params.put("phone", tempPhone);
                params.put("user_type", user_type);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
