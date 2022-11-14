package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.dtos.ExchangeInfoDto;
import com.example.demo.models.entities.CurrencyExchange;
import com.example.demo.models.responses.FxRates;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyService implements  ICurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Override
    public List<CurrencyExchangeDto> GetCurrencyExchanges() {
       List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
       List<CurrencyExchange> currencyExchangeList =  currencyRepository.findAll();
       MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }


    @Override
    public List<CurrencyExchangeDto> GetCurrencyExchangesByCode(String currencyCode) {
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
        List<CurrencyExchange> currencyExchangeList =  currencyRepository.findByCurrencyCode(currencyCode);
        MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }

    @Override
    public List<CurrencyExchangeDto> ImportCurrencies(String date) throws IOException, InterruptedException, ParseException {
        List<CurrencyExchange> currencyExchangeList = new ArrayList<CurrencyExchange>();
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(MessageFormat.format("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=eu&dt={0}", date)))
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

    @Override
    public ExchangeInfoDto ExchangeMoney(String money, String currencyCode, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        var parsedDate = formatter.parse(date);
        var parsedMoney  = Float.parseFloat(money);
        var currencyExchange = currencyRepository.findByCurrencyCodeAndExchangeDate(currencyCode, parsedDate);
        if (currencyExchange == null) {
            return null;
        }
        var exchangedMoney = decimalFormat.format(parsedMoney * currencyExchange.ExchangeValue);
        var ExchangeInfoDto = new ExchangeInfoDto(exchangedMoney, currencyCode, parsedDate);

        return ExchangeInfoDto;
    }


    private List<CurrencyExchangeDto> MapCurrencyExchangeDtoList(List<CurrencyExchange> currencyExchangeList)
    {
        var currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();

        for (var currencyExchange : currencyExchangeList)
        {
            currencyExchangeDtoList.add(new CurrencyExchangeDto(currencyExchange.Id,
                    currencyExchange.exchangeDate,
                    currencyExchange.currencyCode,
                    currencyExchange.ExchangeValue));
        }

        return currencyExchangeDtoList;
    }
}
