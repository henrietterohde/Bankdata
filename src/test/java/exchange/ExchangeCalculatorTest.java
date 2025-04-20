package exchange;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
public class ExchangeCalculatorTest {

    private final ExchangeService exchangeServiceMock = Mockito.mock(ExchangeService.class);

    @Inject
    ExchangeCalculator subject;

    @BeforeEach
    public void setup() {
        QuarkusMock.installMockForType(exchangeServiceMock, ExchangeService.class);
    }

    @Test
    public void can_calculate_exchange() {
        float exchangeRate = 0.15f;
        long amountInDkk = 100;
        when(exchangeServiceMock.getExchangeRateForDKK()).thenReturn(exchangeRate);

        float exchange = subject.calculateExchange(amountInDkk);

        assertEquals(exchangeRate*amountInDkk, exchange);
    }

    //TODO add more tests like - negative amount - negative exchange rate - exchange rate > 1
}
