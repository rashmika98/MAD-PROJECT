package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_product extends AppCompatActivity {

    EditText txtID, txtName, txtPrice, txtCat;
    Button btnInsert;

    DatabaseReference ref;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        txtID = findViewById(R.id.ItemID);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);

        btnInsert = findViewById(R.id.insert);

        item = new Item();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item.setItemID(txtID.getText().toString());
                item.setProductName(txtName.getText().toString());
                item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                item.setCategory(txtCat.getText().toString());

                ref = FirebaseDatabase.getInstance().getReference().child("Item");

                ref.child(item.getItemID()).setValue(item);


//                ref.push().setValue(item);

                Toast.makeText(new_product.this, "Item Inserted Successfully", Toast.LENGTH_SHORT).show();

                clearAll();
            }
        });


    }




    public void clearAll(){

        txtID.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCat.setText("");
    }


    public void Dashboard(View view){
        Intent intent = new Intent(new_product.this, dashboard.class);
        startActivity(intent);
    }

}
