package com.sg.kata.client;

import com.sg.kata.account.Account;
import com.sg.kata.account.IAccount;

import java.util.List;

public interface IBankClient {

    String getId();
    boolean createAccount();
    List<IAccount> getAccounts();
    String getContactInformation();

}
