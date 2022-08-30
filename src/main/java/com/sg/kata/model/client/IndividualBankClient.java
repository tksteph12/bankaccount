package com.sg.kata.model.client;


import com.sg.kata.model.account.IAccount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class IndividualBankClient extends BankClient {
    private List<IAccount> accounts = new ArrayList<>();

    private String id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String placeOfBirth;

    public IndividualBankClient(String firstName, String lastName, Date birthDate, String placeOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
        this.birthDate = birthDate;
    }

}
