package com.tc.repositories;

import com.tc.entities.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    final String FORMAT_DATE = "MM-dd-yyyy";
    @Query(value = "SELECT e FROM Exchange e WHERE TO_CHAR(e.dateRequest, '"+ FORMAT_DATE +"' ) = :dateRequest")
    Exchange findByDateRequest(String dateRequest);
}
