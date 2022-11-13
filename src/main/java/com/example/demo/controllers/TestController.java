package com.example.demo.controllers;

import com.example.demo.models.responses.FxRates;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class TestController {
    @GetMapping("/currency")
    public FxRates GetCurrency() throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=eu&dt=2017-12-25"))
                .header("Content-Type", "text/xml")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());


        var test = response.body();

        XmlMapper xmlMapper = new XmlMapper();
        FxRates value = xmlMapper.readValue(response.body(), FxRates.class);

        return value;
    }
}
