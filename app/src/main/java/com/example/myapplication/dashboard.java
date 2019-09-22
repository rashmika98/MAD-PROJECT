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
        Intent intent = new Intent(dashboard.this, Category.class);
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


    public void searchProduct(View view){
        Intent intent = new Intent(dashboard.this, Search_Product.class);
        startActivity(intent);
    }

    public void removeProduct(View view){
        Intent intent = new Intent(dashboard.this, Remove_Product.class);
        startActivity(intent);
    }

    public void Logout(View view){
        Intent intent = new Intent(dashboard.this, MainActivity.class);
        startActivity(intent);
    }


}
