package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Order extends Product{         //Order extends Product makes it one order only has one product to it
    private String orderID;
    private String orderStatus; //Whether it's paid or not
    private String orderCustomerID;

    private static Integer orderIdNumber = 0;

    static ArrayList<Order> orders = new ArrayList<>();

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
    private static void addOrderToList(String orderCustomerID, String productID, String productName, Integer productPrice, String productCategory, String orderID, String orderStatus){
        Order order = new Order(orderCustomerID, orderID, productID, productName, productPrice, productCategory, orderStatus);
        orders.add(order);
    }

    public String createOrderID() {                           ///This is to create a unique ID for the Customer Account
        return String.format("ODR_%04d", orderIdNumber++);
    }//To generate unique Order ID

    public static void printOrder(){
        for (int i = 0; i < orders.size(); i++){
            System.out.println(orders.get(i));
        }
    }

    public static void updateOrderStatus(){
        Scanner statusInput = new Scanner(System.in);
        System.out.println("Please enter the Order's ID that you wish to update the status of: ");
        String orderIdToUpdate = statusInput.nextLine();

        for (Order order : orders){
            if (order.getOrderID().equalsIgnoreCase(orderIdToUpdate)){
                System.out.print("Please enter the new status: ");
                String newStatus = statusInput.nextLine();
                order.setOrderStatus(newStatus);
            }
            else {
                System.out.print("There is so such order in the database!");
            }
        }
    }

    public static void printOrderByCustomerID(){
        ArrayList<Order> ordersFound = new ArrayList<>();

        Scanner searchByCustomerId = new Scanner(System.in);

        System.out.print("Please enter the book's Title: ");
        String customerIdFound = searchByCustomerId.nextLine();

        for (Order order : orders){
            if (order.orderCustomerID.contains(customerIdFound)){
                ordersFound.add(order);
            }
        }
        if (ordersFound.isEmpty()){
            System.out.println("No customer with matching ID is found");
        }else{
            System.out.println(String.format("%-15s%-15s%-10s%-15s%-20s%-20s%-15s", "Customer ID", "Order ID", "Order Status", "Product ID", "Product Name", "Product Price", "Product Category"));
            for (Order order : ordersFound){
                System.out.println(order.toString());
            }
        }
    }


    public static void writeOrders() throws FileNotFoundException {         //Write Orders' data to file
        File orderCsvFile = new File("order.csv");
        PrintWriter out0 = new PrintWriter(orderCsvFile);

        for (Order order : orders){
            out0.printf("%s,%s,%s,%s,%s,%d,%s\n", order.getOrderCustomerID(), order.getOrderID(), order.getOrderStatus(), order.getProductID(), order.getProductName(), order.getProductPrice(), order.getProductCategory());
        }
        out0.close();
    }

    public static void readOrder() throws FileNotFoundException {       //Read Orders' data from file
        Scanner orderReader = new Scanner(new File("order.csv"));
        orderReader.useDelimiter(",|\n");

        while (orderReader.hasNext()){
            String orderCustomerID = orderReader.next();
            String orderID = orderReader.next();
            String orderStatus = orderReader.next();
            String productID = orderReader.next();
            String productName = orderReader.next();
            Integer productPrice = orderReader.nextInt();
            String productCategory = orderReader.next();
            addOrderToList(orderCustomerID, productID, productName, productPrice, productCategory, orderID, orderStatus);
        }
    }





    /////
    @Override
    public String toString() {
        return String.format(orderCustomerID + ", " + orderID + ", " + orderStatus + ", " + productID + ", " + productName + ", " + productPrice + ", " + productCategory);
    }
}
