package org.example.transactions.decorators;

import org.example.transactions.ITransaction;

public class FeeDecorator extends TransactionDecorator {

    private final double fee = 25;

    public FeeDecorator(ITransaction transaction) {
        super(transaction);
    }

    @Override
    public void execute() {
        decorated.execute();
        System.out.println("A fee of " + fee + " was added.");
    }
}

