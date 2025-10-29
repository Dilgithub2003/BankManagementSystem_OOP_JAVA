package org.example.models;

public class SavingAccount extends Account{

    private double minimumBalance;
    private double withdrawLimit;

    public SavingAccount(int accountNumber, double balance, double interestRate, Customer owner, double minimumBalance, double withdrawLimit){
        super(accountNumber,balance,interestRate,owner);
        super.setInterestRate(2.5/100);
        this.minimumBalance = minimumBalance;
        this.withdrawLimit = withdrawLimit;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    @Override
    public double calculateInterest() {
        return super.getBalance()*super.getInterestRate();
    }

    @Override
    public void withdraw(double amount) {
        if(super.getBalance() - amount>minimumBalance){
            super.withdraw(amount);
        }else {
            System.out.println("Insufficient account balance");
        }
    }
}
