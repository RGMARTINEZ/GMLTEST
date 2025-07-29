CREATE DATABASE IF NOT EXISTS librarydb;

USE librarydb;

CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL,
    isbn VARCHAR(20) NOT NULL
);

CREATE USER IF NOT EXISTS 'libraryuser'@'%' IDENTIFIED BY 'librarypass';

GRANT ALL PRIVILEGES ON librarydb.* TO 'libraryuser'@'%';

FLUSH PRIVILEGES;
