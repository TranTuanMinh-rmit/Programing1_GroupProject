package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {
    private static Integer productIdNumber = 0;
    protected String productID;
    protected String productName;
    protected Integer productPrice;
    protected String productCategory;

    //ArrayList
    ArrayList<Product> products = new ArrayList<>();

    //Constructor
    public Product(String productID, String productName, Integer productPrice, String productCategory) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    //Getters
    public String getProductID() {
        return productID;
    }
    public String getProductName() {
        return productName;
    }
    public Integer getProductPrice() {
        return productPrice;
    }
    public String getProductCategory() {
        return productCategory;
    }

    //Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    //Methods for creating, deleting, modifying products
    public void createProduct(){
        Scanner input00 = new Scanner(System.in);
        System.out.println("Please enter product's information: ");

        String productID = String.format("PRD%06d", productIdNumber++);

        System.out.print("Product's Name: ");
        String productName = input00.nextLine();

        System.out.print("Product's Category: ");
        String productCategory = input00.nextLine();

        System.out.print("Product's Price: ");
        Integer productPrice = input00.nextInt();

        addProductToList(productID, productName, productPrice, productCategory);
    }

    private void addProductToList(String productID, String productName, Integer productPrice, String productCategory){
        Product product = new Product(productID, productName, productPrice, productCategory);
        products.add(product);
    }

    public void updatePrice(){
        Integer newPrice = 0;
        Scanner input01 = new Scanner(System.in);
        System.out.println("Please enter the Product's ID that you wish to update the price of: ");
        String productIdToUpdate = input01.nextLine();

        for (Product product : products){
            if (product.getProductID().equalsIgnoreCase(productIdToUpdate)){
                System.out.print("Please enter the new price: ");
                newPrice = input01.nextInt();
                product.setProductPrice(newPrice);
            }
            else {
                System.out.print("There is so such products in the database!");
            }
        }
    }



    //toString method
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
