package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class OrderSuccess extends AppCompatActivity {


    LottieAnimationView laView;
    ImageView ivOrderSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        laView = findViewById(R.id.laOrderPlaced);
        ivOrderSuccess = findViewById(R.id.ivOrderSuccessFinal);
        laView.setAnimation(R.raw.animation);

        laView.playAnimation();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                laView.setImageResource(R.drawable.img);
            }
        },2000);

    }
}