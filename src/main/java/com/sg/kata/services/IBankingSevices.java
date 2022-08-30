package com.sg.kata.services;

import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.account.IAccount;
import com.sg.kata.model.client.IBankClient;
import com.sg.kata.model.operation.IBankOperation;

import java.util.List;
import java.util.Map;

public interface IBankingSevices {

    boolean executeOperation(IBankOperation iBankOperation, IAccount account) throws InvalidOperationException;
    Map<String,List<IBankOperation>> getOperationsHistory(IBankClient bankClient);
}
