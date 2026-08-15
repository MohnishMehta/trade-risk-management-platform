package com.traderisk.marketdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/market-data")
public class MarketDataController {

    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/quote/{symbol}")
    public MarketPriceResponse fetchLatestPrice(@PathVariable String symbol) {
        MarketPrice marketPrice = marketDataService.fetchLatestPrice(symbol);

        return MarketPriceResponse.from(marketPrice);
    }
}