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

public class remove_delivery_report extends AppCompatActivity {

    EditText txtSearch, txtoID , txtoAdd , txtoDtime , txtoCity;
    Button remSrhBtn , remDeleBtn ;

    DatabaseReference DBref;

    Delivery_Report_Insert delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_delivery_report);

        txtSearch = findViewById(R.id.rem_serch);
        txtoID = findViewById(R.id.rem_o_id);
        txtoAdd = findViewById(R.id.rem_o_addr);
        txtoDtime = findViewById(R.id.rem_o_dtime);
        txtoCity = findViewById(R.id.rem_o_city);

        remSrhBtn = findViewById(R.id.rem_srh_btn);
        remDeleBtn = findViewById(R.id.rem_dele_btn);


        remSrhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBref = FirebaseDatabase.getInstance().getReference().child("DeliveryReport").child(txtSearch.getText().toString());
                DBref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {

                            if (dataSnapshot.hasChildren()) {


                                txtoID.setText(dataSnapshot.child("diliver_ID").getValue().toString().trim());

                                txtoAdd.setText(dataSnapshot.child("diliver_Address").getValue().toString());
                                txtoDtime.setText(dataSnapshot.child("diliver_Date").getValue().toString());
                                txtoCity.setText(dataSnapshot.child("diliver_City").getValue().toString());



                            }
                            else{
                                Toast.makeText(remove_delivery_report.this, "No source to display", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception ex){

                            Toast.makeText(remove_delivery_report.this, ex.getMessage().toString(),Toast.LENGTH_SHORT).show();



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });


        //delete code

        remDeleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("DeliveryReport");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(txtoID.getText().toString())){

                            DBref = FirebaseDatabase.getInstance().getReference().child("DeliveryReport").child(txtoID.getText().toString());
                            DBref.removeValue();
                            clearAll();

                            Toast.makeText(remove_delivery_report.this, "Remove Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(remove_delivery_report.this, "No Source to Delete", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
    public void clearAll() {

        txtSearch.setText("");
        txtoID.setText("");
        txtoAdd.setText("");
        txtoDtime.setText("");
        txtoCity.setText("");

    }



}
