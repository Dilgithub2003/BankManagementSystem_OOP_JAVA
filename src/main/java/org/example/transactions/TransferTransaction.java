package org.example.transactions;

public class TransferTransaction extends Transaction {

    public TransferTransaction(Builder builder) {
        super(builder);
    }

    @Override
    public void execute() {
        if (!validate()) return;

        getFromAccount().withdraw(getAmount(), this);
        getToAccount().deposit(getAmount(), this);
        System.out.println("Transfer successful!");
        //printReceipt();
    }

    // -------- Builder --------
    public static class Builder extends Transaction.Builder<Builder> {

        public Builder(String transactionId, double amount) {
            super(transactionId, amount);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public TransferTransaction build() {
            return new TransferTransaction(this);
        }
    }
}
