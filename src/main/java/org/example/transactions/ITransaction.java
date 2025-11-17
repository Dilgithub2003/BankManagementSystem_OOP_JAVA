package org.example.transactions;

import org.example.models.Account;

public interface ITransaction {
    void execute();
    void printReceipt();
    boolean validate();

    String getTransactionId();
    double getAmount();
    Account getFromAccount();
    Account getToAccount();
    String getState();
}

