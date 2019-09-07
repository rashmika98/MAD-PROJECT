package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void newproduct(View view){
        Intent intent = new Intent(dashboard.this, new_product.class);
        startActivity(intent);
    }

    public void editproduct(View view){
        Intent intent = new Intent(dashboard.this, edit_product.class);
        startActivity(intent);
    }

    public void viewproduct(View view){
        Intent intent = new Intent(dashboard.this, products.class);
        startActivity(intent);
    }

    public void removeProduct(View view){
        Intent intent = new Intent(dashboard.this, product_remove.class);
        startActivity(intent);
    }

    public void Logout(View view){
        Intent intent = new Intent(dashboard.this, MainActivity.class);
        startActivity(intent);
    }


}
