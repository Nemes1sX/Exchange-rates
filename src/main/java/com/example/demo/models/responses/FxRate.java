package com.example.demo.models.responses;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class FxRate {
    @XmlElement
    public String Tp;
    public Date Dt;
    public List<CcyAmt> CcyAmt;
}
