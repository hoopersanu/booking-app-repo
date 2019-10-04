package com.example.login;



import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Infromation_Form extends AppCompatActivity {

    private TextView tv_categoryTitle;

    private EditText etName;
    private EditText etStore;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etTeamSize;
    private EditText etAddress;
    private Button btn;

    private RequestQueue mQueue;
    private String urlJsonObj = "https://jsonplaceholder.typicode.com/users/10";
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infromation__form);

        String categoryTitle = getIntent().getExtras().getString("category_title");

        Toast.makeText(this, categoryTitle, Toast.LENGTH_SHORT).show();
        tv_categoryTitle = (TextView) findViewById(R.id.textViewCategoryTitle);

        tv_categoryTitle.setText(categoryTitle);





        etName = findViewById(R.id.editTextName);
        etStore = findViewById(R.id.editTextStoreName);
        etEmail = findViewById(R.id.editTextEmail);
        etPhone = findViewById(R.id.editTextPhone);
        etTeamSize = findViewById(R.id.editTextTeamSize);
        etAddress = findViewById(R.id.editTextAddress);

        btn = findViewById(R.id.btnSave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                btn.setVisibility(View.GONE);

//                FragmentManager fragmentManager = getSupportFragmentManager();
//                HomePage fragment = new HomePage();
//                fragmentManager.beginTransaction().replace(R.id.InformationContainer,fragment).commit();

//

                // ------------------- [ Create Store Detail ] -------------------------







                // -------------------- [ Calling Home Page Intent ] ------------------

                Intent intent = new Intent(Infromation_Form.this,HomePage.class);
                startActivity(intent);
            }
        });


        mQueue = Volley.newRequestQueue(this);
        proUserProfileDetail();
    }


      private void proUserProfileDetail() {
//        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                Log.d(TAG, response.toString());
                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject address = response.getJSONObject("address");
                    String city = address.getString("city");
                    String street = address.getString("street");
                    String phone = response.getString("phone");


                    jsonResponse = "";
//                    jsonResponse += "Name: " + name + "\n\n";
//                    jsonResponse += "Email: " + email + "\n\n";
//                    jsonResponse += "city: " + city + "\n\n";
//                    jsonResponse += "Mobile: " + mobile + "\n\n";

//                    Toast.makeText(Infromation_Form.this, name, Toast.LENGTH_SHORT).show();
                    etName.setText(name);
                    etStore.setText("Digital Studio");
                    etEmail.setText(email);
                    etPhone.setText(phone);
                    etTeamSize.setText("4");
                    etAddress.setText(street + city);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
//                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonObjReq);
    }


    // --------------- [ Create User Store Data ] -----------------------------




    // ------------------------------------------------------------------------
}
