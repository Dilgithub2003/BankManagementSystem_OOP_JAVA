package org.example.models;

public class CheckingAccount extends Account{

    private double overdraftLimit;
    private boolean overdraftState = true;

    public void setOverdraftState(boolean overdraftState) {
        this.overdraftState = overdraftState;
    }

    public CheckingAccount(int accountNumber,double balance,double interestRate, Customer owner,double overdraftLimit){
        super(accountNumber,balance,interestRate,owner);
        super.setInterestRate(2.5/100);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if(amount>super.getBalance() && amount<(super.getBalance()+this.overdraftLimit) && this.overdraftState){
            super.setBalance(super.getBalance()+this.overdraftLimit);
            super.withdraw(amount);
            setOverdraftState(false);
        }else{
            super.withdraw(amount);
        }
    }

    @Override
    public double calculateInterest(){
        return super.getBalance()*super.getInterestRate();
    }}
