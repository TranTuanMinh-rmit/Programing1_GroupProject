package com.company;

import java.util.ArrayList;

public class Order extends Product{         //Order extends Product makes it one order only has one product to it
    private String orderID;
    private String orderStatus; //Whether it's paid or not
    private String orderCustomerID;

    ArrayList<Order> orders = new ArrayList<>();

    public Order(String orderCustomerID, String orderID, String productID, String productName, Integer productPrice, String productCategory, String orderStatus) {
        super(productID, productName, productPrice, productCategory);
        this.orderCustomerID = orderCustomerID;
        this.orderID = orderID;
        this.orderStatus = orderStatus;
    }

    //Getter
    public String getOrderCustomerID(){
        return orderCustomerID;
    }
    public String getOrderID() {
        return orderID;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    //Setter
    public void setOrderCustomerID(String orderCustomerID){
        this.orderCustomerID = orderCustomerID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    //Methods for Order
    private void addOrderToList(String orderCustomerID,String productID, String productName, Integer productPrice, String productCategory, String orderID, String orderStatus){
        Order order = new Order(orderCustomerID, orderID, productID, productName, productPrice, productCategory, orderStatus);
        orders.add(order);
    }


    /////
    @Override
    public String toString() {
        return "Order{" +
                "orderCustomerID+'" + orderCustomerID +'\'' +
                "orderID='" + orderID + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
