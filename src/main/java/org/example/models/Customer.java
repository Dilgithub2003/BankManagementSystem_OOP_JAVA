package org.example.models;

import org.example.accounts.SavingAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> accounts;
    private String address;
    private String phoneNumber;

    public Customer(int userId,String name,String email,String password,String role,String address, String phoneNumber ){
        super(userId, name, email, password, role);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
    }

    // getter
    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    //setter
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account findAccount(int accountNumber){
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account; // Found match
            }
        }
        return null; // No match found
    }

    //behaviours
    @Override
    public void displayUserInfo(){
        System.out.println("User name: "+ getName()+"\n User email: "+getEmail()+"\n User Email: "+getEmail());
    }

    public Account selectAccount(int accountNum){
        int noOfAcc = 1;
        for (Account account:accounts ){
            System.out.println(noOfAcc + "=>"+"Acc no." + account.getAccountNumber() + "Type" + (account instanceof SavingAccount ?"Saving Account": "Checking Account"));
            if(accountNum == account.getAccountNumber()){
                return account;
            }
            noOfAcc++;

        }return null;
    }


    public void addAccount(){

    }

}
