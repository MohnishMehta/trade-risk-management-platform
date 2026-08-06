package com.traderisk.portfolio;

import java.math.BigDecimal;

public record CreatePortfolioRequest(
        Long ownerUserId,
        String name,
        String baseCurrency,
        BigDecimal cashBalance
) {
}