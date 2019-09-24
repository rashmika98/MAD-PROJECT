package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Grocery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
    }

    public void vegCategory(View view)
    {
        Intent i = new Intent (Grocery.this,veg_category.class);
        startActivity(i);
    }
    public void fruitCategory(View view)
    {
        Intent i = new Intent (Grocery.this,fruit_category.class);
        startActivity(i);
    }

    public void Home(View view) {

        Intent intent = new Intent(Grocery.this, ManagerDashboard.class);
        startActivity(intent);
    }
    public void logout(View view){
        Intent intent1 = new Intent(Grocery.this, MainActivity.class);
        startActivity(intent1);

    }

}
