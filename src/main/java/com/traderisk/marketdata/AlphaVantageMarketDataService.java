package com.traderisk.marketdata;

import com.traderisk.instrument.Instrument;
import com.traderisk.instrument.InstrumentRepository;
import com.traderisk.marketdata.config.AlphaVantageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AlphaVantageMarketDataService implements MarketDataService {

    private final RestClient restClient;
    private final AlphaVantageProperties alphaVantageProperties;
    private final InstrumentRepository instrumentRepository;
    private final MarketPriceRepository marketPriceRepository;

    public AlphaVantageMarketDataService(
            AlphaVantageProperties alphaVantageProperties,
            InstrumentRepository instrumentRepository,
            MarketPriceRepository marketPriceRepository
    ) {
        this.alphaVantageProperties = alphaVantageProperties;
        this.instrumentRepository = instrumentRepository;
        this.marketPriceRepository = marketPriceRepository;
        this.restClient = RestClient.builder()
                .baseUrl(alphaVantageProperties.getBaseUrl())
                .build();
    }

    @Override
    public MarketPrice fetchLatestPrice(String symbol) {
        String normalizedSymbol = symbol.toUpperCase();

        Instrument instrument = instrumentRepository.findBySymbol(normalizedSymbol)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found with symbol: " + normalizedSymbol));

        if (alphaVantageProperties.getApiKey() == null || alphaVantageProperties.getApiKey().isBlank()) {
            throw new IllegalStateException("Alpha Vantage API key is not configured");
        }

        Map response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", normalizedSymbol)
                        .queryParam("apikey", alphaVantageProperties.getApiKey())
                        .build()
                )
                .retrieve()
                .body(Map.class);

        if (response == null || !response.containsKey("Global Quote")) {
            throw new IllegalStateException("Invalid response from Alpha Vantage for symbol: " + normalizedSymbol);
        }

        Map globalQuote = (Map) response.get("Global Quote");

        Object priceValue = globalQuote.get("05. price");

        if (priceValue == null || priceValue.toString().isBlank()) {
            throw new IllegalStateException("No latest price found for symbol: " + normalizedSymbol);
        }

        BigDecimal price = new BigDecimal(priceValue.toString());

        MarketPrice marketPrice = new MarketPrice(
                instrument,
                price,
                LocalDateTime.now(),
                "ALPHA_VANTAGE"
        );

        return marketPriceRepository.save(marketPrice);
    }
}