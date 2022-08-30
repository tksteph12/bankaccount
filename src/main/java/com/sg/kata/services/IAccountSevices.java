package com.sg.kata.services;

import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.operation.IBankOperation;

public interface IAccountSevices {

    boolean executeOperation(IBankOperation iBankOperation, Account account) throws InvalidOperationException;

}
