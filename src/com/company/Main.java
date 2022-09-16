package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ControlMain controlMain = new ControlMain();

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here
        controlMain.preProcessing();
        Scanner mainInput = new Scanner(System.in);
        Boolean running = true;
        System.out.print("COSC2081 GROUP ASSIGNMENT \n" +
                         "STORE ORDER MANAGEMENT SYSTEM \n" +
                         "Instructor: Mr. Minh Vu \n" +
                         "Group: Group 21 \n" +
                         "s3804812, Tran Tuan Minh \n" +
                         "s3926234, Nguyen Cong Chinh \n" +
                         "sXXXXXXX, Student Name \n");
        while (running) {
            System.out.print("===================================== \n"
                    + "[MENU] \n"
                    + "1. Login \n"
                    + "2. Register \n"
                    + "3. Exit \n"
                    + "Please input the desired choice: ");
            String choice = mainInput.nextLine();
            switch (choice) {
                case "1":
                    controlMain.login();
                    break;

                case "2":
                    controlMain.register();
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Input!");
            }
        }
    }
}
