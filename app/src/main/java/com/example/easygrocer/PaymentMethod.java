package com.example.easygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.Arrays;
import java.util.List;

public class PaymentMethod extends AppCompatActivity {

    ArrayAdapter adapter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        spinner = findViewById(R.id.spOnlinePayment);
        List<String> onlinePayment = Arrays.asList("Visa Card","Debit Card", "Easy Paisa");
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

    }
}