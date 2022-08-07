CREATE OR REPLACE FUNCTION befor_insert()
    RETURNS trigger AS
$$
BEGIN
    NEW.CREATED = NOW();
    RETURN NEW;
END;
$$


    LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION after_update()
    RETURNS trigger AS
$$
BEGIN
    NEW.updated = NOW();
    RETURN NEW;
END;
$$

    LANGUAGE 'plpgsql';