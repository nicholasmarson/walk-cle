BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS location_type;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS rewards;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE location_type (
    location_type_id SERIAL,
    location_type_name varchar(50) NOT NULL UNIQUE,
    CONSTRAINT PK_location_type PRIMARY KEY (location_type_id)
);

CREATE TABLE location (
    location_id SERIAL,
    location_type_name varchar (50) NOT NULL,
    location_name varchar (50) NOT NULL UNIQUE,
    location_latitude double precision NOT NULL,
    location_longitude double precision NOT NULL,
    location_description varchar (300),
    location_sun_open varchar (10),
    location_sun_close varchar (10),
    location_mon_open varchar (10),
    location_mon_close varchar (10),
    location_tue_open varchar (10),
    location_tue_close varchar (10),
    location_wed_open varchar (10),
    location_wed_close varchar (10),
    location_thu_open varchar (10),
    location_thu_close varchar (10),
    location_fri_open varchar (10),
    location_fri_close varchar (10),
    location_sat_open varchar (10),
    location_sat_close varchar (10),
    location_img_url varchar (300),
    location_info_url varchar (300),
    CONSTRAINT PK_location PRIMARY KEY (location_id),
    CONSTRAINT FK_location_type_name FOREIGN KEY (location_type_name) REFERENCES location_type (location_type_name)
);

CREATE TABLE rewards (
    rewards_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    amount_bars_visited int NOT NULL,
    amount_parks_visited int NOT NULL,
    amount_stadiums_visited int NOT NULL,
    all_bars_visited boolean DEFAULT false,
    all_parks_visited boolean DEFAULT false,
    all_stadiums_visited boolean DEFAULT false,
    all_places_visited boolean DEFAULT false,
    CONSTRAINT PK_rewards_id PRIMARY KEY (rewards_id),
    CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE checkins (
    checkin_id SERIAL,
    username varchar(50) NOT NULL,
    location_id SERIAL,
    checked_in boolean DEFAULT false,
    checkin_timestamp TIMESTAMP,
    CONSTRAINT PK_checkin_id PRIMARY KEY (checkin_id),
    CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username),
    CONSTRAINT FK_location_id FOREIGN KEY (location_id) REFERENCES location (location_id)
);

COMMIT TRANSACTION;
