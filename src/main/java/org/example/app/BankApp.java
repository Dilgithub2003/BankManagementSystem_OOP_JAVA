package org.example.app;

import org.example.models.*;
import org.example.services.BankService;
import org.example.transactions.Transaction;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BankApp {
    public static void displayMainMenu(){
        System.out.println(
                "----Welcome to Hatton National Bank online----\n"+
                "1-> Sighup user\n"+
                "2-> Register user\n"+
                "3-> Crete account\n"+
                "4-> Check balance\n"+
                "5-> Deposit money\n"+
                "6-> Withdraw money\n"+
                "7-> Transfer funds\n"+
                "8-> Select account\n"+
                "9-> Calculate interest\n"+
                "10-> Logout\n"+
                "Choose option.."
        );
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankService bankService = new BankService("HNB");
        Customer customer = null;
        Account account = null;
        Account reviverAccount = null;
        Transaction transaction = null;

        while (true){
            displayMainMenu();
            int option = input.nextInt();
            switch (option){
                case 1:
                    System.out.println("----User Login----");
                    input.nextLine();
                    System.out.println("Enter ypur email:");
                    String userEmail = input.nextLine();

                    System.out.println("Enter password");
                    String userpwd = input.nextLine();

                    // Login customer
                    customer = bankService.loginCustomer(userEmail,userpwd);

                    if (customer == null){
                        System.out.println("Invalid user ID or Password");
                        break;
                    }else {
                        System.out.println("Autherization Successful ");
                    }
                    break;



                case 2 :
                    System.out.println("----User Registration----");

                    System.out.print("Enter customer ID: ");
                    int userId = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter name: ");
                    String name = input.nextLine();

                    System.out.print("Enter email: ");
                    String email = input.nextLine();

                    System.out.print("Enter password: ");
                    String password = input.nextLine();

                    System.out.print("Enter address: ");
                    String address = input.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = input.nextLine();

                    // Registering customer
                    customer = bankService.createCustomer(userId, name, email, password, "Customer", address, phone);

                    break;

                case 3:
                    System.out.println("----Create Account----");
                    if(customer == null){
                        System.out.println("Please register first !");
                        break;
                    }
                    input.nextLine();
                    System.out.print("Enter account type (Saving/Checking): ");
                    String type = input.nextLine();

                    System.out.print("Enter account number: ");
                    int accNum = input.nextInt();

                    System.out.print("Enter initial balance: ");
                    double balance = input.nextDouble();

                    System.out.print("Enter interest rate: ");
                    double rate = input.nextDouble();

                    if (type.equalsIgnoreCase("saving")) {
                        System.out.print("Enter minimum balance: ");
                        double minBalance = input.nextDouble();

                        System.out.print("Enter withdrawal limit: ");
                        double withdrawLimit = input.nextDouble();

                        // Create Saving account
                        account = bankService.createAccount(type, accNum, balance, rate, customer, minBalance, withdrawLimit, 0);


                    } else {
                        System.out.print("Enter overdraft limit: ");
                        double overdraftLimit = input.nextDouble();

                        // Create checking account
                        account = bankService.createAccount(type, accNum, balance, rate, customer, 0, 0, overdraftLimit);

                    }

                    break;

                case 4:
                    System.out.println("----Get account balance----");
                    if(customer == null || account==null ){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Your account balance is "+ account.getBalance() );
                    break;


                case 5:
                    System.out.println("----Deposite money----");
                    if(customer == null || account == null){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Enter the amount to be deposit :");
                    double depositAmount = input.nextDouble();

                    input.nextLine();

                    System.out.println("Enter transaction ID: ");
                    String transActionId = input.nextLine();

                    LocalDateTime date = LocalDateTime.now();

                    Account fromAccount = account;
                    Account toAccount = account;

                    bankService.depositMoney(transActionId, depositAmount, fromAccount);

                    break;


                case 6:
                    System.out.println("----Withdraw money----");
                    if(customer == null || account == null){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Enter the amount to be withdraw :");
                    double withdrawAmount = input.nextDouble();

                    input.nextLine();

                    System.out.println("Enter transaction ID: ");
                    String wtransActionId = input.nextLine();

                    LocalDateTime wdate = LocalDateTime.now();
                    Account wfromAccount = account;
                    Account wtoAccount = account;

                    bankService.withdrawMoney(wtransActionId, withdrawAmount, account);

                    break;

                case 7:
                    System.out.println("----Transfer money----");
                    if(customer == null || account == null){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Enter account number to transfer: ");
                    int reciverAccNo = input.nextInt();

                    reviverAccount = bankService.findAccount(reciverAccNo);
                    if (reviverAccount == null){
                        System.out.println("Count find matching account !");
                        break;
                    }
                    System.out.println("Enter the amount to be Transfer :");
                    double transfer = input.nextDouble();

                    input.nextLine();

                    System.out.println("Enter transaction ID: ");
                    String transferActionId = input.nextLine();

                    LocalDateTime tdate = LocalDateTime.now();
                    Account tfromAccount = account;

                    bankService.transferMoney(transferActionId, transfer, account, reviverAccount);

                    break;


                case 8:
                    System.out.println("----Select account----");
                    if (customer == null){
                        System.out.println("Please login or signup");
                    }
                    System.out.println("Enter your account number");
                    int accNo = input.nextInt();

                    account = null;

                    account = customer.selectAccount(accNo);

                    if(account == null) {
                        System.out.println("Not found account");
                        break;
                    }

                    System.out.println("Account autherized sucessfully");
                    break;


                case 9:
                    System.out.println("----Calculate Interest----");
                    if(customer == null || account == null){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Your annual interest is "+account.calculateInterestStrutegy());
                    break;


                case 10:
                    customer = null;
                    account = null;
                    System.out.println(" Logged out successfully.");
                    break;
            }
        }
    }
}
