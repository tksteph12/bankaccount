package com.sg.kata.model.account;

import java.time.Instant;
import java.util.Date;

public class SavingsAccount extends Account{

    public SavingsAccount(final String number){
        super(number);
        dateOpened = Date.from(Instant.now());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
