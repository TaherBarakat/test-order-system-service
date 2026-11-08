

CREATE TABLE IF NOT EXISTS  customers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP(6) NOT NULL,

    PRIMARY KEY (id)
    );
