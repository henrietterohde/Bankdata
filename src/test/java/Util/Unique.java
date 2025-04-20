package Util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;

// This is a workaround because Quarkus tests does not create new instance of the database between each test.
// No longer needed because I moved the accountNumber creation to the Database.
public class Unique {

    private final static IntSupplier intGenerator = new AtomicInteger(1)::incrementAndGet;

    public static int accountNumber() {
        return intGenerator.getAsInt();
    }
}
