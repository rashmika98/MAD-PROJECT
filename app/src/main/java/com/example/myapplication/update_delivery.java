package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class update_delivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delivery);
    }

    public void backOrders(View view){
        Intent intent = new Intent(update_delivery.this, orders.class);
        startActivity(intent);
    }
}
