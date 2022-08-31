package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static ControlMain controlMain = new ControlMain();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	// write your code here
        controlMain.preProcessing();
        Scanner mainInput = new Scanner(System.in);
        Boolean running = true;
        while (running) {
            System.out.print("===================================== \n"
                    + "[MENU] \n"
                    + "1. Login \n"
                    + "2. Register \n"
                    + "Please input the desired choice: ");
            int choice = mainInput.nextInt();
            switch (choice) {
                case 1:
                    controlMain.login();
                    break;

                case 2:
                    controlMain.register();
                    break;
            }
        }

    }
}
