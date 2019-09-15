package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_Product extends AppCompatActivity {

    EditText txtItemID, txtIdItem, txtName, txtPrice, txtCat;
    Button btnSearch, btnClear;

    DatabaseReference ref;

    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__product);



        txtItemID = findViewById(R.id.ItemID);
        txtIdItem = findViewById(R.id.IdItem);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);

        btnSearch = findViewById(R.id.btnSearch);
        btnClear = findViewById(R.id.btnClear);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference SearchRef = FirebaseDatabase.getInstance().getReference().child("Item").child(txtItemID.getText().toString());
                SearchRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()){

                            txtIdItem.setText(dataSnapshot.child("itemID").getValue().toString());
                            txtName.setText(dataSnapshot.child("productName").getValue().toString());
                            txtPrice.setText(dataSnapshot.child("price").getValue().toString());
                            txtCat.setText(dataSnapshot.child("category").getValue().toString());
                        }

                        else{
                            Toast.makeText(Search_Product.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearAll();

            }
        });


    }


    public void clearAll(){

        txtItemID.setText("");
        txtIdItem.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCat.setText("");
    }

}
