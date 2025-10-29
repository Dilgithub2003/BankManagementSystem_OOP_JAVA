package org.example.models;

import java.time.LocalDateTime;

public abstract class Transaction {
    private String transactionId;
    private double amount;
    private LocalDateTime date;
    private Account fromAccount;
    private  Account toAccount;
    private String state = "PENDING";

    public Transaction(String trnsactionId, double amount, LocalDateTime date,Account fromAccount,Account toAccount ){
        this.transactionId = trnsactionId;
        this.amount = amount;
        this.date = date;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getTrnsactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public String getState(){
        return state;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void printReceipt(){
        System.out.println("------------Transaction Receipt----------");
        System.out.println("TransctionID : "+transactionId );
        System.out.println("Amount "+amount);
        System.out.println("Date & Time "+ date);
        System.out.println("From account "+fromAccount != null ? fromAccount.getAccountNumber() : "N/A" );
        System.out.println("To Account "+toAccount != null? toAccount.getAccountNumber():"N/A");
        System.out.println("Status "+ state);
        System.out.println("----------------------------------------");
    }

    protected boolean validate(){
        if(amount==0){
            System.out.println("Invalid transaction amount");
            return false;
        }
        if(fromAccount == null && toAccount == null){
            System.out.println("No valid accounts accosiated with transaction");
            return false;
        }
        return true;
    }

    public abstract void execute();
}
