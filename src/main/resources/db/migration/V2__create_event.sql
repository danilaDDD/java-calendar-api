CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE events
(
    id      BIGINT                      NOT NULL,
    name    VARCHAR(300)                NOT NULL,
    comment TEXT                        NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    played  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id INTEGER,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);