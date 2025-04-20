package exchange;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExchangeCalculator {

    @Inject
    ExchangeService exchangeService;

    public float calculateExchange(long amountInDkk) {
        if (amountInDkk <= 0)
            throw new RuntimeException("TODO add exception type for this");

        float exchangeRate = exchangeService.getExchangeRateForDKK();
        // TODO validate exchangeRate what if negative??
        return amountInDkk * exchangeRate;
    }
}
