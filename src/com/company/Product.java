package com.company;

import java.io.*;
import java.util.*;

import static com.company.Customer.customerInSession;

public class Product {

    protected String productID;
    protected String productName;
    protected Integer productPrice;
    protected String productCategory;
    private static Integer discountPercentage;
    private static Integer discountedPrice;

    //ArrayList
    static ArrayList<Product> products = new ArrayList<>();                     //For Admin view
    static ArrayList<Product> productsDiscountPrice = new ArrayList<>();        //For Customer view
    static ArrayList<Product> productsPriceComparing = new ArrayList<>();       //This is for displaying price asc and desc without affecting the original order
    private static Integer productIdNumber = 0;
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
    public static void createProduct(){
        Random random = new Random();
        Scanner input00 = new Scanner(System.in);
        System.out.println("Please enter product's information: ");

        String productID = String.format("PRD_%06d", random.nextInt(1000000));

        System.out.print("Product's Name: ");
        String productName = input00.nextLine();

        System.out.print("Product's Category: ");
        String productCategory = input00.nextLine();

        System.out.print("Product's Price: ");
        Integer productPrice = input00.nextInt();

        addProductToList(productID, productName, productPrice, productCategory);
    }

    private static void addProductToList(String productID, String productName, Integer productPrice, String productCategory){
        Product product = new Product(productID, productName, productPrice, productCategory);
        products.add(product);
        productsDiscountPrice.add(product);
        productsPriceComparing.add(product);
    }

    public static void updatePrice(){
        ArrayList<Product> productsFound = new ArrayList<>();
        Scanner input01 = new Scanner(System.in);
        System.out.println("Please enter the Product's ID that you wish to update the price of: ");
        String productIdToUpdate = input01.nextLine();
        for (Product product : products){
            if (product.productID.contains(productIdToUpdate)){        //Has to be Case sensitive1
                productsFound.add(product);
            }
        }
        if (productsFound.isEmpty()){
            System.out.println("No such product is found!");
        }else {
            System.out.print("Please enter new price: ");
            Integer newPrice = input01.nextInt();
            for (Product product : productsFound){
                product.setProductPrice(newPrice);
            }
        }
    }

    public static void deleteProduct(){
        ArrayList<Product> productsFound = new ArrayList<>();
        Iterator itr = products.iterator();
        Scanner input01 = new Scanner(System.in);
        System.out.println("Please enter the Product's ID that you wish to remove: ");
        String productIdToRemove = input01.nextLine();
        for (Product product : products){
            if (product.productID.contains(productIdToRemove)){        //Has to be Case sensitive1
                productsFound.add(product);
            }
        }
        if (productsFound.isEmpty()){
            System.out.println("No such product is found!");
        }else {
            while (itr.hasNext()){
                String removeId = (String)itr.next();
                if(removeId.equals(productIdToRemove)){
                    itr.remove();
                }
            }
            System.out.print("Product removed!");

        }
    }

    public static void printProduct(){
        System.out.println(String.format("%-15s %-25s %-20s %15s", "Product ID", "Product Name", "Price", "Category"));
        for (Product product : products){
            System.out.println(String.format("%-15s %-25s %-20d %15s",product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductCategory()));
        }
    }

    public static void printProductByCategory(){
        ArrayList<Product> productsFound = new ArrayList<>();
        Scanner searchByCategory = new Scanner(System.in);

        System.out.print("Please enter the category's name: ");
        String categoryEntered = searchByCategory.nextLine();

        for (Product product : products){
            if (product.productCategory.toLowerCase().contains(categoryEntered.toLowerCase())){
                productsFound.add(product);
            }
        }
        if (productsFound.isEmpty()){
            System.out.println("No products in matching Category is found");
        }else{
            System.out.println(String.format("%-15s %-25s %-20s %15s", "Product ID", "Product Name", "Price", "Category"));
            for (Product product : productsFound){
                System.out.println(product.toString());
            }
        }
    }

    public static void printProductPriceAsc(){
        Collections.sort(productsPriceComparing, Comparator.comparing(Product::getProductPrice));
        System.out.println(String.format("%-15s %-25s %-20s %15s", "Product ID", "Product Name", "Price", "Category"));
        for (Product product : productsPriceComparing){
            System.out.println(String.format("%-15s %-25s %-20d %15s",product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductCategory()));
        }
    }

    public static void printProductPriceDesc(){
        Comparator<Product> priceComparator = Comparator.comparing(Product::getProductPrice);
        Collections.sort(productsPriceComparing, priceComparator.reversed());
        System.out.println(String.format("%-15s %-25s %-20s %15s", "Product ID", "Product Name", "Price", "Category"));
        for (Product product : productsPriceComparing){
            System.out.println(String.format("%-15s %-25s %-20d %15s",product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductCategory()));
        }
    }

    public static void writeProducts() throws IOException {       //Write Product's data to file
        FileWriter productCsvFile = new FileWriter("product.csv", false);
        PrintWriter out0 = new PrintWriter(productCsvFile);

        for (Product product : products){
            out0.printf("%s,%s,%d,%s\n", product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductCategory());
        }
        out0.close();
    }

    public static void readProduct() throws FileNotFoundException {
        Scanner productReader = new Scanner(new File("product.csv"));
        productReader.useDelimiter(",|\n");

        while (productReader.hasNext()){
            String productID = productReader.next();
            String productName = productReader.next();
            Integer productPrice = productReader.nextInt();
            String productCategory = productReader.next();
            addProductToList(productID, productName, productPrice, productCategory);
        }
    }



    //toString method
    @Override
    public String toString() {
        return String.format(productID + ", " + productName + ", " + productPrice + ", " + productCategory);
    }
}
