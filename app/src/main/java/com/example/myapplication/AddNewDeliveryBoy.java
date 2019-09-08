package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;


        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class AddNewDeliveryBoy extends AppCompatActivity {

    EditText txtName, txtNIC, txtAddress, txtEmail,txtDLicense, txtuName, txtPassword;
    Button btnadd;

    DatabaseReference ref;

    Driver driver;


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

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driver.setName(txtName.getText().toString());
                driver.setNic(txtNIC.getText().toString());
                driver.setAddress(txtAddress.getText().toString());
                driver.setEmail(txtEmail.getText().toString());
                driver.setdLicense(Integer.valueOf(txtDLicense.getText().toString()));
                driver.setUname(txtuName.getText().toString());
                driver.setDpaw(txtPassword.getText().toString());

                ref = FirebaseDatabase.getInstance().getReference().child("  Driver");
                //ref.child(String.valueOf(driver.getdLicense())).setValue(driver);
                ref.child(driver.getNic()).setValue(driver);
//                ref.child((driver.getName())).setValue(driver);

//                ref.push().setValue( driver);

                //Toast.makeText(AddNewDeliveryBoy.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                Toast.makeText(AddNewDeliveryBoy.this, "Success", Toast.LENGTH_SHORT).show();

                clearAll();

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

}
