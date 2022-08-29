package com.sg.kata.client;


import com.sg.kata.account.IAccount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class IndividualBankClient extends BankClient {
    private List<IAccount> accounts;

    private String id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String placeOfBirth;


    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public String getContactInformation() {
        return null;
    }
}
