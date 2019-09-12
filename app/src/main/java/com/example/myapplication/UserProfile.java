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




        TextView dsplyfname,dsplyuname,dsplymail,dsplyadd,dsplypno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        //database = new cus_details_add(this);

        //dsplyfname = findViewById(R.id.editText);
        //dsplyuname = findViewById(R.id.user_profile_dsplyuname);
        //dsplymail = findViewById(R.id.user_profile_dsplymail);
        //dsplyadd = findViewById(R.id.user_profile_dsplyadd);
        //dsplypno = findViewById(R.id.user_profile_dsplypno);



    }

    protected void onstart(){
        super.onStart();

        //dsplyfname.setText(data.getfname());
        //dsplyuname.setText();
    }
    public void home(View view){

        Intent i122 = new Intent( UserProfile.this,Grocery.class);
        startActivity(i122);



    }

}
