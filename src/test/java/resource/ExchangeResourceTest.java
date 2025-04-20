package resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * I would add some negative tests that test the Exception mapping for exchangeExceptions here as well.
 */
@QuarkusTest
public class ExchangeResourceTest {

    @Test
    void can_exchange() {
        given()
                .when()
                .contentType("application/json")
                .queryParam("amountInDkk", 100)
                .get("/exchange")
                .then()
                .statusCode(200)
                .body(is("{\"DKK\":100.0,\"USD\":15.26}"));
    }
}
