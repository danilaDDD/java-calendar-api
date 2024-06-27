ALTER TABLE events
    DROP CONSTRAINT fk_events_on_group;

DROP TABLE event_groups CASCADE;

ALTER TABLE events
    DROP COLUMN group_id;