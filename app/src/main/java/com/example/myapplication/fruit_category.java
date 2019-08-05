package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class fruit_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_category);
    }
    public void cart1(View view){

        Intent i122 = new Intent( fruit_category.this,AddToCart.class);
        startActivity(i122);



    }
}
