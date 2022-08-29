package com.sg.kata.bank;

import com.sg.kata.client.BankClient;
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
