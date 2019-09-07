package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class orders extends AppCompatActivity {

    private boolean table_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
    }

    public void collapseTable(View view){

        TableLayout table = findViewById(R.id.table);
        Button switchBtn = findViewById(R.id.switchBtn);

        //setColumnCollapsed(int columnIndex, boolean isCollapsed)
        table.setColumnCollapsed(1, table_flg);
        table.setColumnCollapsed(2, table_flg);
        table.setColumnCollapsed(3, table_flg);
        table.setColumnCollapsed(4, table_flg);
        table.setColumnCollapsed(6, table_flg);

        if (table_flg){
            //close
            table_flg = false;
            switchBtn.setText("SHOW DETAILS");
        }
        else {
            //open
            table_flg = true;
            switchBtn.setText("HIDE DETAILS");
        }
    }

    public void editOrder(View view){
        Intent intent = new Intent(orders.this, update_delivery.class);
        startActivity(intent);
    }

    public void backDashboard(View view){
        Intent intent = new Intent(orders.this, ManagerDashboard.class);
        startActivity(intent);
    }
}
