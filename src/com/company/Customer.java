package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Product.*;

public class Customer extends Account{
    private String customerFullName;
    private String customerPhoneNumber;
    private Integer customerAmountSpent;
    private String customerTier;

    static ArrayList<Account> customers = new ArrayList<>();
    static ArrayList<Customer> customersWrite = new ArrayList<>();
    static ArrayList<Customer> customerInSession = new ArrayList<>();
    private static Integer accountIDNumber = 0;

    //Super constructor
    public Customer(String userName, String passWord, String accountID, String customerFullName, String customerPhoneNumber, Integer customerAmountSpent, String customerTier) {
        super(userName, passWord, accountID);
        this.customerFullName = customerFullName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAmountSpent = customerAmountSpent;
        this.customerTier = customerTier;
    }


    //Getter
    public String getCustomerFullName() {
        return customerFullName;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    public Integer getCustomerAmountSpent() {
        return customerAmountSpent;
    }
    public String getCustomerTier() {
        return customerTier;
    }

    //Setter
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void setCustomerAmountSpent(Integer customerAmountSpent) {
        this.customerAmountSpent = customerAmountSpent;
    }
    public void setCustomerTier(String customerTier) {
        this.customerTier = customerTier;
    }



    //Methods for Customer
    public static void createCustomer() {        ///Remember to write checks for taken username
        Scanner input02 = new Scanner(System.in);
        boolean running = true;

        System.out.println("Please enter your information: ");

        //This is for checking if username had already exist
        String userNameCheck = null;
        while(running){
            System.out.print("Username: ");
            userNameCheck = input02.nextLine();
            for (Account customer : customers) {
                if (customer.getUserName().equals(userNameCheck)) {
                    System.out.println("Username has already been taken, please enter a new one.");
                } else if (customers.isEmpty()) {
                    running = false;
                    break;
                } else{
                    running = false;
                    break;
                }
            }
        }

        String userName = userNameCheck;


        System.out.print("Password: ");
        String passWord = input02.nextLine();

        System.out.print("Full name: ");
        String fullName = input02.nextLine();
        while(!fullName.matches("^[a-zA-Z\\s]+")){
            System.out.println("Invalid Input!");
            fullName = input02.nextLine();
        }

        System.out.print("Phone Number: ");
        String phoneNumber = input02.nextLine();
        while(!phoneNumber.matches("^(\\d{3}[- .]?){2}\\d{4}$")){
            System.out.println("Invalid Input!");
            phoneNumber = input02.nextLine();
        }

        String customerTier = "Bronze";
        Integer amountSpent = 0;

        addCustomerToList(userName, passWord, createAccountID(), fullName, phoneNumber, amountSpent, customerTier);
    }

    static void customerMenu() throws FileNotFoundException {
        Scanner customerInput = new Scanner(System.in);
        setDiscountPrice();

        Boolean running = true;
        while (running) {
            System.out.print("===================================== \n"
                    + "[MENU] \n"
                    + "1. View my information \n"
                    + "2. Display all Products \n"
                    + "3. Display all Products by price (Asc) \n"
                    + "4. Display all Products by price (Desc) \n"
                    + "5. Display all Products by category \n"
                    + "6. Making Orders \n"
                    + "7. Display my Order status \n"
                    + "8. Logout \n"
                    + "Please input the desired choice: ");
            int choice = customerInput.nextInt();
            switch (choice) {
                case 1:
                    for (Account customer : customerInSession){
                        System.out.println("=====================================");
                        System.out.println(customer.toString());
                    }
                    break;

                case 2:
                    printProductPriceDiscount();
                    break;

                case 3:
                    Product.printProductPriceAsc();
                    break;

                case 4:
                    Product.printProductPriceDesc();
                    break;

                case 5:
                    Product.printProductByCategory();
                    break;

                case 6:
                    Order.createOrder();
                    break;

                case 7:
                    Order.printOrderByOrderID();
                    break;

                case 8:
                    System.out.println();
                    customerInSession.clear();
                    System.out.println("Logging Out. See you next time!");
                    running = false;
                    break;
            }
        }
    }

    public static void printAllCustomer(){
        for (int i = 0; i < customers.size(); i++){
            System.out.println(customers.get(i));
        }
    }

    public static void updateCustomerAmountSpent(Integer amountToPay){
        for (Customer customer : customersWrite){
            for (Customer customer1 : customerInSession){
                customer.setCustomerAmountSpent(customer1.customerAmountSpent +=amountToPay);
            }
        }
    }

    public static void printProductPriceDiscount(){
        for (Product product : productsDiscountPrice){
            System.out.println(product.toString());
        }
    }

    public static void setDiscountPrice(){                                  //This is a very dirty way of doing discount for prices

    }

    public static void updateCustomerTier(){
        for (Customer customer : customersWrite){
            if (customer.customerAmountSpent > 5000000 && customer.customerAmountSpent <= 10000000){
                customer.setCustomerTier("Silver");
                break;
            } else if (customer.customerAmountSpent > 10000000 && customer.customerAmountSpent <= 25000000) {
                customer.setCustomerTier("Gold");
                break;
            }else if (customer.customerAmountSpent > 25000000){
                customer.setCustomerTier("Platinum");
            }else {
                customer.setCustomerTier("Bronze");
            }
        }
    }



    public static void writeCustomer() throws IOException {       //Write Customer's data to file
        FileWriter customerCsvFile = new FileWriter("customer.csv", false);
        PrintWriter out0 = new PrintWriter(customerCsvFile);

        for (Customer customer : customersWrite){
            out0.printf("%s,%s,%s,%s,%s,%d,%s\n", customer.getUserName(), customer.getPassWord(), customer.getAccountID(), customer.getCustomerFullName(), customer.getCustomerPhoneNumber(), customer.getCustomerAmountSpent(), customer.getCustomerTier());
        }
        out0.close();
    }

    public static void readCustomer() throws FileNotFoundException {        //This is reading from a file and adding it to the ArrayList
        Scanner customerReader = new Scanner(new File("customer.csv"));
        customerReader.useDelimiter(",|\n");

        while (customerReader.hasNext()){
            String userName = customerReader.next();
            String passWord = customerReader.next();
            String accountId = customerReader.next();
            String customerName = customerReader.next();
            String customerPhone = customerReader.next();
            Integer customerAmountSpent = customerReader.nextInt();
            String customerTier = customerReader.next();
            addCustomerToList(userName, passWord, accountId, customerName, customerPhone, customerAmountSpent, customerTier);
        }
    }

    //This is for adding the newly created Customer to a list
    private static void addCustomerToList(String userName, String passWord, String accountID, String customerFullName, String customerPhoneNumber, Integer customerAmountSpent, String customerTier) {
        Customer customer = new Customer(userName, passWord, accountID, customerFullName, customerPhoneNumber, customerAmountSpent, customerTier);
        customers.add(customer);
        customersWrite.add(customer);
    }



    public static String createAccountID() {                           ///This is to create a unique ID for the Customer Account. Use this in createCustomer()
        return String.format("CTM_%04d", accountIDNumber++);
    }

    @Override
    public String toString(){
        return String.format(userName + ", " + passWord + ", " + accountID + ", " + customerFullName + ", " + customerPhoneNumber + ", " + customerAmountSpent + ", " + customerTier);
    }
}
