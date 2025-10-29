package org.example.app;


import org.example.models.*;
import org.example.services.BankService;

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
                "9-> Logout\n"+
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

                    customer = bankService.loginCustomer(userEmail,userpwd);
                    if (customer == null){
                        System.out.println("Invalid user ID or Password");
                        break;
                    }else {
                        System.out.println("Autherization Successful ");
                    }
                    System.out.println("\n"+customer.getUserId());
                System.out.println(customer.getName());
                System.out.println(customer.getAddress());
                System.out.println(customer.getEmail());
                System.out.println(customer.getPassword());
                System.out.println(customer.getPhoneNumber());

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

                    customer = bankService.createCustomer(userId, name, email, password, "Customer", address, phone);
//                System.out.println("\n"+customer.getUserId());
//                System.out.println(customer.getName());
//                System.out.println(customer.getAddress());
//                System.out.println(customer.getEmail());
//                System.out.println(customer.getPassword());
//                System.out.println(customer.getPhoneNumber());
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

                        account = bankService.createAccount(type, accNum, balance, rate, customer, minBalance, withdrawLimit, 0);
                    } else {
                        System.out.print("Enter overdraft limit: ");
                        double overdraftLimit = input.nextDouble();

                        account = bankService.createAccount(type, accNum, balance, rate, customer, 0, 0, overdraftLimit);
                    }
                    //                System.out.println("\n"+customer.getUserId());
//                System.out.println(account.getAccountNumber());
//                System.out.println(account.getInterestRate());
//                System.out.println(account.getOwner());
//                System.out.println(account.getBalance());
//                if (account instanceof SavingAccount) {
//                    SavingAccount sa = (SavingAccount) account;
//                    System.out.println("Withdraw Limit: " + sa.getWithdrawLimit());
//                }
                break;

                case 4:
                    System.out.println("----Get account balance----");
                    if(customer == null || account==null ){
                        System.out.println("Not found registered user or account");
                        break;
                    }
                    System.out.println("Your account balance is "+ account.getBalance() );
                    //double amount = input.nextDouble();
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

                    transaction = new DepositeTransaction(transActionId, depositAmount, date, fromAccount, toAccount);
                    transaction.execute();

                    bankService.getTransactions().add(transaction);
//                    System.out.println(transaction.getAmount());
//                    System.out.println(transaction.getDate());
//                    System.out.println(transaction.getFromAccount());
//                    System.out.println(transaction.getToAccount());
//                    System.out.println(transaction.getState());
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

                    transaction = new WithdrawTransaction(wtransActionId, withdrawAmount, wdate, wfromAccount, wtoAccount, "Pending");
                    transaction.execute();

                    bankService.getTransactions().add(transaction);
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
//                    System.out.println(reviverAccount.getAccountNumber());
//                    System.out.println(reviverAccount.getInterestRate());
//                    System.out.println(reviverAccount.getOwner());
//                    System.out.println(reviverAccount.getBalance());
//                    if (reviverAccount instanceof SavingAccount) {
//                        SavingAccount sa = (SavingAccount) reviverAccount;
//                        System.out.println("Withdraw Limit: " + sa.getWithdrawLimit());
//                    }


                    System.out.println("Enter the amount to be Transfer :");
                    double transfer = input.nextDouble();



                    input.nextLine();

                    System.out.println("Enter transaction ID: ");
                    String transferActionId = input.nextLine();

                    LocalDateTime tdate = LocalDateTime.now();

                    Account tfromAccount = account;

                    bankService.transfer(transferActionId,transfer,tdate,account,reviverAccount,"PENDING");
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
                    customer = null;
                    account = null;
                    System.out.println(" Logged out successfully.");
                    break;


            }
        }

    }
}
