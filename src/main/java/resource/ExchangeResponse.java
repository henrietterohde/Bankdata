package resource;

import com.fasterxml.jackson.annotation.JsonProperty;

class ExchangeResponse {
    @JsonProperty("DKK")
    public float amountInDkk;

    @JsonProperty("USD")
    public float amountInUSD;

    public ExchangeResponse(float amountInDkk, float amountInUSD) {
        this.amountInDkk = amountInDkk;
        this.amountInUSD = amountInUSD;
    }

}
