package org.example.notify_observers;

import org.example.transactions.Transaction;

public class TransactionReceipt implements AccountObserver {
    private Transaction transaction;

    public TransactionReceipt(Transaction transaction){
        this.transaction = transaction;
    }
    @Override
    public void notifier(Transaction transaction) {
        System.out.println("------------Transaction Receipt----------");
        System.out.println("Transaction ID : " + transaction.getTransactionId());
        System.out.println("Amount         : " + transaction.getAmount());
        System.out.println("Date & Time    : " + transaction.getDate());
        System.out.println("From Account   : " + (transaction.getFromAccount() != null ?
                transaction.getFromAccount().getAccountNumber() : "N/A"));
        System.out.println("To Account     : " + (transaction.getToAccount() != null ?
                transaction.getToAccount().getAccountNumber() : "N/A"));
        System.out.println("Status         : " + transaction.getState());
        System.out.println("------------------------------------------");
    }
}

