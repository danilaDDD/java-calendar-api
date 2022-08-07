CREATE TRIGGER created_api_client
    BEFORE INSERT
    ON api_clients
    FOR EACH ROW
EXECUTE PROCEDURE befor_insert();

CREATE TRIGGER updated_api_client
    BEFORE UPDATE
    ON api_clients
    FOR EACH ROW
EXECUTE PROCEDURE after_update();