CREATE TABLE Client (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200 )
);

CREATE TABLE Planet (
    id VARCHAR(50) PRIMARY KEY CHECK (id ~ '^[A-Z0-9]+$'),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 500)
);

CREATE TABLE Ticket (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL,
    client_id BIGINT,
    from_planet_id VARCHAR(50),
    to_planet_id VARCHAR(50),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
);
