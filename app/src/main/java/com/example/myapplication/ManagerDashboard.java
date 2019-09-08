package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerDashboard<button> extends AppCompatActivity {


   Button btndelivery,btnstatus,btnorders,btnemployee, btnitem,btncus;

    public void Dilivery(View view)
    {
        Intent i1 = new Intent (ManagerDashboard.this,AddNewDeliveryBoy.class);
        startActivity(i1);
    }

    public void Employee(View view)
    {
//        Intent i1 = new Intent (ManagerDashboard.this,ManagerEmployee.class);
        Intent i1 = new Intent (ManagerDashboard.this,RemoveDriver.class);
        startActivity(i1);
    }


    public void items(View view)
    {
        Intent i1 = new Intent (ManagerDashboard.this,Grocery.class);
        startActivity(i1);
    }

    public void orders(View view)
    {
        Intent i1 = new Intent (ManagerDashboard.this,orders.class);
        startActivity(i1);
    }

    public void cusView(View view)
    {
        Intent i1 = new Intent (ManagerDashboard.this,cus_view.class);
        startActivity(i1);
    }

    public void Logout(View view){
        Intent intent = new Intent(ManagerDashboard.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

       btndelivery= (Button)findViewById(R.id.buttondelivery);
//        btnstatus = (Button)findViewById(R.id.buttonstatus);
//       btnorders = (Button)findViewById(R.id.buttonorder);
//        btnemployee = (Button)findViewById(R.id.buttonemployee);
//        btnitem = (Button)findViewById(R.id.buttonitem);
//        btncus = (Button)findViewById(R.id.buttoncus);
//
//
//        btndelivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "New Diliveary BOY Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnstatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "Delivery status Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnorders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "Orders Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        btnemployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "Manage Employee Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "Inventory Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btncus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ManagerDashboard.this, "Customer Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

    }





}
