INSERT INTO orders (product_name,
                    quantity,
                    price,
                    status,
                    created_at,
                    customer_id)
SELECT 'Laptop',
       1,
       1200.00,
       'PENDING',
       CURRENT_TIMESTAMP,
       id
FROM customers
WHERE email = 'john@example.com';

INSERT INTO orders (product_name,
                    quantity,
                    price,
                    status,
                    created_at,
                    customer_id)
SELECT 'Keyboard',
       2,
       75.50,
       'PROCESSING',
       CURRENT_TIMESTAMP,
       id
FROM customers
WHERE email = 'jane@example.com';
