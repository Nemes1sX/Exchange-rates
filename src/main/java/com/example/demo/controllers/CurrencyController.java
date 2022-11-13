package com.example.demo.controllers;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService)
    {
        this.currencyService = currencyService;
    }

    @GetMapping("/importDate")
    public ResponseEntity<List<CurrencyExchangeDto>> ImportArchiveCurrencyData(String date)
    {
        try {
            var currencyExcahngeList = currencyService.ImportCurrencies(date);
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
            var currencyExcahngeList = currencyService.GetCurrencyExchangesByCode(currencyCode);
            if (currencyExcahngeList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(currencyExcahngeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
