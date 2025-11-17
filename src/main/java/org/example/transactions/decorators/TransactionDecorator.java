package org.example.transactions.decorators;

import org.example.models.Account;
import org.example.transactions.ITransaction;

public abstract class TransactionDecorator implements ITransaction {

    protected final ITransaction decorated;

    public TransactionDecorator(ITransaction decorated) {
        this.decorated = decorated;
    }

    // Delegate getters
    @Override
    public String getTransactionId() { return decorated.getTransactionId(); }

    @Override
    public double getAmount() { return decorated.getAmount(); }

    @Override
    public Account getFromAccount() { return decorated.getFromAccount(); }

    @Override
    public Account getToAccount() { return decorated.getToAccount(); }

    @Override
    public String getState() { return decorated.getState(); }


    @Override
    public boolean validate() { return decorated.validate(); }

    @Override
    public void printReceipt() { decorated.printReceipt(); }

    @Override
    public abstract void execute();
}
