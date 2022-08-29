package com.sg.kata.account;

import com.sg.kata.operation.BankDepositOperation;
import com.sg.kata.operation.IBankOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Account implements IAccount {
    private final String accountNumber;
    protected List<IBankOperation> operations;
    private double balance;

    protected Date dateOpened;

    @Override
    public synchronized boolean executeOperation(final IBankOperation bankOperation) {
        boolean result = false;
        switch (bankOperation) {
            case BankDepositOperation bankDeposit -> {
                this.balance += bankDeposit.getAmount();
                operations.add(bankDeposit);
                return true;
            }
            default -> throw new IllegalStateException("Unexpected value: " + bankOperation);
        }
    }

    @Override
    public String printAccountStatement() {
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", operations=" + operations +
                ", balance=" + balance +
                ", dateOpened=" + dateOpened +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (!Objects.equals(accountNumber, account.accountNumber))
            return false;
        if (!Objects.equals(operations, account.operations)) return false;
        return Objects.equals(dateOpened, account.dateOpened);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateOpened != null ? dateOpened.hashCode() : 0);
        return result;
    }
}

