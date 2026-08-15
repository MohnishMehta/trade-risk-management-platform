package com.traderisk.marketdata;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MarketPriceResponse(
        Long id,
        Long instrumentId,
        String symbol,
        String instrumentName,
        BigDecimal price,
        LocalDateTime priceTime,
        String source,
        LocalDateTime createdAt
) {
    public static MarketPriceResponse from(MarketPrice marketPrice) {
        return new MarketPriceResponse(
                marketPrice.getId(),
                marketPrice.getInstrument().getId(),
                marketPrice.getInstrument().getSymbol(),
                marketPrice.getInstrument().getName(),
                marketPrice.getPrice(),
                marketPrice.getPriceTime(),
                marketPrice.getSource(),
                marketPrice.getCreatedAt()
        );
    }
}