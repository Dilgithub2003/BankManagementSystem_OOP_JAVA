package org.example.models;

import org.example.accounts.interest_strategies.InterestStrategy;
import org.example.notify_observers.AccountObserver;
import org.example.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private int accountNumber;
    private double balance;
    private InterestStrategy interestStrategy;
    private Customer owner;
    // calculateInterest
    // === Observer List ===
    private final List<AccountObserver> observers = new ArrayList<>();

    // === Constructor ===
    public Account(int accountNumber, double balance, InterestStrategy interestStrategy, Customer owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestStrategy = interestStrategy;
        this.owner = owner;
    }

    // === Observer handling ===
    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Transaction transaction) {
        for (AccountObserver observer : observers) {
            observer.notifier(transaction);
        }
    }

    // === Account operations ===
    public void deposit(double amount, Transaction transaction) {
        this.balance += amount;

        System.out.println("Money deposited successfully into account " + accountNumber);

        // Notify observers (receipt, SMS, etc.)
        notifyObservers(transaction);
    }

    public void withdraw(double amount, Transaction transaction) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Money withdrawn successfully from account " + accountNumber);

            notifyObservers(transaction);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    // === Getters & Setters ===
    public double getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public Customer getOwner() {
        return this.owner;
    }

    public double calculateInterestStrutegy() {
        return interestStrategy.calculateInterest(balance);
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInterestStrategy(InterestStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
