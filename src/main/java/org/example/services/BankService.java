package org.example.services;

import org.example.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<Customer> customers;
    private List<Transaction> transactions;
    private String bankName;

    public BankService(String bankName ){
        this.customers = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.bankName = bankName;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public Customer createCustomer(int userId, String name, String email, String password, String role, String address, String phoneNumber){
        Customer customer = new Customer(userId, name, email, password, role ,address, phoneNumber);
        customers.add(customer);
        System.out.println(" Customer created successfully: " + name);
        return customer;
    }

    public Account createAccount(String type, int accountNumber,double balance,double interestRate, Customer owner, double minimumBalance, double withdrawLimit, double overdraftLimit){
        Account account = null;
        switch (type) {
            case "Saving":
                SavingAccount savingAccount = new SavingAccount(accountNumber, balance, interestRate, owner, minimumBalance, withdrawLimit);
                owner.getAccounts().add(savingAccount);
                System.out.println(" Saving account created successfully.");
                return savingAccount;


            case "Checking":
                CheckingAccount checkingAccount = new CheckingAccount(accountNumber, balance, interestRate, owner, overdraftLimit);
                owner.getAccounts().add(checkingAccount);
                System.out.println(" Checking account created successfully.");
                return checkingAccount;


            default:
                System.out.println(" Invalid account type!");
        }
        return  account;
    }

    public Customer loginCustomer(String email, String password){
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password)) {
                return customer; // Found match
            }
        }
        return null; // No match found
    }

    public Account findAccount(int accountNumber){
        for (Customer customer: customers){
            for (Account account: customer.getAccounts() ){
                if(account.getAccountNumber() == accountNumber){
                    return account;
                }
            }
        }
        return null;
    }



    public void depositMoney(String transactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount, String state){
        DepositeTransaction depositeTransaction = new DepositeTransaction(transactionId, amount, date, fromAccount, toAccount);
        depositeTransaction.execute();
        transactions.add(depositeTransaction);
    }

    public void withdrawMoney(String transactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount, String state){
        WithdrawTransaction withdrawTransaction = new WithdrawTransaction(transactionId, amount, date, fromAccount, toAccount,state);
        withdrawTransaction.execute();
        transactions.add(withdrawTransaction);
    }

    public void transfer(String trnsactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount, String state){
        TransferTransaction transferTransaction = new TransferTransaction(trnsactionId, amount, date, fromAccount, toAccount, state);
        transferTransaction.execute();
        transactions.add(transferTransaction);
    }

    // Display all customers
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        System.out.println("\n===== Customer List =====");
        for (Customer c : customers) {
            System.out.println("Name: " + c.getName() + " | Email: " + c.getEmail());
        }
    }

    //  Display customer accounts
    public void displayCustomerAccounts(Customer customer) {
        System.out.println("\n===== Accounts for " + customer.getName() + " =====");
        for (Account acc : customer.getAccounts()) {
            System.out.println("Account No: " + acc.getAccountNumber() + " | Balance: " + acc.getBalance());
        }
    }

    //  View all transactions
    public void viewAllTransactions() {
        System.out.println("\n===== All Transactions =====");
        for (Transaction t : transactions) {
            System.out.println("Transaction ID: " + t.getTrnsactionId() + " | Amount: " + t.getAmount());
        }
    }

    // Getters
    public List<Customer> getCustomers() {
        return customers;
    }

    public String getBankName() {
        return bankName;
    }
}
