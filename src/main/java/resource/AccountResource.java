package resource;

import account.Account;
import account.exception.AccountNotFoundException;
import account.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * Input validation:
 * Validation should be handled in the services, to keep the resource clean.
 * Simple input validation could be handled in simple data objects.
 * In this example I would create an AccountNumber class, that contains the accountNumber value, and which can validate
 * that the account number is legal - positive and number of digits.
 * Likewise, some input validation should be added to the Account class.
 * Exception thrown should be handled by the ExceptionMapper - like this all exceptions are handled in one place.
 */
@Path("/account")
public class AccountResource {

    @Inject
    AccountService accountService;

    // This should be extended with multiple Exception types, and mapped in an ExceptionMapper instead.
    @SuppressWarnings("unused")
    @ServerExceptionMapper
    public RestResponse<String> mapAccountException(AccountNotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, "account number: " + exception.accountNumber());
    }

    @GET
    @Produces("application/json")
    public Response getBalance(@QueryParam("accountNumber") int accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        long balance = account.balance();
        return Response.ok(new BalanceResponse(balance)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createAccount(CreateAccountRequest account) {
        int accountNumber = accountService.createAccount(account.firstName(), account.lastName());

        UriBuilder path = UriBuilder.fromResource(AccountResource.class).path(String.valueOf(accountNumber));
        return Response.created(path.build()).build();
    }

    @POST
    @Path("/deposit")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deposit(DepositRequest deposit) {
        accountService.deposit(deposit.amount(), deposit.accountNumber());
        return Response.noContent().build();
    }

    @POST
    @Path("/transfer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response transfer(TransferRequest transfer) {
        accountService.transfer(transfer.fromAccountNumber(), transfer.toAccountNumber(), transfer.amount());
        return Response.noContent().build();
    }
}
