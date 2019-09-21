package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Category extends AppCompatActivity {

    private ImageView fruit, veg, grocery, Dairy;
    private ImageView grain, meat, beverage, bakery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        fruit = (ImageView) findViewById(R.id.cat_fruit);
        veg = (ImageView) findViewById(R.id.cat_veg);
        grocery = (ImageView) findViewById(R.id.cat_grocery);
        Dairy = (ImageView) findViewById(R.id.cat_Dairy);

        grain = (ImageView) findViewById(R.id.cat_grain);
        meat = (ImageView) findViewById(R.id.cat_meat);
        beverage = (ImageView) findViewById(R.id.cat_beverage);
        bakery = (ImageView) findViewById(R.id.cat_bakery);

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Fruit");
                startActivity(intent);
            }
        });

        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Vegetable");
                startActivity(intent);
            }
        });

        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Grocery");
                startActivity(intent);
            }
        });

        Dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Dairy");
                startActivity(intent);
            }
        });

        grain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Grain");
                startActivity(intent);
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Meat");
                startActivity(intent);
            }
        });

        beverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Beverage");
                startActivity(intent);
            }
        });


        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, new_product.class);
                intent.putExtra("category", "Bakery");
                startActivity(intent);
            }
        });

    }
}
