package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Startup_ScreenOne extends AppCompatActivity {


    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_screen_one);

        start = findViewById(R.id.btnletsstart);
        Intent i = new Intent(this, WelcomeScreen.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        startActivity(i);

            }
        });



    }
}