package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class ManagerEmployee extends AppCompatActivity {


    private boolean table_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_employee);
    }

    public void collapseTable(View view){

        TableLayout table = findViewById(R.id.table);
        Button switchBtn = findViewById(R.id.switchBtn);

        //setColumnCollapsed(int columnIndex, boolean isCollapsed)
        table.setColumnCollapsed(1, table_flg);
        table.setColumnCollapsed(3, table_flg);
        table.setColumnCollapsed(4, table_flg);
        table.setColumnCollapsed(5, table_flg);
        table.setColumnCollapsed(6, table_flg);
        table.setColumnCollapsed(7, table_flg);


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
}
