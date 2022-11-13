package com.example.demo.models.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CcyAmt {
    @JacksonXmlProperty(localName = "Ccy")
    public String Ccy;
    @JacksonXmlProperty(localName = "Amt")
    public String Amt;

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public CcyAmt()
    {

    }
    public CcyAmt(String ccy, String amt) {
        Ccy = ccy;
        Amt = amt;
    }
}
