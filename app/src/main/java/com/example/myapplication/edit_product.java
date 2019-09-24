package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class edit_product extends AppCompatActivity {

    EditText txtItemID, txtName, txtPrice, txtCat;
    TextView txtIdItem;
    ImageView viewImage;
    Button btnSearch, btnUpdate;

    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private StorageTask uploadTask;
    private String myUrl = "";

    DatabaseReference ref;
    private StorageReference ProductImageRef;
    private String downloadImageUrl;


    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

//        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Item").child(txtItemID.getText().toString());

        txtItemID = findViewById(R.id.ItemID);
        txtIdItem = findViewById(R.id.IdItem);
        txtName = findViewById(R.id.name);
        txtPrice = findViewById(R.id.price);
        txtCat = findViewById(R.id.category);
        viewImage =(ImageView) findViewById(R.id.ProductViewImage);

        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference SearchRef = FirebaseDatabase.getInstance().getReference().child("Item").child(txtItemID.getText().toString());
                SearchRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){

                            txtIdItem.setText(dataSnapshot.child("itemID").getValue().toString());
                            txtName.setText(dataSnapshot.child("productName").getValue().toString());
                            txtPrice.setText(dataSnapshot.child("price").getValue().toString());
                            txtCat.setText(dataSnapshot.child("category").getValue().toString());

                            String image = dataSnapshot.child("imageURL").getValue().toString();

//                            viewImage.setImageURI(setImageURL(getApplicationContext(), item.getImageURL()));
                            Picasso.get().load(image).into(viewImage);
                        }
                        
                        else{
                            Toast.makeText(edit_product.this, "No Source to Display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        viewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenGallery();

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{

                    if (TextUtils.isEmpty(txtItemID.getText().toString()))
                    {
                        Toast.makeText(edit_product.this, "Please enter an Valid Item ID here", Toast.LENGTH_SHORT).show();
                    }
                    else {


                        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Item").child(txtItemID.getText().toString());


                        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Item");
                        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




//                                final StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment()+ ".jpg");
//                                final UploadTask uploadTask = filePath.putFile(ImageUri);
//
//                                uploadTask.addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//
//                                        String message = e.toString();
//                                        Toast.makeText(edit_product.this, "Error" + message, Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                    @Override
//                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                                        Toast.makeText(edit_product.this, "Product Image uploaded successfully", Toast.LENGTH_SHORT).show();
//
//                                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                                            @Override
//                                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//
//                                                if (!task.isSuccessful()){
//
//                                                    throw task.getException();
//                                                }
//
//                                                downloadImageUrl= filePath.getDownloadUrl().toString();
//                                                return filePath.getDownloadUrl();
//
//                                            }
//                                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Uri> task) {
//
//                                                if (task.isSuccessful()){
//
//                                                    downloadImageUrl = task.getResult().toString();
//
//                                                    Toast.makeText(edit_product.this, "got the product URL successfully", Toast.LENGTH_SHORT).show();
//
//                                                }
//
//                                            }
//                                        });
//
//                                    }
//                                });



                                if(ImageUri != null){
//                                    final StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment()+ ".jpg");
                                    final StorageReference filePath = ProductImageRef.child(txtIdItem.getText().toString()+ ".jpg");
                                    uploadTask = filePath.putFile(ImageUri);


                                    uploadTask.continueWithTask(new Continuation() {
                                        @Override
                                        public Object then(@NonNull Task task) throws Exception {
                                            if (!task.isSuccessful()){
                                                throw task.getException();
                                            }

                                            return filePath.getDownloadUrl();
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {


                                            if(task.isSuccessful()){
                                                Uri downloadUrl = task.getResult();
                                                myUrl = downloadUrl.toString();
                                            }
                                        }
                                    });
                                }


                                item = new Item();

                                if(dataSnapshot.hasChild(txtIdItem.getText().toString())){

                                    item.setItemID(txtIdItem.getText().toString());
                                    item.setProductName(txtName.getText().toString());
                                    item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                                    item.setCategory(txtCat.getText().toString());

//                                    item.setImageURL(downloadImageUrl);
                                    item.setImageURL(myUrl);

//                                    String image = dataSnapshot.child("").getValue().toString();
                                    ref = FirebaseDatabase.getInstance().getReference().child("Item").child(txtIdItem.getText().toString());
                                    ref.setValue(item);
                                    clearAll();

                                    Toast.makeText(edit_product.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                }

                                else{

                                    Toast.makeText(edit_product.this, "No source to Update", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }catch (NumberFormatException e){

                    Toast.makeText(edit_product.this, "Error In updating", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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
            viewImage.setImageURI(ImageUri);

        }
    }

    public void clearAll(){

        txtItemID.setText("");
        txtIdItem.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCat.setText("");
        viewImage.setImageURI(null);
    }


//    public void setImageURL(Context ctr, String image){
//
//        ImageView imageView = (ImageView) findViewById(R.id.ProductViewImage);
//        Picasso.get().load(image).into(imageView);
//    }


    public void table(View view){

        Intent intent = new Intent( edit_product.this, products.class);
        startActivity(intent);
    }


    public void Home(View view) {

        Intent intent = new Intent(edit_product.this, dashboard.class);
        startActivity(intent);
    }

    public void Search(View view) {

        Intent intent = new Intent(edit_product.this, Search_Product.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent1 = new Intent(edit_product.this, MainActivity.class);
        startActivity(intent1);

    }

}
