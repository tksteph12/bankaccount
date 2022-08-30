package com.sg.kata.services;

import com.sg.kata.error.InvalidOperationException;
import com.sg.kata.model.account.Account;
import com.sg.kata.model.account.SavingsAccount;
import com.sg.kata.model.operation.BankDepositOperation;
import com.sg.kata.model.operation.BankOperation;
import com.sg.kata.model.operation.IBankOperation;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.sg.kata.error.ErrorEnum.INVALID_OPERATION;
import static org.assertj.core.api.Assertions.assertThat;


public class TestAccountServicesImpl {

    private IAccountSevices accountSevices = AccountServicesImpl.getInstance();
    private Account account;

    private IBankOperation bankOperation;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount("FR44012484787J55");
        bankOperation = new BankDepositOperation(200);
    }

    @Test
    void shouldReturnValidAmountDeposited() throws InvalidOperationException {
        assertThat(account.getBalance()).isEqualTo(0);
        assertThat(accountSevices.executeOperation(bankOperation, account)).isTrue();

        assertThat(account.getBalance()).isEqualTo(Double.valueOf(200));
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


    class OtherOperation extends BankOperation{
        @Override
        public double getAmount() {
            return 0;
        }
    }

}
