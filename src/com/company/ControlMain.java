package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ControlMain {

    public void preProcessing() throws FileNotFoundException, InterruptedException {    ///This is for the programme to read data from the csv file and write to the ArrayList
        System.out.println("Loading");  ///Cosmetic purposes
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

    public void register() throws IOException {     //This is for Customer to register their account
        Customer.createCustomer();
        Customer.writeCustomer();
    }

    public void login() throws IOException, InterruptedException {
        String userNameEntered;
        String passWordEntered;
        boolean verified = false;
        Scanner loginScan = new Scanner(System.in);

        System.out.println("Please enter your account credentials:");

        System.out.print("Username: ");
        userNameEntered = loginScan.nextLine();
        System.out.print("Password: ");
        passWordEntered = loginScan.nextLine();
        for (Account account : Customer.customers){
            if (account.getUserName().equals(userNameEntered) && account.getPassWord().equals(passWordEntered)){
                Customer.customerInSession.add((Customer) account);
                Product.readProduct();
                System.out.println("...");
                Thread.sleep(500);
                Order.readOrder();
                System.out.println("...");
                Thread.sleep(500);
                verified = true;
                Customer.customerMenu();
                break;
            } else if (userNameEntered.equals("admin") && passWordEntered.equals("admin")) {
                Product.readProduct();
                System.out.println("...");
                Thread.sleep(500);
                Order.readOrder();
                System.out.println("...");
                Thread.sleep(500);
                Admin.adminMenu();
                verified = true;
                break;
            }
        }
        while(!verified == true){
            System.out.println("Your Username/Password is incorrect!");
            System.out.println("Please enter your account credentials:");

            System.out.print("Username: ");
            userNameEntered = loginScan.nextLine();
            System.out.print("Password: ");
            passWordEntered = loginScan.nextLine();
        }
    }
}
