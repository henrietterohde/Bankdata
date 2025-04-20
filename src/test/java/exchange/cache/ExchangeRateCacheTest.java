package exchange.cache;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeRateCacheTest {

    private final Instant now = Instant.ofEpochSecond(17263829);
    private final ExchangeRateCache subject = new ExchangeRateCache(() -> now);

    @Test
    public void cache_returns_empty_if_no_rate_is_provided() {
        Optional<ExchangeRate> actual = subject.cachedExchangeRate();

        assertEquals(Optional.empty(), actual);
    }

    @Test
    public void cache_returns_empty_if_rate_is_expired() {
        ExchangeRate cachedRate = new ExchangeRate(0.15f, now.minusSeconds(1));
        subject.updateCachedExchangeRate(cachedRate);

        Optional<ExchangeRate> actual = subject.cachedExchangeRate();

        assertEquals(Optional.empty(), actual);
    }

    @Test
    public void cache_returns_rate_if_valid_rate_exists() {
        ExchangeRate cachedRate = new ExchangeRate(0.15f, now.plusSeconds(1));
        subject.updateCachedExchangeRate(cachedRate);

        Optional<ExchangeRate> actual = subject.cachedExchangeRate();

        assertEquals(Optional.of(cachedRate), actual);
    }

}
