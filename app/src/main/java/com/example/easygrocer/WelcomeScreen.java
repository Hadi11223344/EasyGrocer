package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeScreen extends AppCompatActivity {


    private TextView signup;
    private Button login;
    private FirebaseAuth mAuth;
    EditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        signup = findViewById(R.id.tvSignUpWelcomeScreen);
        login = findViewById(R.id.btnLoginWelcomeScreen);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        Intent DashboardIntent = new Intent(this, Dashboard.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,password);

                if(mAuth.getCurrentUser() != null)
                {
                    startActivity(DashboardIntent);


                }





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