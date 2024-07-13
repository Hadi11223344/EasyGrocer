package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerificationScreen extends AppCompatActivity {

    private final long startTimeInMillis = 130000; // 1.30 minutes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_screen);
    }
}