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
import java.util.List;

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
        SimpleDateFormat formatter = GetDateFormat();

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
    public ExchangeInfoDto ExchangeMoney(String money, String currencyCode) throws ParseException {
        SimpleDateFormat formatter = GetDateFormat();
        DecimalFormat decimalFormatExchangedValue = new DecimalFormat("###.##");
        DecimalFormat decimalFormatRateOfExchange = new DecimalFormat("###.####");
        Float parsedMoney  = Float.parseFloat(money);
        CurrencyExchange currencyExchange = currencyRepository.findTop1ByCurrencyCodeOrderByExchangeDateDesc(currencyCode);
        if (currencyExchange == null) {
            return null;
        }
        String exchangedMoney = decimalFormatExchangedValue.format(parsedMoney * currencyExchange.RateOfExchange);
        String rateOfExchange = decimalFormatRateOfExchange.format(currencyExchange.RateOfExchange);
        String simplifiedDate = formatter.format(currencyExchange.exchangeDate);
        ExchangeInfoDto exchangeInfoDto = new ExchangeInfoDto(currencyCode, rateOfExchange, exchangedMoney, simplifiedDate);

        return exchangeInfoDto;
    }

    private SimpleDateFormat GetDateFormat()
    {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private List<CurrencyExchangeDto> MapCurrencyExchangeDtoList(List<CurrencyExchange> currencyExchangeList)
    {
        SimpleDateFormat formatter = GetDateFormat();
        List<CurrencyExchangeDto> currencyExchangeDtoList = new ArrayList<CurrencyExchangeDto>();

        for (var currencyExchange : currencyExchangeList)
        {
            currencyExchangeDtoList.add(new CurrencyExchangeDto(currencyExchange.Id,
                    formatter.format(currencyExchange.exchangeDate),
                    currencyExchange.currencyCode,
                    currencyExchange.RateOfExchange));
        }

        return currencyExchangeDtoList;
    }
}
