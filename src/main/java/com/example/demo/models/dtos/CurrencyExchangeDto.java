package com.example.demo.models.dtos;

public class CurrencyExchangeDto {
    public Long Id;
    public String ExchangeDate;
    public String ConvertCurrency;
    public Float ExchangeValue;

    public CurrencyExchangeDto() {

    }

    public CurrencyExchangeDto(Long id, String exchangeDate, String convertCurrency, Float exchangeValue) {
        super();
        Id = id;
        ExchangeDate = exchangeDate;
        ConvertCurrency = convertCurrency;
        ExchangeValue = exchangeValue;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getExchangeDate() {
        return ExchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        ExchangeDate = exchangeDate;
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
}
