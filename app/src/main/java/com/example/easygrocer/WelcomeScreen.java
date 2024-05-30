package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {


    TextView signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        signup = findViewById(R.id.tvSignUp);
        login = findViewById(R.id.btnLogin);

        Intent loginIntent = new Intent(this, VerificationScreen.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(loginIntent);
            }
        });


        Intent signUpIntent = new Intent(this, SignUp_Screen.class);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(signUpIntent);
            }
        });

    }
}