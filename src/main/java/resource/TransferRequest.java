package resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferRequest {

    @JsonProperty("fromAccountNumber")
    private int fromAccountNumber;

    @JsonProperty("toAccountNumber")
    private int toAccountNumber;

    @JsonProperty("amount")
    private long amount;

    public TransferRequest(int fromAccountNumber, int toAccountNumber, long amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public int fromAccountNumber() {
        return fromAccountNumber;
    }

    public int toAccountNumber() {
        return toAccountNumber;
    }

    public long amount() {
        return amount;
    }
}
