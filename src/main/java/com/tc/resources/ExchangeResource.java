package com.tc.resources;

import com.tc.entities.Exchange;
import com.tc.responses.ResponseExchange;
import com.tc.services.ExchangeManagerService;
import com.tc.utils.DateUtil;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/exchanges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExchangeResource {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeResource.class);

    @Inject
    private ExchangeManagerService serviceMangager;

    @GET
    @Path("/{dataCotacao}/")
    public Response getExchangeBC(@PathParam("dataCotacao") String dataCotacao) {
        LOGGER.info("Date: " + dataCotacao);
        if (DateUtil.validDate(dataCotacao)) {
            Optional<ResponseExchange> response = serviceMangager.findExchange(dataCotacao);
            if (!response.isPresent()) {
                return Response.ok(response).status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(response).status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
