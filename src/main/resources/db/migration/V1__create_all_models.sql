CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event_groups
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_event_groups PRIMARY KEY (id)
);

CREATE TABLE events
(
    id       BIGINT                      NOT NULL,
    name     VARCHAR(300)                NOT NULL,
    comment  TEXT,
    created  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status   VARCHAR(255)                NOT NULL,
    played   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id  BIGINT                      NOT NULL,
    group_id BIGINT                      NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    login    VARCHAR(255),
    password VARCHAR(255),
    fio      VARCHAR(255),
    email    VARCHAR(255),
    age      INTEGER,
    status   BOOLEAN,
    sex      VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_GROUP FOREIGN KEY (group_id) REFERENCES event_groups (id);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);