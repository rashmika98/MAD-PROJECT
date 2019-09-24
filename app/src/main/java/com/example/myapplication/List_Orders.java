package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_Orders extends AppCompatActivity {

    private RecyclerView myRecyclerView;
      private DatabaseReference db;
    String orderId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__orders);

       db = FirebaseDatabase.getInstance().getReference().child("Order");
        db.keepSynced(true);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

         @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Order,OrderViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Order, OrderViewHolder>
                (Order.class,R.layout.order_row,OrderViewHolder.class,db) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Order order, int i) {

                orderViewHolder.setOrderID(order.getOrderID());
                orderViewHolder.setCusNIC(order.getCusNIC());
                orderViewHolder.setCusName(order.getCusName());
                orderViewHolder.setAddress(order.getAddress());
                orderViewHolder.setDeliveryID(order.getDeliveryID());
                orderViewHolder.setStatus(order.getStatus());
                orderId = order.getOrderID();



            }
        };

        myRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public OrderViewHolder(View itemView)
        {
            super(itemView);
            mView =itemView;
        }
        public void setOrderID(String orderID)
        {
            TextView id=(TextView)mView.findViewById(R.id.editTextOrderID);
            id.setText(orderID);
        }
        public  void setCusNIC (String cusNIC)
        {
            TextView nic = (TextView)mView.findViewById(R.id.editTextCusNIC);
            nic.setText(cusNIC);
        }

        public void setCusName(String cusName)
        {
            TextView name=(TextView)mView.findViewById(R.id.editTextCusName);
            name.setText(cusName);
        }
        public  void setAddress (String address)
        {
            TextView addresss = (TextView)mView.findViewById(R.id.editTextAddress);
            addresss.setText(address);
        }

        public void setDeliveryID(String deliveryID)
        {
            TextView DelId=(TextView)mView.findViewById(R.id.editTextDelID);
            DelId.setText(deliveryID);
        }
        public  void setStatus (String status)
        {
            TextView Delstatus = (TextView)mView.findViewById(R.id.editTextStatus);
            Delstatus.setText(status);
        }
    }
    public void UpdateOrderStatus(View view){
        Intent intent = new Intent(List_Orders.this, Updata_Order.class);
        intent.putExtra("ID",orderId);
        startActivity(intent);
    }

    public void Home(View view) {

        Intent intent = new Intent(List_Orders.this, ManagerDashboard.class);
        startActivity(intent);
    }
    public void logout(View view){
        Intent intent1 = new Intent(List_Orders.this, MainActivity.class);
        startActivity(intent1);

    }
}




















//    ListView lv;
//    ArrayList<String> OrderID;
//    ArrayList<String> CusNIC;
//    ArrayList<String> CusName;
//    ArrayList<String> Address;
//    ArrayList<String> DelID;
//    ArrayList<String> status;
//set id
//        lv = (ListView) findViewById(R.id.fbListview);
//
//        OrderID = new ArrayList<String>();
//        CusNIC = new ArrayList<String>();
//        CusName = new ArrayList<String>();
//        Address = new ArrayList<String>();
//        DelID = new ArrayList<String>();
//        status = new ArrayList<String>();
//
//
//        final ArrayList<String> OrderList = new ArrayList<>();
//        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("Order");
//        readRef1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //dataSnapshot.getChildrenCount();
//                Toast.makeText(getApplicationContext(), dataSnapshot.getChildrenCount() + "", Toast.LENGTH_LONG).show();
//
//                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
//                    String feedbackStr = dsp.getKey();
//                    OrderList.add(feedbackStr);
//
//                    //dataSnapshot.child("B001").child("fid").getValue().toString();
//
//                    //Toast.makeText(TeacherViewAllResults.this, examidStr+"", Toast.LENGTH_SHORT).show();
//                }
//                for (int i = 0; i < OrderList.size(); i++) {
//                    DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("Order").child(OrderList.get(i));
//                    readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            //  dataSnapshot.getChildrenCount();
//                            String orderID, CusNic, CustomerName, Add, DeliveryID,Status;
//                            orderID = dataSnapshot.child("orderID").getValue().toString();
//                            CusNic = dataSnapshot.child("CusNIC").getValue().toString();
//                            CustomerName = dataSnapshot.child("CusName").getValue().toString();
//                            Add = dataSnapshot.child("Address").getValue().toString();
//                            DeliveryID = dataSnapshot.child("DeliveryID").getValue().toString();
//                            Status = dataSnapshot.child("Status").getValue().toString();
//
//                            OrderID.add(orderID);
//                            CusNIC.add(CusNic);
//                            CusName.add(CustomerName);
//                            Address.add(Add);
//                            DelID.add(DeliveryID);
//                            status.add(Status);
//
//                            MyOrderAdapter adapter = new MyOrderAdapter(getApplicationContext(), OrderID, CusNIC, CusName, Address, DelID,status);
//                            lv.setAdapter(adapter);
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
//
//
////MyFeedbackAdapter class
//class MyOrderAdapter  extends ArrayAdapter<String> {
//    Context context;
//
//    ArrayList orderID;
//    ArrayList CusName;
//    ArrayList CusNIC;
//    ArrayList Address;
//    ArrayList DeliveryID;
//    ArrayList Status;
//
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View orrow = layoutInflater.inflate(R.layout.order_row, parent, false);
//
//
//        TextView TextOrderID = orrow.findViewById(R.id.editTextOrderID);
//        TextView TextCusNIC = orrow.findViewById(R.id.editTextCusNIC);
//        TextView TextCusName = orrow.findViewById(R.id.editTextCusName);
//        TextView TextAddress = orrow.findViewById(R.id.editTextAddress);
//        TextView TextDelID = orrow.findViewById(R.id.editTextDelID);
//        TextView TextStatus = orrow.findViewById(R.id.editTextStatus);
//
//
//        TextOrderID.setText(orderID.get(position).toString());
//        TextCusNIC.setText(CusNIC.get(position).toString());
//        TextCusName.setText(CusName.get(position).toString());
//        TextAddress.setText(Address.get(position).toString());
//        TextDelID.setText(DeliveryID.get(position).toString());
//        TextStatus.setText(Status.get(position).toString());
//
//
//        return orrow;
//    }
//
//
//    MyOrderAdapter(Context c, ArrayList orderID, ArrayList CusNIC, ArrayList CusName, ArrayList Address, ArrayList DeliveryID, ArrayList Status) {
//        super(c, R.layout.order_row, R.id.editTextOrderID, orderID);
//        this.context = c;
//        this.orderID = orderID;
//        this.CusNIC = CusNIC;
//        this.CusName = CusName;
//        this.Address = Address;
//        this.DeliveryID = DeliveryID;
//        this.Status = Status;
//
//    }







