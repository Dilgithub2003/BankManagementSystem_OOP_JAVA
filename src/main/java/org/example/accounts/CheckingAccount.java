package org.example.accounts;

import org.example.models.Account;
import org.example.accounts.interest_strategies.CheckingInteresStrutegy;
import org.example.models.Customer;
import org.example.transactions.Transaction;

public class CheckingAccount extends Account {

    private double overdraftLimit;
    private boolean overdraftState = true;

    public void setOverdraftState(boolean overdraftState) {
        this.overdraftState = overdraftState;
    }

    public CheckingAccount(int accountNumber, double balance, Customer owner, double overdraftLimit){
        super(accountNumber,balance,new CheckingInteresStrutegy(),owner);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount, Transaction transaction) {
        if(amount>super.getBalance() && amount<(super.getBalance()+this.overdraftLimit) && this.overdraftState){
            super.setBalance(super.getBalance()+this.overdraftLimit);
            super.withdraw(amount, transaction);
            setOverdraftState(false);
        }else{
            super.withdraw(amount, transaction);
        }
    }

}
