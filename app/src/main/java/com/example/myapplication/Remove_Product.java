package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Remove_Product extends AppCompatActivity {

    EditText txtItemID, txtName, txtPrice, txtCat;
    TextView txtIdItem;
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


                try{

                    if (TextUtils.isEmpty(txtItemID.getText().toString()))
                    {
                        Toast.makeText(Remove_Product.this, "Please enter an Valid Item ID here", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Item");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChild(txtIdItem.getText().toString())){

                                    ref = FirebaseDatabase.getInstance().getReference().child("Item").child(txtIdItem.getText().toString());
                                    ref.removeValue();
                                    clearAll();

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
                }catch (NumberFormatException e){

                    Toast.makeText(Remove_Product.this, "Error In Deleting", Toast.LENGTH_SHORT).show();
                }

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


    public void Home(View view) {

        Intent intent = new Intent(Remove_Product.this, dashboard.class);
        startActivity(intent);
    }

    public void Search(View view) {

        Intent intent = new Intent(Remove_Product.this, Search_Product.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent1 = new Intent(Remove_Product.this, MainActivity.class);
        startActivity(intent1);

    }

}
