package com.sg.kata.model.client;

import com.sg.kata.model.account.IAccount;

import java.util.List;

public interface IBankClient {

    List<IAccount> getAccounts();
}
