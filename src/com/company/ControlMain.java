package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Admin.admins;
import static com.company.Customer.customers;

public class ControlMain {

    ///This is for the programme to read data from the csv file and write to the ArrayList
    public void preProcessing() throws FileNotFoundException, InterruptedException {
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

    //This is for Customer to register their account
    public void register() throws IOException {
        Customer.createCustomer();
        Customer.writeCustomer();
    }

    //Login
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
        for (Admin admin : admins){
            for (Customer customer : customers){
                if (customer.getUserName().equals(userNameEntered) && customer.getPassWord().equals(passWordEntered)){
                    Customer.customerInSession.add(customer);
                    verified = true;
                    Customer.customerMenu();
                    break;
                } else if (admin.getUserName().equals(userNameEntered) && admin.getPassWord().equals(passWordEntered)) {
                    Admin.adminMenu();
                    verified = true;
                    break;
                }
            }
        }

        while(!verified == true){
            System.out.println("Your Username/Password is incorrect!");
            System.out.println("Please enter your account credentials:");

            System.out.print("Username: ");
            userNameEntered = loginScan.nextLine();
            System.out.print("Password: ");
            passWordEntered = loginScan.nextLine();
            for (Admin admin : admins){
                for (Customer customer : customers){
                    if (customer.getUserName().equals(userNameEntered) && customer.getPassWord().equals(passWordEntered)){
                        Customer.customerInSession.add(customer);
                        verified = true;
                        Customer.customerMenu();
                        break;
                    } else if (admin.getUserName().equals(userNameEntered) && admin.getPassWord().equals(passWordEntered)) {
                        Admin.adminMenu();
                        verified = true;
                        break;
                    }
                }
            }
        }
    }
}
