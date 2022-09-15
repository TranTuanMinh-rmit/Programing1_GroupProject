package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Account {
    private Integer accountIdNumber;

    static ArrayList<Account> admins = new ArrayList<>();

    public Admin(String userName, String passWord, String accountID) {
        super(userName, passWord, accountID);
    }


    //Admin Control menu
    public static void adminMenu() throws IOException {       //Main menu for an Admin
        Scanner adminInput = new Scanner(System.in);

        Boolean running = true;
        while (running){
            System.out.print("===================================== \n"
                    + "[MENU] \n"
                    + "1. Add a new Product \n"
                    + "2. Update the Product's price \n"
                    + "3. Display all Products \n"
                    + "4. Display all Customers \n"
                    + "5. Display all Orders \n"
                    + "6. Update Order status \n"
                    + "7. View Orders by Customer ID \n"
                    + "8. Log Out \n"
                    + "Please input the desired choice: ");
            int choice = adminInput.nextInt();
            switch(choice){
                case 1:
                    Product.createProduct();
                    break;

                case 2:
                    Product.updatePrice();
                    Product.writeProducts();
                    break;

                case 3:
                    Product.printProduct();
                    break;

                case 4:
                    Customer.printAllCustomer();
                    break;

                case 5:
                    Order.printOrder();
                    break;

                case 6:
                    Order.updateOrderStatus();
                    Customer.updateCustomerTier();
                    Order.writeOrders();
                    break;
                case 7 :
                    Order.printOrderByCustomerID();
                    break;

                case 8:
                    System.out.println();
                    System.out.println("Logging Out. See you next time!");
                    Customer.writeCustomer();
                    Product.writeProducts();
                    Order.writeOrders();
                    running = false;
                    break;
            }
        }
    }

    public static void readAdmin() throws FileNotFoundException {       //Read Admin's data fro file
        Scanner adminReader = new Scanner(new File("admin.csv"));
        adminReader.useDelimiter(",|\n");

        while (adminReader.hasNext()){
            String userName = adminReader.next();
            String passWord = adminReader.next();
            String accountId = adminReader.next();
            addAdminToList(userName, passWord, accountId);
        }
    }

    private static void addAdminToList(String userName, String passWord, String accountId) {
        Admin admin = new Admin(userName, passWord, accountId);
        admins.add(admin);
    }

    public static void printAdmin(){
        for (int i = 0; i < admins.size(); i++){
            System.out.println(admins.get(i));
        }
    }


    @Override
    public String toString() {
        return String.format(userName + ", " + passWord + ", " + accountID);
    }
}
