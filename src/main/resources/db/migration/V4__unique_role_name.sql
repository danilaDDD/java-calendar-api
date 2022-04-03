ALTER TABLE role
    ADD CONSTRAINT uc_role_name UNIQUE (name);