package com.tc.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ts_request")
    private Timestamp timestampRequest;

    @Column(name = "dt_request")
    private LocalDateTime datetimeRequest;

    @Column(name = "vl_request_buy")
    private Double valueBuy;

    @Column(name = "vl_request_sell")
    private Double valueSell;

    @Column(name = "dt_exchange")
    private LocalDateTime datetimeExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestampRequest() {
        return timestampRequest;
    }

    public void setTimestampRequest(Timestamp timestampRequest) {
        this.timestampRequest = timestampRequest;
    }

    public LocalDateTime getDatetimeRequest() {
        return datetimeRequest;
    }

    public void setDatetimeRequest(LocalDateTime datetimeRequest) {
        this.datetimeRequest = datetimeRequest;
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

    public LocalDateTime getDatetimeExchange() {
        return datetimeExchange;
    }

    public void setDatetimeExchange(LocalDateTime datetimeExchange) {
        this.datetimeExchange = datetimeExchange;
    }
}
