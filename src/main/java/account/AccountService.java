package account;

import account.exception.AccountNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class AccountService {

    @Inject
    EntityManager em;

    @Transactional
    public int createAccount(String firstName, String lastName) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(0);
        accountEntity.setFirstName(firstName);
        accountEntity.setLastName(lastName);

        em.persist(accountEntity);
        return accountEntity.accountNumber();
    }

    @Transactional
    public void transfer(int fromAccountNumber, int toAccountNumber, long amount) {
        // TODO might need some additional locking to avoid concurrency issues
        AccountEntity from = findEntityOrThrow(fromAccountNumber);
        AccountEntity to = findEntityOrThrow(toAccountNumber);

        long fromBalance = from.balance();
        long toBalance = to.balance();

        // TODO: Not clear from requirements if accounts can go negative but can be implemented here
        if (fromBalance - amount >= 0) {
            from.setBalance(fromBalance - amount);
            to.setBalance(toBalance + amount);
        } else {
            // TODO make exception type for negative balance
        }
    }

    @Transactional
    public void deposit(long amount, int accountNumber) {
        AccountEntity account = findEntityOrThrow(accountNumber);

        long balance = account.balance() + amount;
        account.setBalance(balance);
    }

    @Transactional
    public Account getAccount(int accountNumber) {
        AccountEntity accountEntity = findEntityOrThrow(accountNumber);
        return getAccountFrom(accountEntity);
    }

    private AccountEntity findEntityOrThrow(int accountNumber) {
        return findAccountEntity(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    private Optional<AccountEntity> findAccountEntity(int accountNumber) {
        AccountEntity accountEntity = em.find(AccountEntity.class, accountNumber);
        return Optional.ofNullable(accountEntity);
    }

    private Account getAccountFrom(AccountEntity entity) {
        return new Account(
                entity.accountNumber(),
                entity.balance(),
                entity.firstName(),
                entity.lastName()
        );
    }
}
