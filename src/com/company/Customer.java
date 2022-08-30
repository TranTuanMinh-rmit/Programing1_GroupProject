package com.company;

import java.util.ArrayList;

public class Customer extends Account{
    private String customerFullName;
    private String customerPhoneNumber;
    private Integer customerAmountSpent;
    private String customerTier;

    ArrayList<Customer> customers = new ArrayList<>();

    private static Integer accountIDNumber = 0;

    //Super constructor
    public Customer(String userName, String passWord, String accountID, String customerFullName, String customerPhoneNumber, Integer customerAmountSpent, String customerTier) {
        super(userName, passWord, accountID);
        this.customerFullName = customerFullName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAmountSpent = customerAmountSpent;
        this.customerTier = customerTier;
    }

    public Customer() {

    }

    //Getter
    public String getCustomerFullName() {
        return customerFullName;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    public Integer getCustomerAmountSpent() {
        return customerAmountSpent;
    }
    public String getCustomerTier() {
        return customerTier;
    }

    //Setter
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void setCustomerAmountSpent(Integer customerAmountSpent) {
        this.customerAmountSpent = customerAmountSpent;
    }
    public void setCustomerTier(String customerTier) {
        this.customerTier = customerTier;
    }

    @Override
    public String createAccountID() {                           ///This is to create a unique ID for the Customer Account
        return String.format("CTM_%04d", accountIDNumber++);
    }



    ///Remember to write checks for taken username

}
