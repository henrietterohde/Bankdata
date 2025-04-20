package exchange;

import exchange.cache.ExchangeRate;
import exchange.cache.ExchangeRateCache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Instant;
import java.util.Optional;

@ApplicationScoped
public class ExchangeService {

    @Inject
    ExchangeRateCache cache;

    @RestClient
    ExchangeClient exchangeClient;

    public float getExchangeRateForDKK() {
        Optional<ExchangeRate> cachedExchangeRate = cache.cachedExchangeRate();
        if (cachedExchangeRate.isPresent()) {
            return cachedExchangeRate.get().rate();
        }

        LatestResponse response = exchangeClient.getExchangeRate("DKK");

        if (response instanceof LatestResponseSuccess) {
            ExchangeRate exchangeRate = extractUsdExchangeRate((LatestResponseSuccess) response);
            cache.updateCachedExchangeRate(exchangeRate);
            return exchangeRate.rate();
        } else {
            //TODO make ExchangeException instead
            throw new RuntimeException("Exchange rate not successful");
        }
    }

    private ExchangeRate extractUsdExchangeRate(LatestResponseSuccess response) {
        float rate = response.conversionRate.get("USD");
        Instant validUntil = Instant.ofEpochSecond(response.timeNextUpdateUnix);
        return new ExchangeRate(rate, validUntil);
    }
}
