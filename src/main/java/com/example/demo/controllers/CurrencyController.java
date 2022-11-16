package com.example.demo.controllers;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.dtos.ExchangeInfoDto;
import com.example.demo.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/importData")
    public ResponseEntity<List<CurrencyExchangeDto>> ImportArchiveCurrencyData(String date)
    {
        try {
            List<CurrencyExchangeDto> currencyExcahngeList = currencyService.ImportCurrencies(date);
            if (currencyExcahngeList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(currencyExcahngeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCurrencyArchive")
    public ResponseEntity<List<CurrencyExchangeDto>> GetCurrencyData(String currencyCode)
    {
        try {
            List<CurrencyExchangeDto> currencyExcahngeList = currencyService.GetCurrencyExchangesByCode(currencyCode.toUpperCase());
            if (currencyExcahngeList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(currencyExcahngeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exchangeCurrencyValue")
    public ResponseEntity<ExchangeInfoDto> ExchangeCurrencyValue(String money, String currencyCode, @RequestParam(required = false) String date)
    {
        try {
            ExchangeInfoDto currencyExchange = currencyService.ExchangeMoney(money, currencyCode, date);
            if (currencyExchange == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(currencyExchange, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
