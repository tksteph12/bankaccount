package com.sg.kata.services;

import com.sg.kata.error.ErrorEnum;
import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.operation.BankDepositOperation;
import com.sg.kata.model.operation.BankWithdrawalOperation;
import com.sg.kata.model.operation.IBankOperation;
import org.apache.log4j.Logger;


public class AccountServicesImpl implements IAccountSevices{
    private static Logger LOGGER = Logger.getLogger(AccountServicesImpl.class);
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
                LOGGER.info("Amount successfully deposited into account " + account.getNumber() );
                //save the account
                return true;
            }
            case BankWithdrawalOperation bankWithdrawalOperation ->{

                if(isValidWithdrawal(bankWithdrawalOperation,account))
                    account.setBalance(account.getBalance() - bankWithdrawalOperation.getAmount());
                    account.getOperations().add(bankWithdrawalOperation);
                    //save the account
                    LOGGER.info("Amount successfully withdrawed from account");

                return true;
            }
            default ->
            {
                LOGGER.error(ErrorEnum.INVALID_OPERATION.getMessage());
                throw  new InvalidOperationException(ErrorEnum.INVALID_OPERATION.getCode());
            }
        }
    }

    private boolean isValidWithdrawal(BankWithdrawalOperation operation, Account account) throws InvalidOperationException {
        if(Double.compare(operation.getAmount(),account.getBalance()) < 0)
            return true;
        throw new InvalidOperationException(ErrorEnum.INSUFFICIENT_AMMOUNT.getCode());
    }

}
