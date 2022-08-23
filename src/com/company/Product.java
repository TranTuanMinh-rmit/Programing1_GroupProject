package com.company;

public class Product {
    protected String productID;
    protected String productName;
    protected Integer productPrice;
    protected String productCategory;

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
