package com.example.demo.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class FxRate {
    @JsonProperty("Tp")
    public String Tp;
    public String Dt;
    public ArrayList<CcyAmt> CcyAmt;
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

    public ArrayList<com.example.demo.models.responses.CcyAmt> getCcyAmt() {
        return CcyAmt;
    }

    public void setCcyAmt(ArrayList<com.example.demo.models.responses.CcyAmt> ccyAmt) {
        CcyAmt = ccyAmt;
    }

    public FxRate()
    {

    }
    public FxRate(String tp, String dt, ArrayList<com.example.demo.models.responses.CcyAmt> ccyAmt) {
        Tp = tp;
        Dt = dt;
        CcyAmt = ccyAmt;
    }

}
