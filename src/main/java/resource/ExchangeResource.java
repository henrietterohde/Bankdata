package resource;

import exchange.ExchangeCalculator;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/exchange")
public class ExchangeResource {

    @Inject
    ExchangeCalculator exchangeCalculator;

    @GET
    @Produces("application/json")
    public Response calculateExchange(@QueryParam("amountInDkk") long amountInDkk) {
        float amountInUsd = exchangeCalculator.calculateExchange(amountInDkk);
        return Response.ok(new ExchangeResponse(amountInDkk, amountInUsd)).build();
    }
}
