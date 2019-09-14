package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText pass;

    Button login;

    UserLogin userLogin;

    RelativeLayout layout;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            layout.setVisibility(View.VISIBLE);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout =  (RelativeLayout) findViewById(R.id.layout1);

        handler.postDelayed(runnable, 1500);



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
        }
//        else if((username.equals("saluk")) && (password.equals("9900"))){
//        Intent intent3 = new Intent(MainActivity.this,DeliveryProfile.class);
//        startActivity(intent3);
//        Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
//        }
        else if((username.equals("customer")) && (password.equals("0000"))){
        Intent intent4 = new Intent(MainActivity.this,UserProfile.class);
        startActivity(intent4);
        Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
        }
//        else{
//            Toast.makeText(getApplicationContext(),"Invalid Password or Username",Toast.LENGTH_LONG).show();
//        }

        else{

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserLogin").child(name.getText().toString());

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.hasChildren()) {

                       // String LoginPass = userLogin.getPassword();

                        String LoginPass = dataSnapshot.child("password").getValue().toString();
                        if (LoginPass.equalsIgnoreCase(pass.getText().toString())) {

                            Intent intent3 = new Intent(MainActivity.this, DeliveryProfile.class);
                            startActivity(intent3);
                            Toast.makeText(getApplicationContext(), "Login Pass!!", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Password or Username! Please enter valid userName/Password", Toast.LENGTH_LONG).show();
                        }

                    }
                    else{

                        Toast.makeText(getApplicationContext(), "Invalid Password or Username", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }


    public void startNewActivity(View view)
    {
        Intent i = new Intent (MainActivity.this,Signup_From.class);
        startActivity(i);
    }



}
