package org.example.notify_observers;


import org.example.transactions.Transaction;

public interface AccountObserver {
    public void notifier(Transaction transaction);
}
