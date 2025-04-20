package resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeResponse {
    @JsonProperty("DKK")
    public float amountInDkk;

    @JsonProperty("USD")
    public float amountInUSD;

    public ExchangeResponse(float amountInDkk, float amountInUSD) {
        this.amountInDkk = amountInDkk;
        this.amountInUSD = amountInUSD;
    }

}
