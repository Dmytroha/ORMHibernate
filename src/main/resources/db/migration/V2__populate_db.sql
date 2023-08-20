-- Insert Clients
INSERT INTO Client (name) VALUES ('Alice'),
('Leonard'),('Kenny'),('Jaja'),('Khun'),('Burt'),
('Teem'),('John'),('Poul'),('Jimmy'),('Georg');
-- ... Add more clients

-- Insert Planets
INSERT INTO Planet (id, name) VALUES ('MARS', 'Mars'),
('VEN', 'Venus'),('JUP','Jupiter'),('NEP','Neptun'),('SAT','Saturn');
-- ... Add more planets

-- Insert Tickets
INSERT INTO Ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES ('2023-08-11 12:01:10', 1, 'MARS', 'VEN'),
        ('2023-08-17 13:34:40', 2, 'JUP', 'SAT'),
        ('2023-07-10 09:15:32', 3, 'VEN', 'SAT'),
        ('2023-06-02 10:00:50', 4, 'MARS', 'SAT'),
        ('2023-03-07 11:30:05', 5, 'NEP', 'MARS'),
        ('2023-04-05 14:00:21', 6, 'SAT', 'MARS'),
        ('2023-05-01 13:00:26', 7, 'MARS', 'VEN'),
        ('2023-09-16 09:15:17', 8, 'SAT', 'NEP'),
        ('2023-11-25 10:00:12', 9, 'SAT', 'JUP'),
        ('2023-02-17 11:30:43', 11, 'VEN', 'MARS');
-- ... Add more tickets
