package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICurrencyService{
    List<CurrencyExchangeDto> PrintCurrencyExchanges();
}
