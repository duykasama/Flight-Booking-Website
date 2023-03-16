USE vnflight
GO
if object_id('DeleteFlightRecord') is not null
	drop proc DeleteFlightRecord 
go
CREATE PROCEDURE DeleteFlightRecord 
    @flightID int
AS
BEGIN
    SET NOCOUNT ON;

    -- Delete related records from seat table
    UPDATE seat
	SET flight_id = 222
	WHERE flight_id = @flightID;

    -- Delete related records from invoice table
    UPDATE invoice
	SET flight_id = 222
	WHERE flight_id = @flightID;

    -- Delete record from flight table
    DELETE FROM flight
    WHERE id = @flightID;
END

--call DeleteFlightRecord('12')
select * from flight