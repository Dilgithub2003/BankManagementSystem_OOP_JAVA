package org.example.transactions;

import org.example.models.Account;

public class WithdrawTransaction extends Transaction {

    private WithdrawTransaction(Builder builder) {
        super(builder);
    }

    @Override
    public void execute() {
        if (!validate()) return;

        getFromAccount().withdraw(getAmount(), this);
        System.out.println("Withdrawal successful!");
        //printReceipt();
    }

    // ---------- Builder ----------
    public static class Builder extends Transaction.Builder<Builder> {

        public Builder(String transactionId, double amount, Account fromAccount) {
            super(transactionId, amount);
            super.fromAccount(fromAccount); // required
        }

        @Override
        protected Builder self() {
            return this;
        }

        public WithdrawTransaction build() {
            return new WithdrawTransaction(this);
        }
    }
}
