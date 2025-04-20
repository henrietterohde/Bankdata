package account;

import jakarta.persistence.*;

// Package private because it should not be used outside the account package
@Entity
class AccountEntity {

    @Id
    @Column(unique = true, nullable = false)
    @SequenceGenerator(name = "Sequence7Digits", initialValue = 1000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence7Digits")
    private int accountNumber;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    public AccountEntity() {
    }

    public int accountNumber() {
        return accountNumber;
    }

    public long balance() {
        return balance;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
