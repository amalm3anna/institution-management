-- start of the script
BEGIN;

-- create the table INSTITUTION in the institution_management database
CREATE TABLE IF NOT EXISTS INSTITUTION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    code INT NOT NULL CHECK (code BETWEEN 0 AND 99999),
    name VARCHAR(50) NOT NULL,
    status INT CHECK (status IN (0, 1)) NOT NULL
);

-- create the table USER in the institution_management database
CREATE TABLE IF NOT EXISTS APP_USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- end of the script
END;