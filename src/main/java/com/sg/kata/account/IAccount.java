package com.sg.kata.account;

import com.sg.kata.operation.IBankOperation;

import java.util.Date;

public interface IAccount {

    String getAccountNumber();

    boolean executeOperation(IBankOperation iBankOperation);

    double getBalance();

    Date getDateOpened();

    String printAccountStatement();

}
