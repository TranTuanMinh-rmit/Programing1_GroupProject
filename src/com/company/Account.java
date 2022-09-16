package com.company;

public abstract class Account {
    protected String userName;
    protected String passWord;
    protected String accountID;

    //Constructor
    public Account(String userName, String passWord, String accountID) {
        this.userName = userName;
        this.passWord = passWord;
        this.accountID = accountID;
    }

    public Account() {

    }

    //Getter

    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public String getAccountID() {
        return accountID;
    }

    //Setter
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
