package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Remove_Product extends AppCompatActivity {

    EditText txtItemID, txtIdItem, txtName, txtPrice, txtCat;
    Button btnSearch, btnDelete;

    DatabaseReference ref;

    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__product);


        txtItemID = findViewById(R.id.ItemID);
        txtIdItem = findViewById(R.id.IdItem);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);

        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);


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
                            Toast.makeText(Remove_Product.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Item");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(txtIdItem.getText().toString())){

                            ref = FirebaseDatabase.getInstance().getReference().child("Item").child(txtIdItem.getText().toString());
                            ref.removeValue();
//                            clearAll();

                            Toast.makeText(Remove_Product.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                        }

                        else{

                            Toast.makeText(Remove_Product.this, "No Source to Delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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

    public void table(View view){

        Intent intent = new Intent( Remove_Product.this, products.class);
        startActivity(intent);
    }
}
