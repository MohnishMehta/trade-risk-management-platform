package com.traderisk.marketdata;

import com.traderisk.instrument.Instrument;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "market_prices")
public class MarketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "price_time", nullable = false)
    private LocalDateTime priceTime;

    @Column(nullable = false, length = 50)
    private String source;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected MarketPrice() {
    }

    public MarketPrice(Instrument instrument, BigDecimal price, LocalDateTime priceTime, String source) {
        this.instrument = instrument;
        this.price = price;
        this.priceTime = priceTime;
        this.source = source;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getPriceTime() {
        return priceTime;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}