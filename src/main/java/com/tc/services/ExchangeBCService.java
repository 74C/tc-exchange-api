package com.tc.services;

import com.tc.entities.Exchange;
import com.tc.responses.ResponseExchangeBC;
import com.tc.responses.ResponseExchangeValue;
import com.tc.rest.client.ExchangeBCClientService;
import com.tc.utils.DateUtil;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class ExchangeBCService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeBCService.class);

    public static final String FORMAT = "json";
    @Inject
    @RestClient
    ExchangeBCClientService serviceBC;

    public Exchange getExchangeFromBC(String dateRequest){
        ResponseExchangeBC responseBC =  serviceBC.getExchangeBC(putQuotation(dateRequest), FORMAT);
        LOGGER.info("Get service client data from BC executed");
        Exchange exchange =  null;

        if (!responseBC.getValue().isEmpty()) {
            ResponseExchangeValue value = responseBC.getValue().get(0);
            exchange = new Exchange();
            exchange = setValueBCToExchange(dateRequest, value);
        }
        return exchange;
    }

    private Exchange setValueBCToExchange(String dateRequest, ResponseExchangeValue value) {
        Exchange exchange = new Exchange();

        exchange.setDateExchange(DateUtil.formatStrinToLocalDateTime(value.getDataHoraCotacao()));
        exchange.setValueBuy(Double.valueOf(value.getCotacaoCompra()));
        exchange.setValueSell(Double.valueOf(value.getCotacaoVenda()));
        exchange.setDateRequest(DateUtil.formatStrinToLocalDate(dateRequest));

        return exchange;
    }

    private String putQuotation(String value) {
        return "'"+ value +"'";
    }
}
