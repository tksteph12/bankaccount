package com.sg.kata.model.account;

import com.sg.kata.model.operation.IBankOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Account implements IAccount {
    private final String number;
    protected List<IBankOperation> operations = new ArrayList<>();
    private double balance;
    protected Date dateOpened;
    
    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", opened Since=" + dateOpened +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (!Objects.equals(number, account.number))
            return false;
        if (!Objects.equals(operations, account.operations)) return false;
        return Objects.equals(dateOpened, account.dateOpened);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = number != null ? number.hashCode() : 0;
        result = 31 * result + (operations != null ? operations.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateOpened != null ? dateOpened.hashCode() : 0);
        return result;
    }
}

