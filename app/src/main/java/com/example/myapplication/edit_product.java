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

public class edit_product extends AppCompatActivity {

    EditText txtItemID, txtIdItem, txtName, txtPrice, txtCat;
    Button btnSearch, btnUpdate;

    DatabaseReference ref;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);


        txtItemID = findViewById(R.id.ItemID);
        txtIdItem = findViewById(R.id.IdItem);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);

        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);



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
                            Toast.makeText(edit_product.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Item");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        item = new Item();

                        if(dataSnapshot.hasChild(txtIdItem.getText().toString())){

                            item.setItemID(txtIdItem.getText().toString());
                            item.setProductName(txtName.getText().toString());
                            item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                            item.setCategory(txtCat.getText().toString());

                            ref = FirebaseDatabase.getInstance().getReference().child("Item").child(txtIdItem.getText().toString());
                            ref.setValue(item);
                            clearAll();

                            Toast.makeText(edit_product.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                        
                        else{

                            Toast.makeText(edit_product.this, "No source to Update", Toast.LENGTH_SHORT).show();
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

        Intent intent = new Intent( edit_product.this, products.class);
        startActivity(intent);
    }
}
