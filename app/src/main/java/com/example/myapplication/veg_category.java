package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class veg_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_category);
    }

    public void cart(View view){

        Intent i122 = new Intent( veg_category.this,AddToCart.class);
        startActivity(i122);



    }

}


