CREATE TABLE api_clients
(
    id       BIGINT                      NOT NULL,
    login    VARCHAR(255)                NOT NULL,
    password VARCHAR(255)                NOT NULL,
    created  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    active   BOOLEAN                     NOT NULL,
    CONSTRAINT pk_api_clients PRIMARY KEY (id)
);

ALTER TABLE api_clients
    ADD CONSTRAINT uc_api_clients_login UNIQUE (login);