CREATE TABLE portfolios (
    id BIGSERIAL PRIMARY KEY,
    owner_user_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    base_currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    cash_balance NUMERIC(19, 4) NOT NULL DEFAULT 0.0000,
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_portfolios_owner_user
        FOREIGN KEY (owner_user_id)
            REFERENCES app_users(id)
);