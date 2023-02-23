DROP TABLE IF EXISTS Users;

CREATE TABLE Users
(
    id                 SERIAL PRIMARY KEY,
    firstName          VARCHAR(50)         NOT NULL,
    lastName           VARCHAR(50)         NOT NULL,
    age                INTEGER,
    login              VARCHAR(50) UNIQUE  NOT NULL,
    password           VARCHAR(100)        NOT NULL,
    email              VARCHAR(100) UNIQUE NOT NULL,
    dateOfRegistration DATE DEFAULT CURRENT_DATE,
    isActive           BOOLEAN             NOT NULL,
    role               VARCHAR(20)         NOT NULL
);