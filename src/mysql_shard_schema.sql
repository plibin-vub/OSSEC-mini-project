CREATE TABLE user (
 name VARCHAR(100) UNIQUE NOT NULL, 
 region ENUM('flanders','wallonia','brussels') NOT NULL,
 PRIMARY KEY (name)
);
CREATE TABLE post (
 id INT,
 message TEXT NOT NULL,
 name VARCHAR(100) NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (name) REFERENCES user(name)
);
