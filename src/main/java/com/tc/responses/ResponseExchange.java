package com.tc.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResponseExchange {
    private Long id;

    private LocalDateTime timestampRequest;

    private LocalDate dateRequest;

    private Double valueBuy;

    private Double valueSell;

    private LocalDateTime dateExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestampRequest() {
        return timestampRequest;
    }

    public void setTimestampRequest(LocalDateTime timestampRequest) {
        this.timestampRequest = timestampRequest;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Double getValueBuy() {
        return valueBuy;
    }

    public void setValueBuy(Double valueBuy) {
        this.valueBuy = valueBuy;
    }

    public Double getValueSell() {
        return valueSell;
    }

    public void setValueSell(Double valueSell) {
        this.valueSell = valueSell;
    }

    public LocalDateTime getDateExchange() {
        return dateExchange;
    }

    public void setDateExchange(LocalDateTime dateExchange) {
        this.dateExchange = dateExchange;
    }

}
