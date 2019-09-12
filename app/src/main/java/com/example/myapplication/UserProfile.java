package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

public class UserProfile extends AppCompatActivity {


//            cus_details_add;

        TextView dsplyfname,dsplyuname,dsplymail,dsplyadd,dsplypno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

//        database = new cus_details_add(this);

        dsplyfname = findViewById(R.id.editText);
        dsplyuname = findViewById(R.id.editText2);
        dsplymail = findViewById(R.id.editText6);
        dsplyadd = findViewById(R.id.editText5);
        dsplypno = findViewById(R.id.editText4);



    }

    protected void onstart(){
        super.onStart();

//        dsplyfname.setText(data.getfname());
//        dsplyuname.setText();
    }
    public void home(View view){

        Intent i122 = new Intent( UserProfile.this,Grocery.class);
        startActivity(i122);



    }

}
