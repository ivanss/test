DROP TABLE IF EXISTS exchange_rate;

CREATE TABLE exchange_rate (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  country VARCHAR(100) NOT NULL,
  currency_from VARCHAR(5) NOT NULL,
  currency_to VARCHAR(5) NOT NULL,
  exchange_rate DECIMAL(20,3) NOT NULL,
  date DATE NOT NULL
);

INSERT INTO exchange_rate (country, currency_from, currency_to, exchange_rate, date) VALUES
  ('PE', 'PEN', 'USD', 4.098, NOW()),
  ('PE', 'USD', 'PEN', 4.109, NOW());