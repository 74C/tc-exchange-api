package com.tc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_exchange")
public class Exchange extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ts_request")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampRequest;

    @Column(name = "dt_request")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.", shape = JsonFormat.Shape.STRING)
    private LocalDate dateRequest;

    @Column(name = "vl_request_buy")
    private Double valueBuy;

    @Column(name = "vl_request_sell")
    private Double valueSell;

    //@JsonbDateFormat(value = "MM/dd/YYYY")
    @Column(name = "dt_exchange")
    private LocalDate dateExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestampRequest() {
        return timestampRequest;
    }

    public void setTimestampRequest(Date timestampRequest) {
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

    public LocalDate getDateExchange() {
        return dateExchange;
    }

    public void setDateExchange(LocalDate dateExchange) {
        this.dateExchange = dateExchange;
    }
}
