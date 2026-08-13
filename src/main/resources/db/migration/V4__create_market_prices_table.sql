CREATE TABLE market_prices (
    id BIGSERIAL PRIMARY KEY,
    instrument_id BIGINT NOT NULL,
    price NUMERIC(19, 4) NOT NULL,
    price_time TIMESTAMP NOT NULL,
    source VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_market_prices_instrument
        FOREIGN KEY (instrument_id)
            REFERENCES instruments(id)
);

CREATE INDEX idx_market_prices_instrument_time
    ON market_prices (instrument_id, price_time DESC);