package com.example.demo.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currencyexchange")
public class CurrencyExchange {
    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "Id=" + Id +
                ", CurrencyDate=" + ExchangeDate +
                ", ConvertCurrency='" + ConvertCurrency + '\'' +
                ", ExchangeValue=" + ExchangeValue +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getConvertCurrency() {
        return ConvertCurrency;
    }

    public void setConvertCurrency(String convertCurrency) {
        ConvertCurrency = convertCurrency;
    }

    public Float getExchangeValue() {
        return ExchangeValue;
    }

    public void setExchangeValue(Float exchangeValue) {
        ExchangeValue = exchangeValue;
    }

    public Date getExchangeDate() {
        return ExchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        ExchangeDate = exchangeDate;
    }

    public CurrencyExchange() {

    }

    public CurrencyExchange(Date exchangeDate, String convertCurrency, Float exchangeValue) {
        this.ExchangeDate = exchangeDate;
        this.ConvertCurrency = convertCurrency;
        this.ExchangeValue = exchangeValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long Id;
    @Column(name = "exchange_date")
    public Date ExchangeDate;
    @Column(name = "convert_currency")
    public String ConvertCurrency;
    @Column(name = "ExchangeValue")
    public Float ExchangeValue;
}
