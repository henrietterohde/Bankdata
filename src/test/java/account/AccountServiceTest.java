package account;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AccountServiceTest {

    private final String FIRST_NAME_1 = "John";
    private final String FIRST_NAME_2 = "Jane";
    private final String LAST_NAME = "Smith";

    @Inject
    AccountService subject;

    @Test
    public void can_create_account() {
        int accountNumber = subject.createAccount(FIRST_NAME_1, LAST_NAME);

        Account actual = subject.getAccount(accountNumber);

        assertEquals(accountNumber, actual.accountNumber());
        assertEquals(0, actual.balance());
        assertEquals(FIRST_NAME_1, actual.firstName());
        assertEquals(LAST_NAME, actual.lastName());
    }

    @Test
    public void can_deposit() {
        int accountNumber = subject.createAccount(FIRST_NAME_2, LAST_NAME);

        subject.deposit(230, accountNumber);
        Account actual = subject.getAccount(accountNumber);

        assertEquals(230, actual.balance());
    }

    @Test
    public void can_transfer() {
        int accountNumber1 = subject.createAccount(FIRST_NAME_1, LAST_NAME);
        int accountNumber2 = subject.createAccount(FIRST_NAME_2, LAST_NAME);
        subject.deposit(300, accountNumber1);

        subject.transfer(accountNumber1, accountNumber2, 120);

        Account updatedFromAccount = subject.getAccount(accountNumber1);
        assertEquals(180, updatedFromAccount.balance());
        Account updatedToAccount = subject.getAccount(accountNumber2);
        assertEquals(120, updatedToAccount.balance());
    }
}
