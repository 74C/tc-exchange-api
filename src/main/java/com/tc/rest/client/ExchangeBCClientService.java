package com.tc.rest.client;

import com.tc.responses.ResponseExchangeBC;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/odata")
@RegisterRestClient(configKey="bcb-api")
public interface ExchangeBCClientService {

    @GET
    @Path("CotacaoDolarDia(dataCotacao=@dataCotacao)")
    ResponseExchangeBC getExchangeBC(@QueryParam("@dataCotacao") String dataCotacao, @QueryParam("format") String formato);


}
