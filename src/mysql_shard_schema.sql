CREATE TABLE user (
 user_name TEXT UNIQUE NOT NULL, 
 region ENUM('flanders','wallonia','brussels') NOT NULL
)
CREATE TABLE post (
 message TEXT NOT NULL,
 user_name TEXT NOT NULL,
 FOREIGN KEY(user_name) REFERENCES user
)
