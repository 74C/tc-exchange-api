package com.tc.services;

import com.tc.entities.Exchange;
import com.tc.responses.ResponseExchange;
import com.tc.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class ExchangeManagerService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeManagerService.class);

    @Inject
    ExchangeService exchangeService;

    @Inject
    ExchangeBCService exchangeBCService;

    public Optional<ResponseExchange> findExchange(String dateRequest) {
        Exchange exchange = null;
        exchange = exchangeService.findByDateRequest(dateRequest);
        if (exchange == null) {
            exchange = exchangeBCService.getExchangeFromBC(dateRequest);
            if (exchange != null) {
                exchange.setDateRequest(DateUtil.formatStrinToLocalDate(dateRequest));
                exchangeService.save(exchange);
            }
        }
        return  setEntitytoResponse(exchange);
    }

    public Optional<Exchange> save(Exchange exchange) {
        return exchangeService.save(exchange);
    }

    private static Optional<ResponseExchange> setEntitytoResponse(Exchange exchange) {
        if (exchange != null) {
            ResponseExchange request = new ResponseExchange();
            request.setId(exchange.getId());
            request.setTimestampRequest(exchange.getTimestampRequest());
            request.setDateRequest(exchange.getDateRequest());
            request.setValueBuy(Double.valueOf(exchange.getValueBuy()));
            request.setValueSell(Double.valueOf(exchange.getValueSell()));
            request.setDateExchange(exchange.getDateExchange());
            return Optional.of(request);
        }
        return Optional.empty();
    }

}
