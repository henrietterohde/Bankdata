package exchange;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// TODO remove hardcoded API key
@Path("/v6/320bc8ce0649b9e5293cad7a")
@RegisterRestClient(baseUri = "https://v6.exchangerate-api.com")
public interface ExchangeClient {

    @GET
    @Path("/latest/{currency}")
    LatestResponse getExchangeRate(@PathParam("currency") String currency);

}
