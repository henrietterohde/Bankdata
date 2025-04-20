package resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepositRequest {

    @JsonProperty("accountNumber")
    private int accountNumber;

    @JsonProperty("amount")
    private long amount;

    DepositRequest(int accountNumber, long amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public int accountNumber() {
        return accountNumber;
    }

    public long amount() {
        return amount;
    }
}
