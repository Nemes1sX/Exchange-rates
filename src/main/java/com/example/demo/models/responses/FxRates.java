package com.example.demo.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"FxRate", "xlmns"})
public class FxRates {
    //@JacksonXmlProperty(localName = "FxRate")
    @JsonProperty("FxRate")
    public ArrayList<com.example.demo.models.responses.FxRate> FxRate;
    @JsonProperty("xlmns")
    public String xmlns;
   /*@JacksonXmlText(value = false)
    public String text;*/

    public FxRates()
    {

    }
    public FxRates(ArrayList<com.example.demo.models.responses.FxRate> fxRate, String xmlns)  {
        FxRate = fxRate;
        this.xmlns = xmlns;
        //this.text = text;
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

   /* public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }*/
}
