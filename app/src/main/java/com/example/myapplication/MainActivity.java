package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

    String un, pwd;
    EditText name;
    EditText pass;


    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

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


        sharedpreferences = getSharedPreferences("login" , 0) ;
        editor = sharedpreferences.edit();

        layout = (RelativeLayout) findViewById(R.id.layout1);

        handler.postDelayed(runnable, 1500);


        name = findViewById(R.id.editText);
        pass = findViewById(R.id.editText3);
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(), pass.getText().toString());
            }
        });

    }

    public void validate(String username, String password) {

        DatabaseReference readref = FirebaseDatabase.getInstance().getReference().child("cus_details_add").child(name.getText().toString());
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {

                    un = dataSnapshot.child("usrname").getValue().toString();
                    pwd = dataSnapshot.child("pwrd").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if ((username.equals("admin")) && (password.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, ManagerDashboard.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login Pass!!", Toast.LENGTH_LONG).show();
        } else {
            if ((username.equals("manager")) && (password.equals("5678"))) {
                Intent intent2 = new Intent(MainActivity.this, dashboard.class);
                startActivity(intent2);
                Toast.makeText(getApplicationContext(), "Login Pass!!", Toast.LENGTH_LONG).show();
            }
//        else if((username.equals("saluk")) && (password.equals("9900"))){
//        Intent intent3 = new Intent(MainActivity.this,DeliveryProfile.class);
//        startActivity(intent3);
//        Toast.makeText(getApplicationContext(),"Login Pass!!",Toast.LENGTH_LONG).show();
//        }
            else {
                if ((username.equals(name.getText().toString())) && (password.equals(pass.getText().toString()))) {
                    editor.putString("key1", name.getText().toString()) ;
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login Pass!!", Toast.LENGTH_LONG).show();

                    Intent intent4 = new Intent(MainActivity.this, UserProfile.class);
                    startActivity(intent4);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Password or Username", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

       /* else.{
            Toast.makeText(getApplicationContext(),"Invalid Password or Username",Toast.LENGTH_LONG).show();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserLogin").child(name.getText().toString());

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    userLogin = dataSnapshot.getValue(UserLogin.class);

                    if (dataSnapshot.hasChildren()) {

                       // String LoginPass = userLogin.getPassword();

                        String LoginPass = dataSnapshot.child("password").getValue().toString();
                        if (LoginPass.equalsIgnoreCase(pass.getText().toString())) {

                            Intent intent3 = new Intent(MainActivity.this, DeliveryProfile.class);
                            intent3.putExtra("NIC", userLogin.getNic());
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
            });*/






    public void startNewActivity(View view)
    {
        Intent i = new Intent (MainActivity.this,Signup_From.class);
        startActivity(i);
    }



}
