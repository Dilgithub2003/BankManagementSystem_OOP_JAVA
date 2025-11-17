package org.example.factory;

import org.example.models.Account;
import org.example.transactions.*;

import java.time.LocalDateTime;

public class TransactionFactory {

    public static ITransaction create(
            TransactionType type,
            String transactionId,
            double amount,
            Account fromAccount,
            Account toAccount
    ) {
        LocalDateTime now = LocalDateTime.now();

        switch (type) {

            case DEPOSIT:
                return new DepositeTransaction.Builder(transactionId, amount, toAccount)
                        .date(now)
                        .build();

            case WITHDRAW:
                return new WithdrawTransaction.Builder(transactionId, amount, fromAccount)
                        .date(now)
                        .build();

            case TRANSFER:
                return new TransferTransaction.Builder(transactionId, amount)
                        .date(now)
                        .fromAccount(fromAccount)
                        .toAccount(toAccount)
                        .build();

            default:
                throw new IllegalArgumentException("Unknown transaction type!");
        }
    }
}

