package exchange.cache;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

@ApplicationScoped
public class ExchangeRateCache {

    private ExchangeRate cachedExchangeRate;
    private final Supplier<Instant> nowSupplier;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    @SuppressWarnings("unused")
    public ExchangeRateCache() {
        this(Instant::now);
    }

    //Package private because this should only be used in tests
    ExchangeRateCache(Supplier<Instant> nowSupplier) {
        this.nowSupplier = nowSupplier;
    }

    public Optional<ExchangeRate> cachedExchangeRate() {
        lock.readLock().lock();
        try {
            if (cachedExchangeRate == null || cachedExchangeRate.validUntil().isBefore(nowSupplier.get()))
                return Optional.empty();
            return Optional.of(cachedExchangeRate);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void updateCachedExchangeRate(ExchangeRate cachedExchangeRate) {
        lock.writeLock().lock();
        try {
            this.cachedExchangeRate = cachedExchangeRate;
        }  finally {
            lock.writeLock().unlock();
        }

    }
}
