package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.dtos.ExchangeInfoDto;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICurrencyService{
    List<CurrencyExchangeDto> GetCurrencyExchanges();
    List<CurrencyExchangeDto> GetCurrencyExchangesByCode(String currencyCode);
    List<CurrencyExchangeDto> ImportCurrencies(String date) throws IOException, InterruptedException, ParseException;
    ExchangeInfoDto ExchangeMoney(String currencyCode, String date, String money) throws ParseException;
}
