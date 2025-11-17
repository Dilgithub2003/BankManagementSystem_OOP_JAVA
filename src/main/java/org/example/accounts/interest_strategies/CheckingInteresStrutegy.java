package org.example.accounts.interest_strategies;

public class CheckingInteresStrutegy implements InterestStrategy {
    public double rate;

    public CheckingInteresStrutegy(){
        this.rate = 0.01 ;
    }

    @Override
    public double calculateInterest(double balance) {
        if(balance>100000){
            return balance * 0.02;
        }
        else {
            return balance * 0.01;
        }
    }
}