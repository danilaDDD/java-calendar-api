ALTER TABLE api_clients
    ADD encoded_password TEXT;

ALTER TABLE api_clients
    ALTER COLUMN encoded_password SET NOT NULL;

ALTER TABLE api_clients
    DROP COLUMN password;

ALTER TABLE users
    RENAME password TO encoded_password;

ALTER TABLE users
    DROP COLUMN role;

ALTER TABLE users
    ADD role SMALLINT NOT NULL default 0;

ALTER TABLE users
    ALTER COLUMN role SET NOT NULL;