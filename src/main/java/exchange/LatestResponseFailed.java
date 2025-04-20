package exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatestResponseFailed extends LatestResponse {
    @JsonProperty("error-type")
    private String errorType;
}
