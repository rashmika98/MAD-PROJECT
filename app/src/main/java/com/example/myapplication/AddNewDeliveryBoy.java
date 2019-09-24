package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;


        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class AddNewDeliveryBoy extends AppCompatActivity {

    EditText txtName, txtNIC, txtAddress, txtEmail,txtDLicense, txtuName, txtPassword;
    Button btnadd;

    DatabaseReference ref, reff;

    Driver driver;

    UserLogin userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_delivery_boy);

        txtName = findViewById(R.id.adtxt1);
        txtNIC = findViewById(R.id.adtxt2);
        txtAddress = findViewById(R.id.adtxt3);
        txtEmail = findViewById(R.id.adtxt4);
        txtDLicense = findViewById(R.id.adtxt5);
        txtuName = findViewById(R.id.adtxt6);
        txtPassword = findViewById(R.id.adtxt7);

        btnadd = findViewById(R.id.adbtn1);

        driver = new Driver();
        userLogin = new UserLogin();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if (TextUtils.isEmpty(txtName.getText().toString())) {
                        Toast.makeText(AddNewDeliveryBoy.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtNIC.getText().toString())) {

                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter NIC Number", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(txtAddress.getText().toString())) {

                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter a address", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(txtEmail.getText().toString())) {

                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter an Email", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(txtDLicense.getText().toString())) {
                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter Driving License", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtuName.getText().toString())) {
                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter unique user Name", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(txtPassword.getText().toString())) {
                        Toast.makeText(AddNewDeliveryBoy.this, "Please Enter a password", Toast.LENGTH_SHORT).show();
                    } else {

                        driver.setName(txtName.getText().toString());
                        driver.setNic(txtNIC.getText().toString());
                        driver.setAddress(txtAddress.getText().toString());
                        driver.setEmail(txtEmail.getText().toString());
                        driver.setdLicense(Integer.valueOf(txtDLicense.getText().toString()));
                        driver.setUname(txtuName.getText().toString());
                        driver.setDpaw(txtPassword.getText().toString());

                        userLogin.setUsename(txtuName.getText().toString());
                        userLogin.setPassword(txtPassword.getText().toString());
                        userLogin.setNic(txtNIC.getText().toString());

                        ref = FirebaseDatabase.getInstance().getReference().child("Driver");
                        reff = FirebaseDatabase.getInstance().getReference().child("UserLogin");
                        //ref.child(String.valueOf(driver.getdLicense())).setValue(driver);
                        ref.child(driver.getNic()).setValue(driver);
                        reff.child(userLogin.getUsename()).setValue(userLogin);
                        //                ref.child((driver.getName())).setValue(driver);

                        //                ref.push().setValue( driver);

                        //Toast.makeText(AddNewDeliveryBoy.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                        Toast.makeText(AddNewDeliveryBoy.this, "Success", Toast.LENGTH_SHORT).show();

                        clearAll();

                        Intent intent = new Intent(AddNewDeliveryBoy.this, ManagerDashboard.class);
                        startActivity(intent);
                    }
                }catch (NumberFormatException e){

                    Toast.makeText(AddNewDeliveryBoy.this, "Error In Inserting", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




    public void clearAll(){
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtDLicense.setText("");
        txtuName.setText("");
        txtPassword.setText("");
    }


    public void Home(View view) {

        Intent intent = new Intent(AddNewDeliveryBoy.this, ManagerDashboard.class);
        startActivity(intent);
    }
    public void logout(View view){
        Intent intent1 = new Intent(AddNewDeliveryBoy.this, MainActivity.class);
        startActivity(intent1);

    }
}
