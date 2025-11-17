package org.example.accounts.interest_strategies;


public class SavingInterestStrutegy implements InterestStrategy {

    private double rate;

    public SavingInterestStrutegy(double rate){
        this.rate = rate;
    }

    @Override
    public double calculateInterest(double balance) {
        return balance * rate ;
    }

}