package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cus_details_add {

    private String fname;
    private String usrname;
    private String email;
    private String address;
    private String pno;
    private String pwrd;
    private String conpwrd;
    private String NIC;
    public String getConpwrd() {
        return conpwrd;
    }

    public void setConpwrd(String conpwrd) {
        this.conpwrd = conpwrd;
    }





    // private String male;
    //private String female;


    public cus_details_add() {
    }


    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getfname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPwrd() {
        return pwrd;
    }

    public void setPwrd(String pwrd) {
        this.pwrd = pwrd;}


       //     public String getmale() {
         //       return male;
           // }

            //public void setMale(String male) {
              //  this.male = male;
            //}
    //public String getFemale() {
      //  return female;
    //}

    //public void setFemale(String female) {
      //  this.female = female;
    //}
        }



