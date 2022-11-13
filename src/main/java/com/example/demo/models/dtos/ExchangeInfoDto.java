package com.example.demo.models.dtos;

import java.util.Date;

public class ExchangeInfoDto {
    public String ExchangedValue;
    public String CurrencyCode;
    public Date ExchangeDate;

    public  ExchangeInfoDto() {

    }

    public ExchangeInfoDto(String exchangedValue, String currencyCode, Date exchangeDate) {
        ExchangedValue = exchangedValue;
        CurrencyCode = currencyCode;
        ExchangeDate = exchangeDate;
    }

    public String getExchangedValue() {
        return ExchangedValue;
    }

    public void setExchangedValue(String exchangedValue) {
        ExchangedValue = exchangedValue;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public Date getExchangeDate() {
        return ExchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        ExchangeDate = exchangeDate;
    }
}
