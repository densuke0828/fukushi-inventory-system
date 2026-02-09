--  -- 一旦リセット
--  DROP TABLE IF EXISTS stocks;
--  DROP TABLE IF EXISTS products;
--  DROP TABLE IF EXISTS statuses;
--  DROP TABLE IF EXISTS locations;
--  DROP TABLE IF EXISTS categories;
--  DROP TABLE IF EXISTS manufactures;
--  DROP TABLE IF EXISTS rental_record;
--  DROP TABLE IF EXISTS equipment;

-- メーカーテーブル
CREATE TABLE manufactures (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 用具種目テーブル
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 保管場所テーブル
CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 用具状態テーブル
CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) UNIQUE NOT NULL,
    display_name VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 製品テーブル
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    manufactures_id INT REFERENCES manufactures(id),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- 在庫テーブル
CREATE TABLE stocks (
    id SERIAL PRIMARY KEY,
    serial_code VARCHAR(20) UNIQUE NOT NULL,
    products_id INT NOT NULL REFERENCES products(id),
    locations_id INT NOT NULL REFERENCES locations(id),
    statuses_id INT NOT NULL REFERENCES statuses(id),
    purchased_at DATE,
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);