package org.example.factory;

import org.example.accounts.CheckingAccount;
import org.example.accounts.SavingAccount;
import org.example.models.Account;
import org.example.models.AccountType;
import org.example.models.Customer;

public class AccountFactory {

    public static Account createAccount(
            AccountType type,
            int accountNumber,
            double balance,
            double interestRate,
            Customer customer,
            double minBalance,
            double withdrawLimit,
            double overdraftLimit
    ) {
        switch (type) {

            case SAVING:
                return new SavingAccount(
                        accountNumber,
                        balance,
                        customer,
                        minBalance,
                        withdrawLimit
                );

            case CHECKING:
                return new CheckingAccount(
                        accountNumber,
                        balance,
                        customer,
                        overdraftLimit
                );

            default:
                throw new IllegalArgumentException("Invalid account type!");
        }
    }
}

