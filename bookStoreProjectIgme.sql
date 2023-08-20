create database bookStoreIgme;
use bookStoreIgme;

CREATE TABLE book (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(255),
  author VARCHAR(255),
  price DECIMAL(10,2)
);

CREATE TABLE my_books (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR(255),
  author VARCHAR(255),
  price DECIMAL(10,2)
);


INSERT INTO book (name, author, price) VALUES
  ('Libro 1', 'Autor 1', 19.99),
  ('Libro 2', 'Autor 2', 29.99),
  ('Libro 3', 'Autor 3', 39.99);
  
  
  
  -- User
  -- users_role
CREATE TABLE User (
  id INT PRIMARY KEY,
  email VARCHAR(255),
  name VARCHAR(255),
  password VARCHAR(255)
);

-- Tabla para almacenar roles
CREATE TABLE Role (
  RoleId INT PRIMARY KEY,
  Name VARCHAR(255)
);

-- Tabla intermedia para la relación entre usuarios y roles
CREATE TABLE UsersRole (
  cust_id INT,
  role_id INT,
  FOREIGN KEY (cust_id) REFERENCES User(id),
  FOREIGN KEY (role_id) REFERENCES Role(RoleId),
  PRIMARY KEY (cust_id, role_id)
);







INSERT INTO Role (id,role) VALUES (1,'ADMIN');
INSERT INTO Role (id,role) VALUES (2,'USER');


  
  CREATE TABLE customer (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255),
  address VARCHAR(255)
);

CREATE TABLE purchase (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  book_id INT,
  purchase_date DATE,
  price DECIMAL(10,2),
  FOREIGN KEY (customer_id) REFERENCES customer(id),
  FOREIGN KEY (book_id) REFERENCES book(id)
);

INSERT INTO customer (name, email, address) VALUES
  ('Cliente 1', 'cliente1@email.com', 'Dirección 1'),
  ('Cliente 2', 'cliente2@email.com', 'Dirección 2'),
  ('Cliente 3', 'cliente3@email.com', 'Dirección 3');

INSERT INTO purchase (customer_id, book_id, purchase_date, price) VALUES
  (1, 1, '2023-08-17', 19.99),
  (2, 2, '2023-08-16', 29.99),
  (3, 3, '2023-08-15', 39.99);
  
