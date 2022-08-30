package com.sg.kata.services;

import com.sg.kata.error.ErrorEnum;
import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.operation.BankDepositOperation;
import com.sg.kata.model.operation.IBankOperation;
import com.sg.kata.utils.BankLogger;


public class AccountServicesImpl implements IAccountSevices{

    private static AccountServicesImpl accountServices;

    private AccountServicesImpl(){

    }

    synchronized public static AccountServicesImpl getInstance(){
        return accountServices == null ? new AccountServicesImpl() : accountServices;
    }
    @Override
    public synchronized boolean executeOperation(final IBankOperation bankOperation, Account account) throws InvalidOperationException {
        switch (bankOperation) {
            case BankDepositOperation bankDeposit -> {
                account.setBalance(account.getBalance() + bankDeposit.getAmount());
                account.getOperations().add(bankDeposit);
                //save the account
                return true;
            }
            default ->
            {
                BankLogger.logError(ErrorEnum.INVALID_OPERATION.getMessage());
                throw  new InvalidOperationException(ErrorEnum.INVALID_OPERATION.getCode());
            }
        }
    }

}
