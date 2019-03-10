package com.gauravpathak.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stock")
public class StockRestController {

    private RestTemplate restTemplate;
/*    private final String db_api_url="http://localhost:8081/api/db";*/

    public StockRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{username}")
    public List<Stock> getStock(@PathVariable("username")String username){

       /* UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(db_api_url).
                queryParam("username", username);*/

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/api/db/"+username,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){});

        return quoteResponse.getBody().stream().map(this::getStockPrice).collect(Collectors.toList());

    }

    private Stock getStockPrice(String quote) {
        try{
            return YahooFinance.get(quote);
        }catch (IOException ex){
            ex.printStackTrace();
            return new Stock(quote);
        }
    }
}
