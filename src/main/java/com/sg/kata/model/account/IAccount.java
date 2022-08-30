package com.sg.kata.model.account;

import com.sg.kata.model.operation.IBankOperation;

import java.util.Date;
import java.util.List;

public interface IAccount {

    String getNumber();


    double getBalance();

    Date getDateOpened();

    List<IBankOperation> getOperations();

}
