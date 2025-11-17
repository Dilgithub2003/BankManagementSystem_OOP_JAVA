package org.example.accounts;

import org.example.models.Account;
import org.example.models.Customer;
import org.example.accounts.interest_strategies.SavingInterestStrutegy;
import org.example.transactions.Transaction;

public class SavingAccount extends Account {

    private double minimumBalance;
    private double withdrawLimit;

    public SavingAccount(int accountNumber, double balance, Customer owner, double minimumBalance, double withdrawLimit){
        super(accountNumber,balance,new SavingInterestStrutegy(0.02),owner);
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
    public void withdraw(double amount, Transaction transaction) {
        if(super.getBalance() - amount>minimumBalance){
            super.withdraw(amount, transaction);
        }else {
            System.out.println("Insufficient account balance");
        }
    }
}
