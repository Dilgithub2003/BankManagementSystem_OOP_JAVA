package org.example.models;

import java.time.LocalDateTime;

public class WithdrawTransaction extends Transaction{

    public WithdrawTransaction(String transactionId, double amount, LocalDateTime date, Account fromAccount, Account toAccount, String state){
        super(transactionId, amount, date, fromAccount, toAccount);
    }
    @Override
    public void execute() {
        super.validate();
        super.getToAccount().withdraw(super.getAmount());
        super.setState("SUCCESS");
        super.printReceipt();
    }
}
