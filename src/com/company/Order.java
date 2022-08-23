package com.company;

public class Order extends Product{         //Order extends Product makes it one order only has one product to it
    private String orderID;
    private String orderStatus; //Whether it's paid or not

    public Order(String productID, String productName, Integer productPrice, String productCategory, String orderID, String orderStatus) {
        super(productID, productName, productPrice, productCategory);
        this.orderID = orderID;
        this.orderStatus = orderStatus;
    }

    //Getter
    public String getOrderID() {
        return orderID;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    //Setter
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    //Methods for Order


    /////
    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
