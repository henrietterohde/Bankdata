package resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import account.AccountService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AccountResourceTest {

    @Inject
    AccountService accountService;

    private final String FIRST_NAME_1 = "John";
    private final String FIRST_NAME_2 = "Jane";
    private final String LAST_NAME = "Smith";

    @Test
    void can_create_account() {
        given()
                .when()
                .contentType("application/json")
                .body("""
                        {
                            "firstName":"John",
                            "lastName":"Smith"
                        }
                        """)
                .post("/account")
                .then()
                .statusCode(201);
    }

    @Test
    void can_get_balance() {
        int accountNumber = accountService.createAccount(FIRST_NAME_1, LAST_NAME);
        given()
                .when()
                .contentType("application/json")
                .queryParam("accountNumber", accountNumber)
                .get("/account")
                .then()
                .statusCode(200)
                .body(is(String.valueOf(0)));
    }

    @Test
    void get_returns_404_if_account_is_not_found() {
        int accountNumber = Integer.MAX_VALUE;

        given()
                .when()
                .contentType("application/json")
                .queryParam("accountNumber", accountNumber)
                .get("/account")
                .then()
                .statusCode(404)
                .body(is("account number: " + accountNumber));
    }

    @Test
    void can_deposit() {
        int accountNumber = accountService.createAccount(FIRST_NAME_2, LAST_NAME);

        given()
                .when()
                .contentType("application/json")
                .body("""
                   {
                        "accountNumber":%s,
                        "amount": "20000"
                   }
                """.formatted(accountNumber))
                .post("/account/deposit")
                .then()
                .statusCode(204);
    }

    @Test
    void can_transfer() {
        int accountNumber1 = accountService.createAccount(FIRST_NAME_1, LAST_NAME);
        int accountNumber2 = accountService.createAccount(FIRST_NAME_2, LAST_NAME);

        given()
                .when()
                .contentType("application/json")
                .body("""
                   {
                      "fromAccountNumber":%s,
                      "toAccountNumber":%s,
                      "amount":120
                   }
                """.formatted(accountNumber1, accountNumber2))
                .post("/account/transfer")
                .then()
                .statusCode(204);
    }
}
