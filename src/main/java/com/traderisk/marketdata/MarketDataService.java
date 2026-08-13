package com.traderisk.marketdata;

public interface MarketDataService {
    MarketPrice fetchLatestPrice(String symbol);
}
