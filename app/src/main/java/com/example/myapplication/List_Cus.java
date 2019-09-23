package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class List_Cus extends AppCompatActivity {


    private RecyclerView myRecyclerView;
      private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__cus);

        db = FirebaseDatabase.getInstance().getReference().child("cus_details_add");
        db.keepSynced(true);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerViewCustomer);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

         @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<cus_details_add,CusViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<cus_details_add, CusViewHolder>
                (cus_details_add.class,R.layout.cus_row,CusViewHolder.class,db) {
            @Override
            protected void populateViewHolder(CusViewHolder cusViewHolder, cus_details_add customer, int i) {

                cusViewHolder.setNIC(customer.getNIC());
                cusViewHolder.setFname(customer.getfname());
                cusViewHolder.setAddress(customer.getAddress());
                cusViewHolder.setEmail(customer.getEmail());
                cusViewHolder.setPno(customer.getPno());
                cusViewHolder.setPwrd(customer.getPwrd());
                cusViewHolder.setUsrname(customer.getUsrname());

            }
        };

        myRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CusViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public CusViewHolder(View itemView)
        {
            super(itemView);
            mView =itemView;
        }
        public void setNIC(String NIC)
        {
            TextView nic=(TextView)mView.findViewById(R.id.editTextNIC);
            nic.setText(NIC);
        }
        public  void setFname (String CusName)
        {
            TextView Name = (TextView)mView.findViewById(R.id.editTextCusFName);
            Name.setText(CusName);
        }

        public void setAddress(String Address)
        {
            TextView address=(TextView)mView.findViewById(R.id.editTextCusAddress);
            address.setText(Address);
        }
        public  void setEmail (String email)
        {
            TextView Email = (TextView)mView.findViewById(R.id.editTextEmail);
            Email.setText(email);
        }

        public void setPno(String Phone)
        {
            TextView phoneNum=(TextView)mView.findViewById(R.id.editTextPhone);
            phoneNum.setText(Phone);
        }
        public  void setPwrd (String pass)
        {
            TextView Password = (TextView)mView.findViewById(R.id.editTextPassword);
            Password.setText(pass);
        }
        public  void setUsrname (String UName)
        {
            TextView UserName = (TextView)mView.findViewById(R.id.editTextUserName);
            UserName.setText(UName);
        }
    }

}
