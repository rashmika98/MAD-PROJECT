package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText pass;

    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editText);
        pass=findViewById(R.id.editText3);
        login=findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),pass.getText().toString());
            }
        });

    }

    public void validate(String username, String password){

        if((username.equals("admin")) && (password.equals("1234"))){
            Intent intent = new Intent(MainActivity.this,ManagerDashboard.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Login Pass!!", Toast.LENGTH_LONG).show();
        }else if((username.equals("manager")) && (password.equals("5678"))){
            Intent intent2 = new Intent(MainActivity.this,dashboard.class);
            startActivity(intent2);
            Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
        }else if((username.equals("saluk")) && (password.equals("9900"))){
        Intent intent3 = new Intent(MainActivity.this,DeliveryProfile.class);
        startActivity(intent3);
        Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
        }else if((username.equals("customer")) && (password.equals("0000"))){
        Intent intent4 = new Intent(MainActivity.this,UserProfile.class);
        startActivity(intent4);
        Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Password or Username",Toast.LENGTH_LONG).show();
        }
    }


    public void startNewActivity(View view)
    {
        Intent i = new Intent (MainActivity.this,Signup_From.class);
        startActivity(i);
    }



}
