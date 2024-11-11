CREATE TABLE IF NOT EXISTS pedidos
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome varchar(100),
  valor double,
  ordem_id varchar(100)
);