CREATE TABLE orders (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        product_name VARCHAR(255) NOT NULL,
                        quantity INT NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP(6) NOT NULL,
                        customer_id BIGINT NOT NULL,

                        PRIMARY KEY (id),

                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
) ENGINE=InnoDB;
