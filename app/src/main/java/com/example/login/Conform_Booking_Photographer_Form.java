package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Conform_Booking_Photographer_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform__booking__photographer__form);
    }

    public void btn_Conform_Booking_Form(View view) {
        Intent intent = new Intent(this,Booking_Received_Form.class);
        startActivity(intent);
    }
}
