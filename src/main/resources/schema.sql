CREATE TABLE hotel (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    location VARCHAR(255),
    rating Int
);

CREATE TABLE room (
    id INT PRIMARY KEY AUTO_INCREMENT,    
    roomNumber VARCHAR(255),
    type VARCHAR(255),
    price DOUBLE,
    hotelId Int FOREIGN KEY
);

CREATE TABLE hotel_room (
    researcherId INT,
    projectId INT,
    PRIMARY KEY(hotelId, roomId),
    FOREIGN KEY(roomId) REFERENCES room(id),
    FOREIGN KEY(hotelId) REFERENCES hotel(id)
);