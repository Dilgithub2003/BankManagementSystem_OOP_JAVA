package org.example.services;

import org.example.accounts.CheckingAccount;
import org.example.accounts.SavingAccount;
import org.example.factory.TransactionFactory;
import org.example.models.Account;
import org.example.models.AccountType;
import org.example.models.Customer;
import org.example.models.User;
import org.example.notify_observers.TransactionReceipt;
import org.example.transactions.*;
import org.example.transactions.decorators.FeeDecorator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<Customer> customers;
    private List<Transaction> transactions;
    private String bankName;
    private Transaction transaction ;

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
                SavingAccount savingAccount = new SavingAccount(accountNumber, balance, owner, minimumBalance, withdrawLimit);
                owner.getAccounts().add(savingAccount);
                System.out.println(" Saving account created successfully.");
                return savingAccount;


            case "Checking":
                CheckingAccount checkingAccount = new CheckingAccount(accountNumber, balance, owner, overdraftLimit);
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


    public void depositMoney(String transactionId, double amount, Account account) {

        ITransaction tx = TransactionFactory.create(
                TransactionType.DEPOSIT,
                transactionId,
                amount,
                account,   // from
                account    // to
        );

        // Add decorator
        tx = new FeeDecorator(tx);

        // Add observer (receipt)
        account.addObserver(new TransactionReceipt(transaction));

        // Execute
        tx.execute();

        // Log transaction
        //transactions.add((Transaction) tx);
    }


    public void withdrawMoney(String transactionId, double amount, Account account) {

        ITransaction tx = TransactionFactory.create(
                TransactionType.WITHDRAW,
                transactionId,
                amount,
                account,   // from
                account    // to (still account for receipt)
        );

        tx = new FeeDecorator(tx);
        account.addObserver(new TransactionReceipt(transaction));

        tx.execute();
        //transactions.add((Transaction) tx);
    }


    public void transferMoney(String transactionId, double amount, Account fromAcc, Account toAcc) {

        ITransaction tx = TransactionFactory.create(
                TransactionType.TRANSFER,
                transactionId,
                amount,
                fromAcc,
                toAcc
        );

        tx = new FeeDecorator(tx);

        fromAcc.addObserver(new TransactionReceipt(transaction));
        toAcc.addObserver(new TransactionReceipt(transaction));

        tx.execute();
        //transactions.add((Transaction) tx);
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

    // Getters
    public List<Customer> getCustomers() {
        return customers;
    }

    public String getBankName() {
        return bankName;
    }
}
