package org.example.models;

import java.time.LocalDateTime;

public class DepositeTransaction extends Transaction {

    public DepositeTransaction(String transactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount){
        super(transactionId, amount, date, fromAccount, toAccount);
    }
    @Override
    public void execute() {
        super.validate();
        super.getToAccount().deposit(super.getAmount());
        super.setState("SUCCESS");
        super.printReceipt();
    }
}
