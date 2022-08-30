package com.sg.kata.model.bank;

import com.sg.kata.model.client.BankClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Bank {
    private final String name;

    @Setter
    private List<BankClient> customers;

}
