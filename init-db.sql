DROP DATABASE IF EXISTS product_db;
DROP DATABASE IF EXISTS trx_db;

CREATE DATABASE product_db;
CREATE DATABASE trx_db;

\c product_db;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table MST_PRODUCTS(
                             PRODUCT_ID UUID primary KEY,
                             PRODUCT_NAME VARCHAR(100) not null,
                             PRICE DECIMAL(15, 2) not null,
                             STOCK INT not null,
                             CREATED_DATE TIMESTAMP default CURRENT_TIMESTAMP
);

INSERT INTO MST_PRODUCTS (PRODUCT_ID, PRODUCT_NAME, PRICE, STOCK)
VALUES (gen_random_uuid(), 'Macbook Pro M3', 25000000, 7);

\c trx_db;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table TRX_TRANSACTIONS(
                                 TRX_ID UUID primary KEY,
                                 PRODUCT_ID UUID,
                                 QUANTITY INT not null,
                                 TRX_AMOUNT DECIMAL(15, 2) not null,
                                 TRX_DATE TIMESTAMP default CURRENT_TIMESTAMP,
                                 TRX_STATUS VARCHAR(10)
);