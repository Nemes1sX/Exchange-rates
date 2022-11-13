package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ICurrencyService{
    List<CurrencyExchangeDto> PrintCurrencyExchanges();
    List<CurrencyExchangeDto> ImportCurrencies(String date) throws IOException, InterruptedException, ParseException;
}
