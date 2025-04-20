package exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * See https://www.exchangerate-api.com/docs/standard-requests for definition
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "result")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LatestResponseSuccess.class, name = "success"),
        @JsonSubTypes.Type(value = LatestResponseFailed.class, name = "error")}
)
public class LatestResponse {
    @JsonProperty("result")
    public String result;
}
