package org.example.models;

import java.time.LocalDateTime;

public class TransferTransaction extends Transaction {

    public TransferTransaction(String trnsactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount, String state) {
        super(trnsactionId, amount, date, fromAccount, toAccount);
    }

    @Override
    public void execute() {
        if(super.validate()){
            super.getFromAccount().withdraw(getAmount());
            super.getToAccount().deposit(getAmount());
            super.printReceipt();
        }
    }
}
