package org.example.models;

public abstract class Account {
    private int accountNumber;
    private double balance;
    private double interestRate;
    private Customer owner;


    public Account(int accountNumber,double balance,double interestRate, Customer owner ){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.owner = owner;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public double getInterestRate() {
        return interestRate;
    }



    public void deposit(double amount){
        this.balance = balance + amount;
        System.out.println("Money deposited successful");
    }

    public void withdraw(double amount){
        if (this.balance > amount){
            this.balance = balance - amount;
            System.out.println("Money withdrawal successful");
        }else {
            System.out.println("Insufficient account balance");
        }
    }

    public double getBalance(){
        return this.balance;
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }

    public Customer getOwner(){
        return this.owner;
    }

    public abstract double calculateInterest();
}
