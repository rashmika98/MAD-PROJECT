package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class new_product extends AppCompatActivity {

    EditText txtID, txtName, txtPrice, txtCat;
    Button btnInsert;
    ImageView InputProductImage;

    private static final int GalleryPick = 1;

    private Uri ImageUri;

    private String CategoryName;
    private String downloadImageUrl;
    private String saveCurrentDate, saveCurrentTime, prodctRandomKey;

    DatabaseReference ref;
    private StorageReference ProductImageRef;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Item");

        txtID = findViewById(R.id.ItemID);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);

        btnInsert = findViewById(R.id.insert);

        InputProductImage = (ImageView) findViewById(R.id.select_product_image);

        item = new Item();


        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenGallery();

            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    if (ImageUri == null)
                    {
                        Toast.makeText(new_product.this, "Product Image is Mandatory", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(txtID.getText().toString())){

                        Toast.makeText(new_product.this, "Please Enter an ID", Toast.LENGTH_SHORT).show();

                    }
                    else if (TextUtils.isEmpty(txtName.getText().toString())){

                        Toast.makeText(new_product.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();

                    }
                    else if (TextUtils.isEmpty(txtPrice.getText().toString())){

                        Toast.makeText(new_product.this, "Please Enter Product Price", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        final StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment()+ ".jpg");
                        final UploadTask uploadTask = filePath.putFile(ImageUri);

                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                String message = e.toString();
                                Toast.makeText(new_product.this, "Error" + message, Toast.LENGTH_SHORT).show();

                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(new_product.this, "Product Image uploaded successfully", Toast.LENGTH_SHORT).show();

                                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                                        if (!task.isSuccessful()){

                                            throw task.getException();
                                        }

                                        downloadImageUrl = filePath.getDownloadUrl().toString();
                                        return filePath.getDownloadUrl();

                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {

                                        if(task.isSuccessful()){

                                            downloadImageUrl = task.getResult().toString();

                                            Toast.makeText(new_product.this, "got the product URL successfully", Toast.LENGTH_SHORT).show();


                                            item.setItemID(txtID.getText().toString());
                                            item.setProductName(txtName.getText().toString());
                                            item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
//                        item.setCategory(txtCat.getText().toString());
                                            item.setCategory(CategoryName);
                                            item.setImageURL(downloadImageUrl);

                                            ref = FirebaseDatabase.getInstance().getReference().child("Item");

                                            ref.child(item.getItemID()).setValue(item);

                                            //ref.push().setValue(item);

                                            Toast.makeText(new_product.this, "Item Inserted Successfully", Toast.LENGTH_SHORT).show();

                                            clearAll();

                                            Intent intent = new Intent(new_product.this, Category.class);
                                            startActivity(intent);

                                        }

                                    }
                                });
                            }
                        });



//                        StoreProductInformation();
                    }
            }catch (NumberFormatException e){

                    Toast.makeText(new_product.this, "Error In Inserting", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

//    private void StoreProductInformation() {
//
//        Calendar calendar = Calendar.getInstance();
//
//        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY");
//        saveCurrentDate = currentDate.format(calendar.getTime());
//
//
//        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentDate.format(calendar.getTime());
//
//
//        prodctRandomKey = saveCurrentDate + saveCurrentTime;
//
//        StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment() + prodctRandomKey + ".jpg");
//
//
//    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {

            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);

        }
    }

    public void clearAll(){

        txtID.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCat.setText("");
        InputProductImage.setImageURI(null);
    }


    public void Dashboard(View view){
        Intent intent = new Intent(new_product.this, dashboard.class);
        startActivity(intent);
    }


    public void Home(View view) {

        Intent intent = new Intent(new_product.this, dashboard.class);
        startActivity(intent);
    }

    public void Search(View view) {

        Intent intent = new Intent(new_product.this, Search_Product.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent1 = new Intent(new_product.this, MainActivity.class);
        startActivity(intent1);

    }


}
