package com.tc.services;

import com.tc.entities.Exchange;
import com.tc.exceptions.BusinessException;
import com.tc.repositories.ExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import java.util.Optional;

@Service
@ApplicationScoped
public class ExchangeService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ExchangeRepository repo;

    public Exchange findByDateRequest(String dateRequest) {
        Exchange exchange;
        try {
             exchange = repo.findByDateRequest(dateRequest);
            LOGGER.info("Get Exchange by date requestxcx executed");
        } catch (PersistenceException e) {
            LOGGER.error("Error at get Exchange by date!" + e.getMessage());
            throw new BusinessException("Error at get Exchange by date!" + e.getMessage());
        }
        return exchange;
    }

    public Optional<Exchange> save(Exchange exchange) {
        try {

            exchange = repo.save(exchange);
            LOGGER.info("Exchange saved!");
        } catch (PersistenceException e) {
            LOGGER.error("Error at get Exchange by date!" + e.getMessage());
            throw new BusinessException("Error at get Exchange by date!" + e.getMessage());
        }
        return Optional.of(exchange);
    }

}
