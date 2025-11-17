package org.example.transactions;

import org.example.models.Account;

import java.time.LocalDateTime;

public abstract class Transaction implements ITransaction {

    private final String transactionId;     // required
    private final double amount;            // required
    private final LocalDateTime date;       // optional
    private final Account fromAccount;      // optional in deposit
    private final Account toAccount;        // optional in withdrawal
    private final String state;             // optional

    protected Transaction(Builder<?> builder) {
        this.transactionId = builder.transactionId;
        this.amount = builder.amount;
        this.date = builder.date != null ? builder.date : LocalDateTime.now();
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
        this.state = builder.state != null ? builder.state : "PENDING";
    }

    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
    public Account getFromAccount() { return fromAccount; }
    public Account getToAccount() { return toAccount; }
    public String getState() { return state; }

    public void printReceipt() {
        System.out.println("------------Transaction Receipt----------");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Amount         : " + amount);
        System.out.println("Date & Time    : " + date);
        System.out.println("From Account   : " + (fromAccount != null ?
                fromAccount.getAccountNumber() : "N/A"));
        System.out.println("To Account     : " + (toAccount != null ?
                toAccount.getAccountNumber() : "N/A"));
        System.out.println("Status         : " + state);
        System.out.println("------------------------------------------");
    }

    public boolean validate() {
        if (amount <= 0) {
            System.out.println("Invalid transaction amount");
            return false;
        }
        if (fromAccount == null && toAccount == null) {
            System.out.println("No valid accounts for transaction");
            return false;
        }
        return true;
    }

    public abstract void execute();

    // ================= BUILDER ====================
    public static abstract class Builder<T extends Builder<T>> {

        private final String transactionId;   // required
        private final double amount;          // required
        private Account fromAccount;          // optional
        private Account toAccount;            // optional
        private LocalDateTime date;           // optional
        private String state;                 // optional

        //  REQUIRED fields constructor
        public Builder(String transactionId, double amount) {
            this.transactionId = transactionId;
            this.amount = amount;
        }

        // OPTIONAL fields setters
        public T fromAccount(Account fromAccount) {
            this.fromAccount = fromAccount;
            return self();
        }

        public T toAccount(Account toAccount) {
            this.toAccount = toAccount;
            return self();
        }

        public T date(LocalDateTime date) {
            this.date = date;
            return self();
        }

        public T state(String state) {
            this.state = state;
            return self();
        }

        // For subclass builders
        protected abstract T self();

        // Build called by concrete transaction types
    }
}
