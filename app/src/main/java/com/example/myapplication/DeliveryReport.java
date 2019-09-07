package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeliveryReport extends AppCompatActivity {

    EditText  Order_Id_txt  , Address_txt ,Delivery_T_txt , City_txt ;
    Button Submit_btn ,Delete_btn ;

    DatabaseReference ref;

    Delivery_Report_Insert deliveryReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_report);



        Order_Id_txt = findViewById(R.id.Order_Id_txt);
        Address_txt = findViewById(R.id.Address_txt);
        Delivery_T_txt = findViewById(R.id.Delivery_T_txt );
        City_txt = findViewById(R.id.City_txt);


        Submit_btn = findViewById(R.id.Submit_btn);
        Delete_btn = findViewById(R.id.Delete_btn);


        deliveryReport = new Delivery_Report_Insert();

        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deliveryReport.setDiliver_ID(Integer.valueOf( Order_Id_txt.getText().toString()));
                deliveryReport.setDiliver_Address(Address_txt.getText().toString());
                deliveryReport.setDiliver_Date(Delivery_T_txt .getText().toString());
                deliveryReport.setDiliver_City(  City_txt.getText().toString());


                ref = FirebaseDatabase.getInstance().getReference().child(" DeliveryReport");
        ref.push().setValue( deliveryReport);

        Toast.makeText(DeliveryReport.this, "Success", Toast.LENGTH_SHORT).show();

        clearAll();


        }
        });
        }




public void clearAll(){
        Order_Id_txt.setText("");
        Address_txt.setText("");
        Delivery_T_txt.setText("");
        City_txt.setText("");

        }

        }