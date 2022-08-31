package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Account{
    private String customerFullName;
    private String customerPhoneNumber;
    private Integer customerAmountSpent;
    private String customerTier;

    static ArrayList<Customer> customers = new ArrayList<>();

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
    public static void createCustomer(){        ///Remember to write checks for taken username

    }

    public static void printCustomer(){
        for (int i = 0; i < customers.size(); i++){
            System.out.println(customers.get(i));
        }
    }


    public static void writeCustomer() throws FileNotFoundException {       //Write Customer's data to file
        File customerCsvFile = new File("customer.csv");
        PrintWriter out0 = new PrintWriter(customerCsvFile);

        for (Customer customer : customers){
            out0.printf("%s,%s,%s,%s,%s,%d,%s\n", customer.getUserName(), customer.getPassWord(), customer.getAccountID(), customer.getCustomerFullName(), customer.getCustomerPhoneNumber(), customer.getCustomerAmountSpent(), customer.getCustomerTier());
        }
        out0.close();
    }

    public static void readCustomer() throws FileNotFoundException {
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

    private static void addCustomerToList(String userName, String passWord, String accountID, String customerFullName, String customerPhoneNumber, Integer customerAmountSpent, String customerTier) {
        Customer customer = new Customer(userName, passWord, accountID, customerFullName, customerPhoneNumber, customerAmountSpent, customerTier);
        customers.add(customer);
    }


    @Override
    public String createAccountID() {                           ///This is to create a unique ID for the Customer Account
        return String.format("CTM_%04d", accountIDNumber++);
    }

    public String toString(){
        return String.format(userName + ", " + passWord + ", " + accountID + ", " + customerFullName + ", " + customerPhoneNumber + ", " + customerAmountSpent + ", " + customerTier);
    }
}
