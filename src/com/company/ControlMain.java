package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ControlMain {

    public void preProcessing() throws FileNotFoundException, InterruptedException {    ///This is for the programme to read data from the csv file and write to the ArrayList
        System.out.println("Loading");
        Thread.sleep(500);
        Admin.readAdmin();
        System.out.println("...");
        Thread.sleep(500);
        Customer.readCustomer();
        System.out.println("...");
        Thread.sleep(500);
        Product.readProduct();
        System.out.println("...");
        Thread.sleep(500);
        Order.readOrder();
        System.out.println("...");
        Thread.sleep(500);
    }

    public void register() throws FileNotFoundException {     //This is for Customer to register their account
        Customer.createCustomer();
        Customer.writeCustomer();
    }

    //This class is for something IDK it's a Work in Progress
    public void login() throws FileNotFoundException {
        String userNameEntered;
        String passWordEntered;
        Scanner loginScan = new Scanner(System.in);

        System.out.println("Please enter your account credentials:");

        System.out.print("Username: ");
        userNameEntered = loginScan.nextLine();
        System.out.print("Password: ");
        passWordEntered = loginScan.nextLine();
        Admin.verifyAdmin(userNameEntered, passWordEntered);
        Admin.printAdmin();
    }

}
