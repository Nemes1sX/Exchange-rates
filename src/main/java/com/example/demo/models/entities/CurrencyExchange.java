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
                ", CurrencyDate=" + CurrencyDate +
                ", ConvertCurrency='" + ConvertCurrency + '\'' +
                ", ExchangeValue=" + ExchangeValue +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getCurrencyDate() {
        return CurrencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        CurrencyDate = currencyDate;
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

    public CurrencyExchange() {

    }

    public CurrencyExchange(Date currencyDate, String convertCurrency, Float exchangeValue) {
        this.CurrencyDate = currencyDate;
        this.ConvertCurrency = convertCurrency;
        this.ExchangeValue = exchangeValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;
    @Column(name = "currency_date")
    public Date CurrencyDate;
    @Column(name = "convert_currency")
    public String ConvertCurrency;
    @Column(name = "ExchangeValue")
    public Float ExchangeValue;
}
