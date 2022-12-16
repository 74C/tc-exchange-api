package com.tc.services;

import com.tc.entities.Exchange;
import com.tc.repositories.ExchangeRepository;
import com.tc.responses.ResponseExchange;
import com.tc.responses.ResponseExchangeBC;
import com.tc.responses.ResponseExchangeValue;
import com.tc.rest.client.ExchangeBCService;
import com.tc.utils.DateUtil;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ApplicationScoped
public class ExchangeService {

    public static final String FORMAT = "json";
    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ExchangeRepository repo;

    @Inject
    @RestClient
    ExchangeBCService serviceBC;

    public Exchange save(ResponseExchange request) {
        Exchange exchangeSave = new Exchange();
        setEntityToSave(exchangeSave, request);

        repo.save(exchangeSave);
        LOGGER.info("Exchange Service saved! resquest: " + request);

        return exchangeSave;
    }

    public Exchange findById(Long id) {
        Exchange response = Exchange.findById(id);
        if (response == null) {
            LOGGER.info("Exchange not found! ID: " + id);
            return response;
        }
        LOGGER.info("Exchange found! ID: " + response.getId());
        return response;
    }


    public Exchange findByDate(LocalDate dateRequest) {
        LOGGER.info("SERVICE: Exchange by Date findByDate! Date: " + dateRequest);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateFormatted = dateRequest.format(formatter);
        LOGGER.info("SERVICE: Exchange by Date findByDate! dateFormatted: " + dateFormatted);
        Exchange response = repo.findByDateRequest(dateFormatted);

        LOGGER.info("Exchange by Date found! Response: " + response);
        return response;
    }

    public Exchange findExchangeBC(String dateRequest) {
        Exchange exchange;

        exchange = repo.findByDateRequest(dateRequest);

        if (exchange == null) {
            ResponseExchangeBC responseBC =  serviceBC.getExchangeBC(putQuotation(dateRequest), FORMAT);
            ResponseExchangeValue value = responseBC.getValue().get(0);

            if (!responseBC.getValue().isEmpty()) {
                exchange = new Exchange();

                exchange.setDateExchange(DateUtil.formatStrinToLocalDateTime(value.getDataHoraCotacao()));
                exchange.setValueBuy(Double.valueOf(value.getCotacaoCompra()));
                exchange.setValueSell(Double.valueOf(value.getCotacaoVenda()));
                exchange.setDateRequest(DateUtil.formatStrinToLocalDate(dateRequest));

                repo.save(exchange);
                LOGGER.info("Exchange saved!");
            }
        }
        return exchange;
    }

    private static void setEntityToSave(Exchange exchange, ResponseExchange response) {
        exchange.setDateExchange(response.getDateExchange());
        exchange.setValueBuy(response.getValueBuy());
        exchange.setValueSell(response.getValueSell());
        exchange.setDateRequest(response.getDateRequest());
    }

    private String putQuotation(String value) {
        return "'"+ value +"'";
    }

    private static void responseToEntity(ResponseExchange request, Exchange exchange) {
        request.setValueBuy(Double.valueOf(exchange.getValueBuy()));
        request.setValueSell(Double.valueOf(exchange.getValueSell()));
        request.setDateExchange(exchange.getDateExchange());
    }
}
