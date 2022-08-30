package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Account {
    private Integer accountIdNumber;

    static ArrayList<Admin> admins = new ArrayList<>();

    public Admin(String userName, String passWord, String accountID) {
        super(userName, passWord, accountID);
    }


    //Control menu
    public static void adminMenu(){
        System.out.print("W.I.P");
    }

    public static void verifyAdmin(String userName, String passWord){
        for (Admin admin : admins){
            if (admin.getUserName().equals(userName) && admin.getPassWord().equals(passWord)){
                adminMenu();
            }
        }
    }

    ///Admin methods
    public static void readAdmin() throws FileNotFoundException {
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
    public String createAccountID() {
        return String.format("ADMIN_%03d", accountIdNumber++);
    }

}
