package resource;

import com.fasterxml.jackson.annotation.JsonProperty;

class BalanceResponse {
    @JsonProperty("balance")
    public long balance;

    public BalanceResponse(long balance) {
        this.balance = balance;
    }
}
