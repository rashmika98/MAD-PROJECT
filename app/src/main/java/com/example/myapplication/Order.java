package com.example.myapplication;

public class Order {

    private String orderID;
    private String CusNIC;
    private String CusName;
    private String Address;
    private String DeliveryID;
    private String Status;

    public Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCusNIC() {
        return CusNIC;
    }

    public void setCusNIC(String cusNIC) {
        CusNIC = cusNIC;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        DeliveryID = deliveryID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
