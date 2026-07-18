CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_app_users_role
        FOREIGN KEY (role_id)
            REFERENCES roles(id)
);

INSERT INTO roles (name, description)
VALUES
    ('TRADER', 'Can create portfolios and submit trade orders'),
    ('RISK_ANALYST', 'Can review portfolio risk metrics and exposure'),
    ('COMPLIANCE_ANALYST', 'Can investigate suspicious trade alerts'),
    ('ADMIN', 'Can manage users, roles, and system configuration'),
    ('AUDITOR', 'Can view audit logs and historical system activity');