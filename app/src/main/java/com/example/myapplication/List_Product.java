package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class List_Product extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__product);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Item");
        dbRef.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerProductView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Item, ItemViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Item, ItemViewHolder>
                (Item.class, R.layout.product_row, ItemViewHolder.class, dbRef) {
            @Override
            protected void populateViewHolder(ItemViewHolder itemViewHolder, Item item, int i) {

                itemViewHolder.setItemID(item.getItemID());
                itemViewHolder.setProductName(item.getProductName());
                itemViewHolder.setPrice(String.valueOf(item.getPrice()));
                itemViewHolder.setCategory(item.getCategory());
                itemViewHolder.setImageURL(getApplicationContext(), item.getImageURL());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        public ItemViewHolder(View itemView)
        {
            super((itemView));
            mView = itemView;
        }

        public void setItemID(String ProductID){
            TextView Product_ID = (TextView)mView.findViewById(R.id.editTextProductID);
            Product_ID.setText(ProductID);
        }

        public void setProductName(String ProductName){
            TextView Product_Name = (TextView)mView.findViewById(R.id.editTextProductName);
            Product_Name.setText(ProductName);
        }

        public void setPrice(String Price){
            TextView Product_Price = (TextView)mView.findViewById(R.id.editTextProductPrice);
            Product_Price.setText(Price);
        }

        public void setCategory(String Category){
            TextView Product_Cat = (TextView)mView.findViewById(R.id.editTextProductCat);
            Product_Cat.setText(Category);
        }

        public void setImageURL(Context ctx, String image)
        {
            ImageView Product_Image = (ImageView)mView.findViewById(R.id.ProductImageView);
            Picasso.get().load(image).into(Product_Image);
        }
    }


    public void Home(View view) {

        Intent intent = new Intent(List_Product.this, dashboard.class);
        startActivity(intent);
    }

    public void Search(View view) {

        Intent intent = new Intent(List_Product.this, Search_Product.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent1 = new Intent(List_Product.this, MainActivity.class);
        startActivity(intent1);

    }
}
