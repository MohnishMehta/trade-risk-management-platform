package com.traderisk.marketdata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketPriceRepository extends JpaRepository<MarketPrice, Long> {

    Optional<MarketPrice> findTopByInstrumentIdOrderByPriceTimeDesc(Long instrumentId);

    List<MarketPrice> findByInstrumentIdOrderByPriceTimeDesc(Long instrumentId);
}
