package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.agrawalsuneet.dotsloader.loaders.TashieLoader;

public class Booking_Received_Form extends AppCompatActivity {

    TashieLoader tashieLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__received__form);

        tashieLoader = (TashieLoader)findViewById(R.id.loader);

        TashieLoader tashie = new TashieLoader(
                this, 5,
                30, 10,
                ContextCompat.getColor(this, R.color.colorAccent));

        tashie.setAnimDuration(500);
        tashie.setAnimDelay(100);
        tashie.setInterpolator(new LinearInterpolator());

        tashieLoader.addView(tashie);
    }
}
