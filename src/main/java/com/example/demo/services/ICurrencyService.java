package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import java.util.List;

public interface ICurrencyService {
    List<CurrencyExchangeDto> PrintCurrencyExchanges();
}
