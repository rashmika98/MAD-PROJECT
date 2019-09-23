package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Remove_Driver extends AppCompatActivity {

    EditText txtSearch, txtName, txtNIC, txtAddress, txtEmail,txtDLicense, txtuName, txtPassword;
    Button btnSearch, btnDelete;

    DatabaseReference DBref, ref;

    Driver driver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__driver);


        txtSearch = findViewById(R.id.reSearch);
        txtName = findViewById(R.id.adtxt1);
        txtNIC = findViewById(R.id.adtxt2);
        txtAddress = findViewById(R.id.adtxt3);
        txtEmail = findViewById(R.id.adtxt4);
        txtDLicense = findViewById(R.id.adtxt5);
        txtuName = findViewById(R.id.adtxt6);
        txtPassword = findViewById(R.id.adtxt7);

       btnSearch = findViewById(R.id.btnsearch);
//        btnSearch = findViewById(R.id.button12);
        btnDelete = findViewById(R.id.rebtn1);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // DatabaseReference FindRef = FirebaseDatabase.getInstance().getReference().child("Driver").child("983081894V");


                DBref = FirebaseDatabase.getInstance().getReference().child("Driver").child(txtSearch.getText().toString());
                //Log.i("NIC", txtSearch.getText().toString());
                DBref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        try {

                            if (dataSnapshot.hasChildren()) {


                                txtNIC.setText(dataSnapshot.child("nic").getValue().toString().trim());

                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                            txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                            txtDLicense.setText(dataSnapshot.child("dLicense").getValue().toString());
                            txtuName.setText(dataSnapshot.child("uname").getValue().toString());
                            txtPassword.setText(dataSnapshot.child("dpaw").getValue().toString());


                            }
                            else{
                                Toast.makeText(Remove_Driver.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception ex){

                            Toast.makeText(Remove_Driver.this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();

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

                    if (TextUtils.isEmpty(txtSearch.getText().toString()))
                    {
                        Toast.makeText(Remove_Driver.this, "Please enter an Valid NIC here", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Driver");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChild(txtNIC.getText().toString())) {

                                    DBref = FirebaseDatabase.getInstance().getReference().child("Driver").child(txtNIC.getText().toString());
                                    ref = FirebaseDatabase.getInstance().getReference().child("UserLogin").child(txtuName.getText().toString());
                                    DBref.removeValue();
                                    ref.removeValue();
                                    clearAll();

                                    Toast.makeText(Remove_Driver.this, "Remove Successfully", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(Remove_Driver.this, "No Source to Delete", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    }catch (NumberFormatException e){

                        Toast.makeText(Remove_Driver.this, "Error In Deleting", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    public void clearAll() {

        txtSearch.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtDLicense.setText("");
        txtuName.setText("");
        txtPassword.setText("");
    }


    }
