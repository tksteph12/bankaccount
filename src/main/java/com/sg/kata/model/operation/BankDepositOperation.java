package com.sg.kata.model.operation;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class BankDepositOperation extends BankOperation {
    private final double amount;

    public BankDepositOperation(final double depositAmount) {
        super.isValidOperation(depositAmount);
        this.amount = depositAmount;

        super.date = Date.from(Instant.now());
    }

}
