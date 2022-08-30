package com.company;

import java.io.FileNotFoundException;

public class Main {
    private static ControlMain controlMain = new ControlMain();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	// write your code here
        controlMain.preProcessing();
        controlMain.login();

    }
}
