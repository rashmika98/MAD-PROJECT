package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Updata_Order extends AppCompatActivity {

    EditText orderID, CusNIC, CusName, Addresss, DeliveryID,Status;
    Button btnUpdate;

    Order order;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata__order);

        final String Extra = getIntent().getStringExtra("ID");

        Toast.makeText(this, ""+Extra, Toast.LENGTH_SHORT).show();

        orderID = findViewById(R.id.editTextOrderID);
        CusNIC = findViewById(R.id.editTextCusNIC);
        CusName = findViewById(R.id.editTextCusName);
        Addresss = findViewById(R.id.editTextAddress);
        DeliveryID = findViewById(R.id.editTextDelID);
        Status = findViewById(R.id.editTextStatus);

        btnUpdate = findViewById(R.id.btnUpdateOrder);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Order").child(Extra);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){

                    orderID.setText(dataSnapshot.child("orderID").getValue().toString());
                    CusNIC.setText(dataSnapshot.child("cusNIC").getValue().toString());
                    CusName.setText(dataSnapshot.child("cusName").getValue().toString());
                    Addresss.setText(dataSnapshot.child("address").getValue().toString());
                    DeliveryID.setText(dataSnapshot.child("deliveryID").getValue().toString());
                    Status.setText(dataSnapshot.child("status").getValue().toString());

                }

                else {
                    Toast.makeText(Updata_Order.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Order");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        order = new Order();

                        if (dataSnapshot.hasChildren()){

                            order.setOrderID(orderID.getText().toString());
                            order.setCusNIC(CusNIC.getText().toString());
                            order.setCusName(CusName.getText().toString());
                            order.setAddress(Addresss.getText().toString());
                            order.setDeliveryID(DeliveryID.getText().toString());
                            order.setStatus(Status.getText().toString());
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Order").child(orderID.getText().toString());
                            ref.setValue(order);
                            clearAll();

                            Intent intent = new Intent(Updata_Order.this, List_Orders.class);
                            startActivity(intent);

                            Toast.makeText(Updata_Order.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else{

                            Toast.makeText(Updata_Order.this, "No source to Update", Toast.LENGTH_SHORT).show();
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

        orderID.setText("");
        CusNIC.setText("");
        CusName.setText("");
        Addresss.setText("");
        DeliveryID.setText("");
        Status.setText("");


    }
}
