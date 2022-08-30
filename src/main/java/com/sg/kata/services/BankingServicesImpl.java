package com.sg.kata.services;

import com.sg.kata.error.ErrorEnum;
import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.account.IAccount;
import com.sg.kata.model.client.IBankClient;
import com.sg.kata.model.operation.BankDepositOperation;
import com.sg.kata.model.operation.BankWithdrawalOperation;
import com.sg.kata.model.operation.IBankOperation;
import org.apache.log4j.Logger;

import java.util.*;


public class BankingServicesImpl implements IBankingSevices {
    private static Logger LOGGER = Logger.getLogger(BankingServicesImpl.class);
    private static BankingServicesImpl accountServices;

    private BankingServicesImpl(){

    }

    synchronized public static BankingServicesImpl getInstance(){
        return accountServices == null ? new BankingServicesImpl() : accountServices;
    }
    @Override
    public synchronized boolean executeOperation(final IBankOperation bankOperation, IAccount account) throws InvalidOperationException {
        switch (bankOperation) {
            case BankDepositOperation bankDeposit -> {
                ((Account)account).setBalance(account.getBalance() + bankDeposit.getAmount());
                account.getOperations().add(bankDeposit);
                LOGGER.info("Amount successfully deposited into account " + account );
                //save the account
                return true;
            }
            case BankWithdrawalOperation bankWithdrawalOperation ->{

                if(isValidWithdrawal(bankWithdrawalOperation,account))
                    ((Account)account).setBalance(account.getBalance() - bankWithdrawalOperation.getAmount());
                    account.getOperations().add(bankWithdrawalOperation);
                    //save the account
                    LOGGER.info("Amount successfully withdrawed from account " + account);

                return true;
            }
            default ->
            {
                LOGGER.error(ErrorEnum.INVALID_OPERATION.getMessage());
                throw  new InvalidOperationException(ErrorEnum.INVALID_OPERATION.getCode());
            }
        }
    }

    @Override
    public Map<String, List<IBankOperation>> getOperationsHistory(IBankClient bankClient) {
        List<IAccount> accounts = bankClient.getAccounts();
        Map<String,List<IBankOperation>> operationsHistory = new HashMap<>();
        accounts.stream().forEach(
                iAccount -> {
                    operationsHistory.put(iAccount.getNumber(),orderList(iAccount.getOperations()));
                }
        );
        return operationsHistory;
    }

    private List<IBankOperation> orderList(List<IBankOperation> operations) {
        //sorting by date asc
        Collections.sort(operations, Comparator.comparing(IBankOperation::getDate));
        return operations;
    }


    private boolean isValidWithdrawal(BankWithdrawalOperation operation, IAccount account) throws InvalidOperationException {
        if(Double.compare(operation.getAmount(),account.getBalance()) < 0)
            return true;
        throw new InvalidOperationException(ErrorEnum.INSUFFICIENT_AMMOUNT.getCode());
    }

}
