package com.traderisk.portfolio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PortfolioResponse(
        Long id,
        Long ownerUserId,
        String ownerEmail,
        String name,
        String baseCurrency,
        BigDecimal cashBalance,
        String status,
        LocalDateTime createdAt
) {
    public static PortfolioResponse from(Portfolio portfolio) {
        return new PortfolioResponse(
                portfolio.getId(),
                portfolio.getOwnerUser().getId(),
                portfolio.getOwnerUser().getEmail(),
                portfolio.getName(),
                portfolio.getBaseCurrency(),
                portfolio.getCashBalance(),
                portfolio.getStatus(),
                portfolio.getCreatedAt()
        );
    }
}