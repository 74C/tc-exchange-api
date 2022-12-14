package com.tc.resource;

import com.tc.entities.Exchange;
import com.tc.service.ExchangeService;
import com.tc.util.DateParam;
import com.tc.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/exchanges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExchangeResource {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeResource.class);

    @Inject
    private ExchangeService service;

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
        LOGGER.info("findById executed!");
        return Response.ok(response).status(Response.Status.FOUND).build();
    }

    @GET
    @Path("/find_by_date")
    public Response findByDate(@QueryParam("dateRequest") String dateRequest) {
        LOGGER.info("Date: " + dateRequest);
        LocalDate localDate = DateUtil.formatStringToLocalDate(dateRequest);
        LOGGER.info("LocalDate: " + localDate);
        Exchange response = service.findByDate(localDate);
        LOGGER.info("Find By Date  executed!");
        return Response.ok(response).status(Response.Status.FOUND).build();
    }

    @POST
    @Path("/add/")
    @Transactional
    public Response add(Exchange exchange) {
        LOGGER.info("save exchange started!");
        service.save(exchange);
        LOGGER.info("save exchange executed!");
        return Response.ok(exchange).status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/test/")
    @Transactional
    public Response test(LocalDate date) {
        LOGGER.info("save exchange started!" + date);
        return Response.ok(date).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/delete")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Exchange exchangeEntity = service.findById(id);

        if (exchangeEntity == null) {
            throw new WebApplicationException("Exchange id: " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        exchangeEntity.delete();
        LOGGER.info("delete exchange executed!");
        return Response.ok().status(Response.Status.ACCEPTED).build();
    }
}
