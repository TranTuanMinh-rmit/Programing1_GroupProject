package com.company;

public class Admin extends Account{
    private Integer accountIdNumber;

    //Super constructor
    public Admin(String userName, String passWord, String accountID) {
        super(userName, passWord, accountID);
    }

    @Override
    public String createAccountID() {
        return String.format("ADMIN_%03d", accountIdNumber++);
    }
    //Username(Inherit)
    //password(Inherit)
    //
}