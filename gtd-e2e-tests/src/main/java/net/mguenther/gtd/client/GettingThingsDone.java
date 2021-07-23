package net.mguenther.gtd.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.util.List;

public interface GettingThingsDone {

    @RequestLine("POST /items")
    @Headers("Content-Type: application/json")
    Response createStock(StockCreate payload);

    @RequestLine("GET /items/{itemId}")
    @Headers("Accept: application/json")
    Stock getStock(@Param("itemId") String itemId);

    @RequestLine("GET /items")
    @Headers("Accept: application/json")
    List<Stock> getStocks();
}
