package com.tc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_exchange")
public class Exchange extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ts_request")
    @CreationTimestamp
    private LocalDateTime timestampRequest;

    @JsonbDateFormat(value = "MM-dd-yyyy")
    @Column(name = "dt_request")
    private LocalDate dateRequest;

    @NotNull
    @Column(name = "vl_request_buy")
    private Double valueBuy;

    @NotNull
    @Column(name = "vl_request_sell")
    private Double valueSell;


    @Column(name = "dt_exchange")
    private LocalDateTime dateExchange;

    public Exchange() {

    }

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
