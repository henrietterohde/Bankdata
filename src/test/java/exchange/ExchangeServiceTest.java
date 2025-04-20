package exchange;

import exchange.cache.ExchangeRate;
import exchange.cache.ExchangeRateCache;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static exchange.ExchangeClientWiremock.DKK_EXCHANGE_RATE;
import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
@QuarkusTestResource(ExchangeClientWiremock.class)
public class ExchangeServiceTest {

    private final ExchangeRateCache exchangeRateCacheMock = Mockito.mock(ExchangeRateCache.class);

    @InjectSpy
    @RestClient
    ExchangeClient exchangeClient;

    @Inject
    ExchangeService subject;

    @BeforeEach
    public void setup() {
        QuarkusMock.installMockForType(exchangeRateCacheMock, ExchangeRateCache.class);
    }

    @Test
    public void can_retrieve_exchange_rate_from_client() {
        Mockito.when(exchangeRateCacheMock.cachedExchangeRate()).thenReturn(Optional.empty());

        float exchangeRate = subject.getExchangeRateForDKK();

        Mockito.verify(exchangeClient, Mockito.times(1)).getExchangeRate(anyString());
        assertEquals(DKK_EXCHANGE_RATE, exchangeRate);
    }

    @Test
    public void can_retrieve_exchange_rate_from_cache() {
        float rate = 0.167f;
        Mockito.when(exchangeRateCacheMock.cachedExchangeRate()).thenReturn(Optional.of(new ExchangeRate(rate, Instant.ofEpochSecond(134532))));

        float exchangeRate = subject.getExchangeRateForDKK();

        Mockito.verify(exchangeClient, Mockito.times(0)).getExchangeRate(anyString());
        assertEquals(rate, exchangeRate);
    }

    //TODO add failure cases. Such as what happens if rate is not in response, client throws exceptions, API returns failed, ...
}
