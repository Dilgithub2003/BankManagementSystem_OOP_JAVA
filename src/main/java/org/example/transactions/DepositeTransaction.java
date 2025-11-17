package org.example.transactions;

import org.example.models.Account;

public class DepositeTransaction extends Transaction {

    public DepositeTransaction(Builder builder) {
        super(builder);
    }

    @Override
    public void execute() {
        if (!validate()) return;

        getToAccount().deposit(getAmount(), this);
        System.out.println("Deposit successful!");
        //printReceipt();
    }

    // -------- Builder --------
    public static class Builder extends Transaction.Builder<Builder> {

        public Builder(String transactionId, double amount, Account toAccount) {
            super(transactionId, amount);
            super.toAccount(toAccount); // force required field
        }

        @Override
        protected Builder self() {
            return this;
        }

        public DepositeTransaction build() {
            return new DepositeTransaction(this);
        }
    }
}
