package com.tc.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseExchangeBC {

    @JsonProperty("@odata.context")
    private String odataContext;

    @JsonProperty("value")
    private List<ResponseExchangeValue> value;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<ResponseExchangeValue> getValue() {
        return value;
    }

    public void setValue(List<ResponseExchangeValue> value) {
        this.value = value;
    }
}
