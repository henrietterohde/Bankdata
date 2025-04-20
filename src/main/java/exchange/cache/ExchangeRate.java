package exchange.cache;

import java.time.Instant;

public record ExchangeRate(float rate, Instant validUntil) {
}
