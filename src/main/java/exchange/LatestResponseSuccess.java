package exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class LatestResponseSuccess extends LatestResponse {
    @JsonProperty("documentation")
    public String documentation;

    @JsonProperty("terms_of_use")
    public String termsOfUse;

    @JsonProperty("time_last_update_unix")
    public int timeLastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    public String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    public int timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    public String timeNextUpdateUtc;

    @JsonProperty("base_code")
    public String baseCode;

    @JsonProperty("conversion_rates")
    public Map<String, Float> conversionRate;
}
