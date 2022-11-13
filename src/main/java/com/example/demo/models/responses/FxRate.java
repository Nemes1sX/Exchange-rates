package com.example.demo.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class FxRate {
    @JsonProperty("Tp")
    public String Tp;
    public String Dt;
    public List<CcyAmt> CcyAmt;
    public String getTp() {
        return Tp;
    }

    public void setTp(String tp) {
        Tp = tp;
    }

    public String getDt() {
        return Dt;
    }

    public void setDt(String dt) {
        Dt = dt;
    }

    public List<com.example.demo.models.responses.CcyAmt> getCcyAmt() {
        return CcyAmt;
    }

    public void setCcyAmt(List<com.example.demo.models.responses.CcyAmt> ccyAmt) {
        CcyAmt = ccyAmt;
    }

    public FxRate()
    {

    }
    public FxRate(String tp, String dt, List<com.example.demo.models.responses.CcyAmt> ccyAmt) {
        Tp = tp;
        Dt = dt;
        CcyAmt = ccyAmt;
    }

}
