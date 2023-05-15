SELECT i.id AS invoice_id, 
       f.id AS flight_id,
	   f.airline_name as airline_name,
       dep.name AS departure, 
       dest.name AS destination, 
       f.takeoff_time, 
       f.departure_date, 
       s.seat_number, 
       i.total_price, 
       i.purchase_status
FROM invoice i
JOIN flight f ON i.flight_id = f.id
JOIN airport dep ON f.departure_id = dep.id
JOIN airport dest ON f.destination_id = dest.id
JOIN passenger_ticket pt ON i.id = pt.invoice_id
JOIN seat s ON pt.id = s.passenger_ticket_id
WHERE i.user_id = 1

UPDATE [invoice]  
SET purchase_status = 0 
FROM [invoice] i 
JOIN passenger_ticket pt ON i.id = pt.invoice_id 
JOIN seat s ON pt.id = s.passenger_ticket_id 
WHERE i.user_id = 1 AND i.purchase_status = 1 AND i.id = 2 AND i.flight_id = 2 AND s.seat_number = 'C1'

-- Insert sample users
INSERT INTO [user] (name, password, email, phone) 
VALUES 
    ('John Doe', 'password1', 'john.doe@example.com', '555-1234'),
    ('Jane Smith', 'password2', 'jane.smith@example.com', '555-5678');

-- Insert sample airports
INSERT INTO airport (name)
VALUES 
    ('John F. Kennedy International Airport'),
    ('Los Angeles International Airport'),
    ('London Heathrow Airport'),
    ('Tokyo Haneda Airport');

-- Insert sample flights
INSERT INTO flight (takeoff_time, landing_time, departure_date, price, airline_name, no_of_seats, departure_id, destination_id, [status])
VALUES 
    ('12:00:00', '16:00:00', '2023-03-20', 500000, 'American Airlines', 200, 1, 2, 1),
    ('08:00:00', '11:00:00', '2023-03-22', 300000, 'Delta Airlines', 150, 2, 3, 1),
    ('21:00:00', '07:00:00', '2023-03-25', 800000, 'United Airlines', 100, 3, 4, 0);

-- Insert sample invoices
INSERT INTO invoice ([user_id], flight_id, booking_date, total_price, purchase_status)
VALUES 
    (1, 1, '2023-03-15', 500000, 1),
	(1, 2, '2023-03-15', 500000, 0),
    (2, 2, '2023-03-18', 300000, 1),
    (1, 3, '2023-03-20', 800000, 0);

-- Insert sample luggage prices
INSERT INTO luggage ([weight], [price])
VALUES 
    (20, 50000),
    (30, 100000),
    (40, 150000);

-- Insert sample passenger tickets
INSERT INTO passenger_ticket (invoice_id, firstname, lastname, luggage_weight, card_id, gender, nationality, dob)
VALUES 
    (1, 'John', 'Doe', 20, '1234567', 'Male', 'USA', '1990-01-01'),    
	(1, 'Jane', 'Doe', 40, '2345678901', 'Female', 'USA', '1995-01-01'),
    (2, 'John', 'Smith', 30, '345678', 'Male', 'Canada', '1985-01-01'),
    (2, 'Jane', 'Smith', 40, '4563456789', 'Female', 'Canada', '1990-01-01'),
    (3, 'John', 'Doe', 40, '56789010', 'Male', 'USA', '1980-01-01');

INSERT INTO seat (seat_number, flight_id, [passenger_ticket_id], seat_status)
VALUES 
    ('A1', 1, 1, 1),
    ('A2', 1, 1, 1),
    ('B1', 1, 2, 0),
    ('B2', 1, 2, 0),
    ('C1', 1, 3, 0);