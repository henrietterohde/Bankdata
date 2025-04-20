package account.exception;

// RuntimeException is chosen so that it does not have to be handled until the Exception mapper catches it in the resource
public class AccountNotFoundException extends RuntimeException {

    private final int accountNumber;

    public AccountNotFoundException(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int accountNumber() {
        return accountNumber;
    }
}
