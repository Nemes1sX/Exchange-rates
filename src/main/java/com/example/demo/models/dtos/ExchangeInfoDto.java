package com.example.demo.models.dtos;

public class ExchangeInfoDto {
    public String ExchangedValue;
    public String RateOfExchange;
    public String CurrencyCode;
    public String ExchangeDate;

    public  ExchangeInfoDto() {

    }

    public ExchangeInfoDto(String currencyCode, String rateOfExchange, String exchangedValue, String exchangeDate) {
        ExchangedValue = exchangedValue;
        CurrencyCode = currencyCode;
        RateOfExchange = rateOfExchange;
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

    public String getExchangeDate() {
        return ExchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        ExchangeDate = exchangeDate;
    }

    public String getRateOfExchange() {
        return RateOfExchange;
    }

    public void setRateOfExchange(String rateOfExchange) {
        RateOfExchange = rateOfExchange;
    }
}
