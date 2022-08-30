package com.sg.kata.services;

import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.account.SavingsAccount;
import com.sg.kata.model.bank.Bank;
import com.sg.kata.model.client.IndividualBankClient;
import com.sg.kata.model.operation.BankDepositOperation;
import com.sg.kata.model.operation.BankOperation;
import com.sg.kata.model.operation.BankWithdrawalOperation;
import com.sg.kata.model.operation.IBankOperation;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sg.kata.error.ErrorEnum.INSUFFICIENT_AMMOUNT;
import static com.sg.kata.error.ErrorEnum.INVALID_OPERATION;
import static org.assertj.core.api.Assertions.assertThat;


public class TestAccountServicesImpl {

    private IAccountSevices accountSevices = AccountServicesImpl.getInstance();
    private Account account;
    private IndividualBankClient bankClient = new IndividualBankClient("Stephane","tatchum",null,"clamart");
    private Bank bank = new Bank("Bank Kata");

    private IBankOperation bankOperation;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("FR44012484787J55");
        bankOperation = new BankDepositOperation(200);
    }

    @Test
    void shouldReturnValidAmountDeposited() throws InvalidOperationException {
        bankClient.getAccounts().add(account);
        assertThat(account.getBalance()).isEqualTo(0);
        assertThat(accountSevices.executeOperation(bankOperation, account)).isTrue();

        assertThat(account.getBalance()).isEqualTo(Double.valueOf(200));
        assertThat("FR44012484787J55").isEqualTo(bankClient.getAccounts().get(0).getNumber());
    }

    @Test
    void shouldGetDepositedDate() {
        assertThat(bankOperation.getDate()).isNotNull();
    }

    @Test
    void shouldThrowInvalidOperationException() {
        bankOperation = new OtherOperation();
        ThrowableAssertAlternative<InvalidOperationException> exception = Assertions.assertThatExceptionOfType(InvalidOperationException.class)
                .isThrownBy(() -> {
                    accountSevices.executeOperation(bankOperation, account);
                });

        assertThat(exception).isNotNull();
        assertThat(exception).withFailMessage(INVALID_OPERATION.getCode());
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        ThrowableAssertAlternative<IllegalArgumentException> exception = Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    bankOperation = new BankDepositOperation(-10);
                });

        assertThat(exception).isNotNull();
        assertThat(exception).withFailMessage("The Amount Cannot be negative!");
    }

    @Test
    void shouldReturnValidAmountWithdrawed() throws InvalidOperationException {
        bankClient.getAccounts().add(account);
        accountSevices.executeOperation(bankOperation, account);
        accountSevices.executeOperation(new BankDepositOperation(180.75), account);

        bankOperation = new BankWithdrawalOperation(150);
        assertThat(accountSevices.executeOperation(bankOperation, account)).isTrue();
        assertThat(account.getBalance()).isEqualTo(Double.valueOf(230.75));
    }

    @Test
    void shouldReturnInvalidOperationWhenWithrawed() throws InvalidOperationException {

        accountSevices.executeOperation(new BankDepositOperation(180.75), account);
        bankOperation = new BankWithdrawalOperation(300);

        ThrowableAssertAlternative<InvalidOperationException> exception = Assertions.assertThatExceptionOfType(InvalidOperationException.class)
                .isThrownBy(() -> {
                    accountSevices.executeOperation(bankOperation,account);
                });
        assertThat(exception).isNotNull();
        assertThat(exception).withFailMessage(INSUFFICIENT_AMMOUNT.getCode());
    }

    class OtherOperation extends BankOperation{
        @Override
        public double getAmount() {
            return 0;
        }
    }

}
