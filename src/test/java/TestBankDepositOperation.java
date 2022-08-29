import com.sg.kata.operation.BankDepositOperation;
import com.sg.kata.operation.BankOperation;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TestBankDepositOperation {

    private BankOperation bankOperation;

    @BeforeEach
    void setUp(){
        bankOperation = new BankDepositOperation(200);
    }

    @Test
    void shouldReturnValidAmountDeposited(){
        assertThat(bankOperation.getAmount()).isEqualTo(Double.valueOf(200));
    }

    @Test
    void shouldGetDepositedDate(){
        assertThat(bankOperation.getDate()).isNotNull();
    }

    @Test
    void shouldThrowIllegalArgumentException(){
        ThrowableAssertAlternative<IllegalArgumentException> exception = Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    bankOperation = new BankDepositOperation(-10);
                });

        assertThat(exception).isNotNull();
        assertThat(exception).withFailMessage("The Amount Cannot be negative!");
    }

}
