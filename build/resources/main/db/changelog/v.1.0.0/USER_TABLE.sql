CREATE TABLE users
(
    id UUID PRIMARY KEY,
    username VARCHAR(21) NOT NULL UNIQUE ,
    first_name VARCHAR(21) NOT NULL,
    last_name VARCHAR(21) NOT NULL,
    birthdate DATE,
    biography TEXT,
    city VARCHAR(21),
    password_hash VARCHAR(60) NOT NULL
);