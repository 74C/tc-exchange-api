package com.tc.service;

import com.tc.entities.Exchange;
import com.tc.repositories.ExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class ExchangeService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ExchangeRepository repo;

    public Exchange save(Exchange exchange) {
        Exchange response = repo.save(exchange);
        LOGGER.info("Exchange Service saved! ID: " + response.getId());
        return response;
    }

    public Exchange findById(Long id) {
        Exchange response = Exchange.findById(id);
        LOGGER.info("Exchange found! ID: " + response.getId());
        return response;
    }


    public Exchange findByDate(LocalDate dateRequest) {
        Exchange response = repo.findByDateRequest(dateRequest);

        LOGGER.info("Exchange by Date found! ID: " + response);
        return response;
    }

/*    public Optional<Exchange> findByDateOp(Date dateRequest) {
        LOGGER.info("Exchange by Date Op found! ID: " + dateRequest);
        Optional<Exchange> response = repo.findByDateOp(dateRequest);

        return response;
    }*/
}
