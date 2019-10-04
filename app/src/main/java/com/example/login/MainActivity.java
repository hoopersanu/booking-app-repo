package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  String str;
  EditText phoneNumber;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
  }

  public void btnlogin(View view) {
    String url_post = "https://estate29.com/server2/api/user-login";
    Toast.makeText(this, "Method called", Toast.LENGTH_SHORT).show();

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url_post, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {

        //------------------------ Redirecting to next activity ---------------------
        Intent intent = new Intent(MainActivity.this,VarificationCode_Form.class);
        intent.putExtra("userLoginData", response);
        startActivity(intent);

//        Toast.makeText(getApplication(), response, Toast.LENGTH_LONG).show();
      }

    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this, error+ "", Toast.LENGTH_SHORT).show();
      }
    }){
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        String tempPhone = phoneNumber.getText().toString();

        params.put("phone", tempPhone);

        return params;
      }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
  }


    public void btn_sign_up(View view) {


    Intent intent = new Intent(this,SignUp_Form.class);
    startActivity(intent);
    }

    public void olololo(View view) {
    Intent intent = new Intent(this,Photography_Scroll_List.class);
    startActivity(intent);
    }
}
