package com.sg.kata.model.client;


import com.sg.kata.model.account.IAccount;
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


}
