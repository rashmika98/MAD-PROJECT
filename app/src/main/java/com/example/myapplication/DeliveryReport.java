package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeliveryReport extends AppCompatActivity {

    EditText  Order_Id_txt  , Address_txt ,Delivery_T_txt , City_txt ;
    Button Submit_btn ;
    Button Update ;


    DatabaseReference ref;

    Delivery_Report_Insert deliveryReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_report);

        Update = findViewById(R.id.Delete_btn);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent up = new Intent(getApplicationContext(),remove_delivery_report.class);
                startActivity(up);
            }
        });





        Order_Id_txt = findViewById(R.id.Order_Id_txt);
        Address_txt = findViewById(R.id.Address_txt);
        Delivery_T_txt = findViewById(R.id.Delivery_T_txt );
        City_txt = findViewById(R.id.City_txt);


        Submit_btn = findViewById(R.id.Submit_btn);



        deliveryReport = new Delivery_Report_Insert();

        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(Order_Id_txt.getText().toString())) {

                        Toast.makeText(DeliveryReport.this, "Please enter Name", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(Address_txt.getText().toString())) {

                        Toast.makeText(DeliveryReport.this, "Please Enter NIC Number", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(Delivery_T_txt.getText().toString())) {

                        Toast.makeText(DeliveryReport.this, "Please Enter a address", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(Delivery_T_txt.getText().toString())) {

                        Toast.makeText(DeliveryReport.this, "Please Enter an Email", Toast.LENGTH_SHORT).show();

                    } else
                        deliveryReport.setDiliver_ID(Integer.valueOf( Order_Id_txt.getText().toString()));
                        deliveryReport.setDiliver_Address(Address_txt.getText().toString());
                        deliveryReport.setDiliver_Date(Delivery_T_txt .getText().toString());
                        deliveryReport.setDiliver_City(  City_txt.getText().toString());


                        ref = FirebaseDatabase.getInstance().getReference().child("DeliveryReport");
                //ref.push().setValue( deliveryReport);
                        ref.child(String.valueOf(deliveryReport.getDiliver_ID())).setValue(deliveryReport);

                Toast.makeText(DeliveryReport.this, "Success", Toast.LENGTH_SHORT).show();

                clearAll();
            }catch (NumberFormatException e){

                Toast.makeText(DeliveryReport.this, "Error In Inserting", Toast.LENGTH_SHORT).show();
            }


        }
        });
    }

        //delete code
        /*

        Delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference deleref = FirebaseDatabase.getInstance().getReference().child("DeliveryReport");
                deleref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild(Order_Id_txt .getText().toString())) {

                            ref = FirebaseDatabase.getInstance().getReference().child("DeliveryReport").child(Order_Id_txt.getText().toString());
                            ref.removeValue();

                            Toast.makeText(DeliveryReport.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }

                        else{

                            Toast.makeText(DeliveryReport.this, "No Source to Delete", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }); */

        //end delete








public void clearAll(){
        Order_Id_txt.setText("");
        Address_txt.setText("");
        Delivery_T_txt.setText("");
        City_txt.setText("");

        }

}