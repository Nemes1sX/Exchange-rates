package com.example.demo.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;

@JsonPropertyOrder({"FxRate", "xlmns"})
public class FxRates {
    @JacksonXmlElementWrapper(useWrapping = false)
    public ArrayList<FxRate> FxRate;
    @JsonProperty("xlmns")
    public String xmlns;

    public FxRates()
    {

    }
    public FxRates(ArrayList<FxRate> fxRate, String xmlns)  {
        FxRate = fxRate;
        xmlns = xmlns;
    }

    public ArrayList<com.example.demo.models.responses.FxRate> getFxRate() {
        return FxRate;
    }

    public void setFxRate(ArrayList<com.example.demo.models.responses.FxRate> fxRate) {
        FxRate = fxRate;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}
