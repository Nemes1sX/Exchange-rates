package com.example.demo.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {
    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "Id=" + Id +
                ", CurrencyDate=" + exchangeDate +
                ", ConvertCurrency='" + currencyCode + '\'' +
                ", ExchangeValue=" + ExchangeValue +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Float getExchangeValue() {
        return ExchangeValue;
    }

    public void setExchangeValue(Float exchangeValue) {
        ExchangeValue = exchangeValue;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date _exchangeDate) {
        exchangeDate = _exchangeDate;
    }

    public CurrencyExchange() {

    }

    public CurrencyExchange(Date _exchangeDate, String _currencyCode, Float exchangeValue) {
        this.exchangeDate = _exchangeDate;
        this.currencyCode = _currencyCode;
        this.ExchangeValue = exchangeValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String _currencyCode) {
        currencyCode = _currencyCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long Id;
    @Column(name = "exchange_date")
    public Date exchangeDate;
    @Column(name = "currency_code")
    public String currencyCode;
    @Column(name = "exchange_value")
    public Float ExchangeValue;
}
