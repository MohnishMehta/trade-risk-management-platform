CREATE TABLE instruments (
    id BIGSERIAL PRIMARY KEY,
    symbol VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    asset_type VARCHAR(50) NOT NULL,
    exchange VARCHAR(50),
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO instruments (symbol, name, asset_type, exchange, currency)
VALUES
    ('AAPL', 'Apple Inc.', 'STOCK', 'NASDAQ', 'USD'),
    ('MSFT', 'Microsoft Corporation', 'STOCK', 'NASDAQ', 'USD'),
    ('TSLA', 'Tesla Inc.', 'STOCK', 'NASDAQ', 'USD'),
    ('SPY', 'SPDR S&P 500 ETF Trust', 'ETF', 'NYSEARCA', 'USD'),
    ('QQQ', 'Invesco QQQ Trust', 'ETF', 'NASDAQ', 'USD');