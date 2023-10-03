BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO locationType (location_type_name) VALUES ('Parks');
INSERT INTO locationType (location_type_name) VALUES ('Bars');
INSERT INTO locationType (location_type_name) VALUES ('Stadiums');

INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (1, 'Wendy Park', 41.4991564, -81.7138946);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (1, 'Settlers Park', 41.4966056, -81.7013211);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (2, 'Collision Bend Brewing Company', 41.4986853, -81.7037735);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (2, 'Butcher and the Brewer', 41.4991465, -81.6900132);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (2, 'Barley House', 41.5005667, -81.6991611);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (2, 'BrewDog Cleveland Outpost', 41.492705, -81.698024);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (2, 'Great Lakes Brewing Company', 41.4844029, -81.7045326);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (3, 'Progressive Field', 41.4958921, -81.6852949);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (3, 'Cleveland Browns Stadium', 41.5060535, -81.6995481);
INSERT INTO location (location_type_id, location_name, location_latitude, location_longitude) VALUES (3, 'Rocket Mortgage FieldHouse', 41.4965474, -81.6880574);

INSERT INTO rewards (amount_bars_visited, amount_parks_visited, amount_stadiums_visited, all_bars_visited, all_parks_visited, all_stadiums_visited, all_places_visited) VALUES (0, 0, 0, false, false, false, false);

COMMIT TRANSACTION;
