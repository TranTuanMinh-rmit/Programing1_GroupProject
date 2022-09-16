package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.company.Customer.customerInSession;

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

    public static void printOrder(){
        for (int i = 0; i < orders.size(); i++){
            System.out.println(orders.get(i));
        }
    }

    public static void createOrder(){
        Random random = new Random();
        ArrayList<Product> productsFound = new ArrayList<>();
        Scanner input03 = new Scanner(System.in);
        Integer amountToPay = 0;
        int discountPercent = 0;
        Integer discountAmount = 0;
        Integer priceAfterDiscount = 0;


        System.out.println("Please enter the Product's ID you wish to purchase: ");
        String productIdToPurchase = input03.nextLine();
        for (Product product : productsDiscountPrice){
            if (product.productID.contains(productIdToPurchase)){        //Has to be Case sensitive1
                productsFound.add(product);
            }
        }
        if (productsFound.isEmpty()){
            System.out.println("No such product is found!");
        }else{
            for (Customer customer : customerInSession){

                String orderCustomerId = customer.getAccountID();
                for (Product product : productsFound){
                    String orderId = String.format("ODR_%04d", random.nextInt(10000));
                    String productID = product.getProductID();
                    String productName = product.getProductName();
                    Integer productPrice = product.getProductPrice();
                    //Check for discount
                    if (customer.getCustomerTier().equals("Bronze")){
                        discountPercent = 0;
                    }else if (customer.getCustomerTier().equals("Silver")){
                        discountPercent = 5;
                    }else if (customer.getCustomerTier().equals("Gold")){
                        discountPercent = 10;
                    }else if (customer.getCustomerTier().equals("Platinum")) {
                        discountPercent = 15;
                    }
                    amountToPay = productPrice;
                    discountAmount = (productPrice/100) * discountPercent;
                    priceAfterDiscount = amountToPay - discountAmount ;

                    String productCategory = product.getProductCategory();
                    String orderStatus = "UNPAID";
                    addOrderToList(orderCustomerId, productID, productName, priceAfterDiscount, productCategory, orderId, orderStatus);
                    System.out.println("Price before Discount: " + amountToPay);
                }
                System.out.println("Price after discount: " + priceAfterDiscount);
                Customer.updateCustomerAmountSpent(priceAfterDiscount);
            }

        }
    }

    public static void updateOrderStatus(){
        ArrayList<Order> ordersFound = new ArrayList<>();
        Scanner statusInput = new Scanner(System.in);
        System.out.println("Please enter the Order's ID that you wish to update the status of: ");
        String orderIdToUpdate = statusInput.next();

        for (Order order : orders){
            if (order.orderID.contains(orderIdToUpdate)){        //Has to be Case sensitive1
                ordersFound.add(order);
            }
        }
        if (ordersFound.isEmpty()){
            System.out.println("No such order is found!");
        }else {
            System.out.print("Please enter new status: ");
            String newStatus = statusInput.nextLine();
            while(!newStatus.equals("PAID") && !newStatus.equals("UNPAID")){
                System.out.println("Status must either changed to PAID or UNPAID!");
                newStatus = statusInput.nextLine();
            }
            for (Order order : ordersFound){
                order.setOrderStatus(newStatus);
            }
        }
    }

    public static void printOrderByCustomerID(){
        ArrayList<Order> ordersFound = new ArrayList<>();

        Scanner searchByCustomerId = new Scanner(System.in);

        System.out.print("Please enter the Customer's ID: ");
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

    public static void printOrderByOrderID(){
        ArrayList<Order> ordersFound = new ArrayList<>();

        Scanner searchByOrderId = new Scanner(System.in);

        System.out.print("Please enter the Order's ID: ");
        String orderIdFound = searchByOrderId.nextLine();

        for (Order order : orders){
            if (order.orderID.contains(orderIdFound)){
                ordersFound.add(order);
            }
        }
        if (ordersFound.isEmpty()){
            System.out.println("No order with matching ID is found");
        }else{
            System.out.println(String.format("%-15s%-15s%-20s%-15s%-20s%-20s%-15s", "Customer ID", "Order ID", "Order Status", "Product ID", "Product Name", "Product Price", "Product Category"));
            for (Order order : ordersFound){
                System.out.println( order.toString());
            }
        }
    }


    public static void writeOrders() throws IOException {         //Write Orders' data to file
        FileWriter orderCsvFile = new FileWriter("order.csv", false);
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
