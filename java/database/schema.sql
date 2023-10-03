BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS locationType;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS rewards;
DROP TABLE IF EXISTS locationData;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE locationType (
    location_type_id SERIAL,
    location_type_name varchar(50) NOT NULL UNIQUE,
    CONSTRAINT PK_location_type PRIMARY KEY (location_type_id)
);

CREATE TABLE location (
    location_id SERIAL,
    location_type_id int NOT NULL,
    location_name varchar (50) NOT NULL UNIQUE,
    location_latitude double precision NOT NULL,
    location_longitude double precision NOT NULL,
    CONSTRAINT PK_location PRIMARY KEY (location_id),
    CONSTRAINT FK_location_type FOREIGN KEY (location_type_id) REFERENCES locationType (location_type_id)
);

CREATE TABLE rewards (
    rewards_id SERIAL,
    user_id SERIAL,
    location_type_name varchar(50),
    amount_bars_visited int NOT NULL,
    amount_parks_visited int NOT NULL,
    amount_stadiums_visited int NOT NULL,
    all_bars_visited boolean DEFAULT false,
    all_parks_visited boolean DEFAULT false,
    all_stadiums_visited boolean DEFAULT false,
    all_places_visited boolean DEFAULT false,
    CONSTRAINT PK_rewards_id PRIMARY KEY (rewards_id),
    CONSTRAINT FK_location_type_name FOREIGN KEY (location_type_name) REFERENCES locationType (location_type_name),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE locationData (
    location_data_id SERIAL,
    location_data_name varchar (50) NOT NULL UNIQUE,
    location_data_description varchar (200) NOT NULL,
    location_data_days varchar (15) NOT NULL,
    location_data_opening_times varchar (15) NOT NULL,
    location_data_closing_times varchar (15) NOT NULL,
    location_data_img_url varchar (200),
    location_data_info_url varchar (200),
    CONSTRAINT PK_location_data PRIMARY KEY (location_data_id),
    CONSTRAINT FK_location_data_name FOREIGN KEY (location_data_name) REFERENCES location (location_name)
);



COMMIT TRANSACTION;
