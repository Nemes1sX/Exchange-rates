package com.example.demo.services;

import com.example.demo.models.dtos.CurrencyExchangeDto;
import com.example.demo.models.dtos.ExchangeInfoDto;
import com.example.demo.models.entities.CurrencyExchange;
import com.example.demo.models.responses.FxRates;
import com.example.demo.repositories.CurrencyRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyService implements  ICurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private Environment environment;

    @Override
    public List<CurrencyExchangeDto> GetCurrencyExchangesByCode(String currencyCode) {
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();
        List<CurrencyExchange> currencyExchangeList =  currencyRepository.findTop7ByCurrencyCodeOrderByExchangeDateDesc(currencyCode);
        currencyExchangeDtoList = MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }

    @Override
    public List<CurrencyExchangeDto> ImportCurrencies(String date) throws IOException, InterruptedException, ParseException {
        List<CurrencyExchange> currencyExchangeList = new ArrayList<CurrencyExchange>();
        List<CurrencyExchangeDto> currencyExchangeDtoList;
        String url = environment.getProperty("lb.api.url");
        url = url.replace("0000-00-00", date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(url))
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

        currencyExchangeDtoList = MapCurrencyExchangeDtoList(currencyExchangeList);

        return currencyExchangeDtoList;
    }

    @Override
    public ExchangeInfoDto ExchangeMoney(String money, String currencyCode, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        Date parsedDate = formatter.parse(date);
        Float parsedMoney  = Float.parseFloat(money);
        CurrencyExchange currencyExchange = currencyRepository.findByCurrencyCodeAndExchangeDate(currencyCode, parsedDate);
        if (currencyExchange == null) {
            return null;
        }
        String exchangedMoney = decimalFormat.format(parsedMoney * currencyExchange.ExchangeValue);
        String simplifiedDate = formatter.format(parsedDate);
        ExchangeInfoDto exchangeInfoDto = new ExchangeInfoDto(exchangedMoney, currencyCode, simplifiedDate);

        return exchangeInfoDto;
    }


    private List<CurrencyExchangeDto> MapCurrencyExchangeDtoList(List<CurrencyExchange> currencyExchangeList)
    {
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();

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
