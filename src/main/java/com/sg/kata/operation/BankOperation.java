package com.sg.kata.operation;

import lombok.Getter;

import java.util.Date;

@Getter
//@Setter
public abstract class BankOperation implements IBankOperation{
    protected Date date;

    protected void isValidOperation(final double operationAmount){
        if(operationAmount < 0){
            throw new IllegalArgumentException("The Amount Cannot be negative!");
        }
    }

    @Override
    public String toString() {
        return "BankOperation{" +
                "date=" + date +
                '}';
    }
}
