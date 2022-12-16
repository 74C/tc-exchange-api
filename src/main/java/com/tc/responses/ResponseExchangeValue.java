package com.tc.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class ResponseExchangeValue {

    @JsonProperty("cotacaoCompra")
    private String cotacaoCompra;

    @JsonProperty("cotacaoVenda")
    private String cotacaoVenda;

    @JsonProperty("dataHoraCotacao")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private String dataHoraCotacao;

    public String getCotacaoCompra() {
        return cotacaoCompra;
    }

    public void setCotacaoCompra(String cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }

    public String getCotacaoVenda() {
        return cotacaoVenda;
    }

    public void setCotacaoVenda(String cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }

    public String getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public void setDataHoraCotacao(String dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }
}
