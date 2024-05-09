CREATE DATABASE concessionaire;

USE DATABASE concessionaire;

CREATE TABLE `car` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `hp` double DEFAULT NULL,
  `kms` double DEFAULT NULL,
  `doors` int DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO car (brand, model, price, hp, kms, doors, category) VALUES 
('Lexus','LFA',25000,250,50000,2,'Coupe'),
('Nissan','370z',21000,300,80000,2,'Coupe'),
('Toyota','Celica',4000,120,150000,2,'Coupe'),
('Nissan','Silvia 240sx',18000,240,120000,2,'Coupe'),
('Honda','Prelude',5000,110,200000,2,'Sedan'),
('Mazda','Miata MX5',9000,95,250000,2,'Coupe'),
('Mercedes','CLK 230 Kompressor',6000,240,160000,2,'Coupe');