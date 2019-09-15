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

public class DeliveryProfile extends AppCompatActivity {


    EditText txtName, txtNIC, txtAddress, txtEmail,txtDLicense, txtuName, txtPassword;
    Button btnUpdate;

    DatabaseReference ref, reff;

    Driver driver;

    UserLogin userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_profile);


        txtName = findViewById(R.id.adtxt1);
        txtNIC = findViewById(R.id.adtxt2);
        txtAddress = findViewById(R.id.adtxt3);
        txtEmail = findViewById(R.id.adtxt4);
        txtDLicense = findViewById(R.id.adtxt5);
        txtuName = findViewById(R.id.adtxt6);
        txtPassword = findViewById(R.id.adtxt7);

        btnUpdate = findViewById(R.id.button3);

        driver = new Driver();
        userLogin = new UserLogin();


//        Intent myIntent = new Intent();
//        String getExtra =  myIntent.getStringExtra("NIC");

//        driver getExtra= (Driver) getIntent().getSerializableExtra("NIC");
        String getExtra = getIntent().getStringExtra("NIC");

//        Bundle getExtra = getIntent().getExtras();

//        if(getExtra != null) {
//            ref = FirebaseDatabase.getInstance().getReference().child("Driver").child(getExtra.getString("NIC"));
            ref = FirebaseDatabase.getInstance().getReference().child("Driver").child("123456789V");
//            ref = FirebaseDatabase.getInstance().getReference().child("Driver").child(getExtra);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){

                    txtNIC.setText(dataSnapshot.child("nic").getValue().toString().trim());

                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                    txtDLicense.setText(dataSnapshot.child("dLicense").getValue().toString());
                    txtuName.setText(dataSnapshot.child("uname").getValue().toString());
                    txtPassword.setText(dataSnapshot.child("dpaw").getValue().toString());


                }
                else{

                    Toast.makeText(DeliveryProfile.this, "No Source to Display", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        }

    }

    public void De(View view)
    {
        Intent i1 = new Intent (DeliveryProfile.this,OrderList.class);
        startActivity(i1);
    }
}
