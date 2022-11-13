package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.entities.CurrencyExchange;
import com.example.demo.models.responses.FxRates;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyService implements  ICurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Override
    public List<CurrencyExchangeDto> PrintCurrencyExchanges() {
       List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
       List<CurrencyExchange> currencyExchangeList =  currencyRepository.findAll();
       MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }

    @Override
    public List<CurrencyExchangeDto> ImportCurrencies(String date) throws IOException, InterruptedException, ParseException {
        List<CurrencyExchange> currencyExchangeList = new ArrayList<CurrencyExchange>();
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=eu&dt=2017-12-25"))
                .header("Content-Type", "text/xml")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        XmlMapper xmlMapper = new XmlMapper();
        FxRates value = xmlMapper.readValue(response.body(), FxRates.class);

        for (var currencyExchange : value.getFxRate())
        {
            currencyExchangeList.add(new CurrencyExchange(formatter.parse(currencyExchange.Dt),
                    currencyExchange.CcyAmt.get(1).Ccy, Float.parseFloat(currencyExchange.CcyAmt.get(1).Amt)));
        }

        currencyRepository.saveAll(currencyExchangeList);

       MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }

    private List<CurrencyExchangeDto> MapCurrencyExchangeDtoList(List<CurrencyExchange> currencyExchangeList)
    {
        var currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();

        for (var currencyExchange : currencyExchangeList)
        {
            currencyExchangeDtoList.add(new CurrencyExchangeDto(currencyExchange.Id,
                    currencyExchange.ExchangeDate,
                    currencyExchange.ConvertCurrency,
                    currencyExchange.ExchangeValue));
        }

        return currencyExchangeDtoList;
    }
}
