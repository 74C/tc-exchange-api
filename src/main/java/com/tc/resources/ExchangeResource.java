package com.tc.resources;

import com.tc.entities.Exchange;
import com.tc.responses.ResponseExchange;
import com.tc.services.ExchangeService;
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

@Path("/exchanges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExchangeResource {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeResource.class);

    @Inject
    private ExchangeService service;

    /*
    @GET
    @Path("/find_all")
    public Response findAll() {
        List<Exchange> list = Exchange.listAll();
        LOGGER.info("findAll executed!");
        return Response.ok(list).status(Response.Status.FOUND).build();
    }

    @GET
    @Path("/find_by_id/{id}")
    public Response findById(@PathParam("id") Long id) {
        Exchange response = service.findById(id);
        LOGGER.info("findById executed!" + response);
        if (response == null) {
            LOGGER.info("findById: NOT FOUND EXCHANGE");
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(response).status(Response.Status.FOUND).build();
    }

    @GET
    @Path("/find_by_date")
    @APIResponse(
            responseCode = "200",
            description = "Get Exchange by ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Exchange.class)
            )
    )
    public Response findByDate(@QueryParam("dateRequest") @JsonbDateFormat("MM-dd-yyyy") LocalDate dateRequest) {
        LOGGER.info("Date: " + dateRequest);
        Exchange response = service.findByDate(dateRequest);
        LOGGER.info("Find By Date  executed!");
        return Response.ok(response).status(Response.Status.FOUND).build();
    }*/


    @POST
    @Transactional
    @APIResponse(
            responseCode = "200",
            description = "Save Exchange",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Exchange.class)
            )
    )
    public Response add(ResponseExchange dto) {
        LOGGER.info("save exchange started!");
        service.save(dto);
        LOGGER.info("save exchange executed!");
        return Response.ok(dto).status(Response.Status.CREATED).build();
    }


    @GET
    @Path("/{dataCotacao}/")
    public Response getExchangeBC(@PathParam("dataCotacao") String dataCotacao) {
        LOGGER.info("Date: " + dataCotacao);
        if (DateUtil.validDate(dataCotacao)) {
            Exchange response = service.findExchangeBC(dataCotacao);
            if (response == null) {
                return Response.noContent().status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(response).status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}