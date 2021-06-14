DROP SCHEMA IF EXISTS ambre_fouvez CASCADE;
CREATE SCHEMA ambre_fouvez;


CREATE TABLE ambre_fouvez.nationalities (
	id SERIAL PRIMARY KEY,
	nationality VARCHAR(30) NULL UNIQUE CHECK(nationality <> '')
);

CREATE TABLE ambre_fouvez.sizes (
	id SERIAL PRIMARY KEY,
	size VARCHAR(20) NULL UNIQUE CHECK(size <> '')
);

CREATE TABLE ambre_fouvez.colours (
	id SERIAL PRIMARY KEY,
	color VARCHAR(20) NULL UNIQUE CHECK(color <> '')
);

CREATE TABLE ambre_fouvez.addresses (
	id SERIAL PRIMARY KEY,
	country VARCHAR(100) NOT NULL CHECK(country <> ''),
	commune VARCHAR(100) NOT NULL CHECK(commune <> ''),
	postcode VARCHAR(10) NOT NULL CHECK(postcode <> ''),
	street VARCHAR(100) NOT NULL CHECK(street <> ''),
	building_number VARCHAR(10) NOT NULL CHECK(building_number <> ''),
	unit_number VARCHAR(10) NULL,
    UNIQUE(country, commune, postcode, street, building_number, unit_number)
);

CREATE TABLE ambre_fouvez.photographers (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NULL UNIQUE CHECK(name <> '')
);

CREATE TABLE ambre_fouvez.photos (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NULL UNIQUE CHECK(name <> ''),
    picture VARCHAR NOT NULL CHECK(picture <> ''),
    photographer INTEGER REFERENCES ambre_fouvez.photographers(id) NOT NULL
);

CREATE TABLE ambre_fouvez.tags (
	id SERIAL PRIMARY KEY,
    label VARCHAR NOT NULL UNIQUE CHECK(label <> '')
);

CREATE TABLE ambre_fouvez.tags_photo (
	photo_id INTEGER REFERENCES ambre_fouvez.photos(id) NOT NULL,
	tag_id INTEGER REFERENCES ambre_fouvez.tags(id) NOT NULL,
	CONSTRAINT id PRIMARY KEY(photo_id, tag_id)
);

CREATE TABLE ambre_fouvez.users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL UNIQUE CHECK(username <> ''),
	last_name VARCHAR(100) NOT NULL CHECK(last_name <> ''),
	first_name VARCHAR(100) NOT NULL CHECK(first_name <> ''),
	email VARCHAR(100) NOT NULL UNIQUE CHECK(email SIMILAR TO '[\w\.\/\\$é~#èà&=+*-]+@[\w\.]+\.[a-zA-Z]{2,4}'),
	is_boss BOOLEAN NOT NULL DEFAULT FALSE,
	registration_date TIMESTAMP NOT NULL CHECK(registration_date <= current_timestamp),
	password VARCHAR(60) NOT NULL CHECK(password <> ''),
    profile_picture INTEGER REFERENCES ambre_fouvez.photos(id) NOT NULL,
    address INTEGER REFERENCES ambre_fouvez.addresses(id) NULL,
    phone_number VARCHAR(20) NULL CHECK(phone_number <> ''),
    facebook VARCHAR(50) NULL CHECK(facebook SIMILAR TO 'https:\/\/www\.facebook\.com\/[\w\.\/\\$é~#èà&=+*-]+'),
    instagram VARCHAR(50) NULL CHECK(instagram SIMILAR TO 'https:\/\/www\.instagram\.com\/[\w\.\/\\$é~#èà&=+*-]+\/'),
    twitter VARCHAR(50) NULL CHECK(twitter SIMILAR TO 'https:\/\/www\.twitter\.com\/[\w\.\/\\$é~#èà&=+*-]+'),
    youtube VARCHAR(50) NULL CHECK(youtube SIMILAR TO 'https:\/\/www\.youtube\.com\/channel\/[\w\.\/\\$é~#èà&=+*-]+'),
    hair_color INTEGER REFERENCES ambre_fouvez.colours(id) NULL,
    hair_size INTEGER REFERENCES ambre_fouvez.sizes(id) NULL,
    eye INTEGER REFERENCES ambre_fouvez.colours(id) NULL,
    height INTEGER NULL CHECK(height > 0 AND height < 300), --cm--
    weight INTEGER NULL CHECK(weight > 0 AND weight < 700), --kg--
    first_nationality INTEGER REFERENCES ambre_fouvez.nationalities(id) NULL,
    second_nationality INTEGER REFERENCES ambre_fouvez.nationalities(id) NULL,
    shoe_size INTEGER NULL CHECK(shoe_size > 0 AND shoe_size < 150),
    jacket_size INTEGER NULL CHECK(jacket_size > 0 AND jacket_size < 150),
    pant_size INTEGER NULL CHECK(pant_size > 0 AND pant_size < 150),
    chest INTEGER NULL CHECK(chest > 0 AND chest < 150), --cm--
    bra_cup CHAR(1) NULL CHECK(bra_cup <> ''),
    waist_size INTEGER NULL CHECK(waist_size > 0 AND waist_size < 150), --cm--
    hip_size INTEGER NULL CHECK(hip_size > 0 AND hip_size < 150), --cm--
    neck_size INTEGER NULL CHECK(neck_size > 0 AND neck_size < 150), --cm--
    head_size INTEGER NULL CHECK(head_size > 0 AND head_size < 150) --cm--
);

CREATE TABLE ambre_fouvez.professions (
	id SERIAL PRIMARY KEY,
    label VARCHAR NOT NULL UNIQUE CHECK(label <> '')
);

CREATE TABLE ambre_fouvez.curriculum_vitae (
	id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL CHECK(title <> ''),
    user_id INTEGER REFERENCES ambre_fouvez.users(id) NOT NULL,
    profession INTEGER REFERENCES ambre_fouvez.professions(id) NOT NULL,
    playing_age VARCHAR(9) NOT NULL CHECK(playing_age SIMILAR TO '[0-9]{2,3} - [0-9]{2,3}'),
    background_picture INTEGER REFERENCES ambre_fouvez.photos(id) NOT NULL
);

CREATE TABLE ambre_fouvez.trainings (
	id SERIAL PRIMARY KEY,
    start_year INTEGER NOT NULL CHECK(start_year BETWEEN date_part('year', current_timestamp)-120 AND date_part('year', current_timestamp)+1),
    end_year INTEGER NOT NULL CHECK(end_year = 0 OR (end_year BETWEEN date_part('year', current_timestamp)-120 AND date_part('year', current_timestamp)+1)),
    label VARCHAR(100) NOT NULL CHECK(label <> ''),
    explanations VARCHAR(200) NOT NULL CHECK(explanations <> '')
);

CREATE TABLE ambre_fouvez.trainings_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	training INTEGER REFERENCES ambre_fouvez.trainings(id) NOT NULL,
    order_number INTEGER NOT NULL CHECK(order_number > 0),
    UNIQUE(order_number, curriculum_vitae, training),
    CONSTRAINT tc_pkey PRIMARY KEY(curriculum_vitae, training)
);

CREATE TABLE ambre_fouvez.roles (
	id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE CHECK(name <> '')
);

CREATE TABLE ambre_fouvez.short_films (
	id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL CHECK(title <> ''),
    role INTEGER REFERENCES ambre_fouvez.roles(id) NOT NULL,
    year INTEGER NOT NULL CHECK(year BETWEEN date_part('year', current_timestamp)-120 AND date_part('year', current_timestamp)+1)
);

CREATE TABLE ambre_fouvez.short_films_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	short_film INTEGER REFERENCES ambre_fouvez.short_films(id) NOT NULL,
    order_number INTEGER NOT NULL CHECK(order_number > 0),
    UNIQUE(order_number, curriculum_vitae, short_film),
    CONSTRAINT sc_pkey PRIMARY KEY(curriculum_vitae, short_film)
);

CREATE TABLE ambre_fouvez.companies (
	id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE CHECK(name <> '')
);

CREATE TABLE ambre_fouvez.companies_short_film (
	short_film_id INTEGER REFERENCES ambre_fouvez.short_films(id) NOT NULL,
	company_id INTEGER REFERENCES ambre_fouvez.companies(id) NOT NULL,
	CONSTRAINT cs_pkey PRIMARY KEY(short_film_id, company_id)
);

CREATE TABLE ambre_fouvez.cinemas (
	id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL CHECK(title <> ''),
    role INTEGER REFERENCES ambre_fouvez.roles(id) NOT NULL,
    year INTEGER NOT NULL CHECK(year BETWEEN date_part('year', current_timestamp)-120 AND date_part('year', current_timestamp)+1)
);

CREATE TABLE ambre_fouvez.cinemas_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	cinema INTEGER REFERENCES ambre_fouvez.cinemas(id) NOT NULL,
    order_number INTEGER NOT NULL CHECK(order_number > 0),
    UNIQUE(order_number, curriculum_vitae, cinema),
    CONSTRAINT cc_pkey PRIMARY KEY(curriculum_vitae, cinema)
);

CREATE TABLE ambre_fouvez.distinctions (
	id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE CHECK(name <> '')
);

CREATE TABLE ambre_fouvez.distinctions_cinema (
	cinema_id INTEGER REFERENCES ambre_fouvez.cinemas(id) NOT NULL,
	distinction_id INTEGER REFERENCES ambre_fouvez.distinctions(id) NOT NULL,
	CONSTRAINT dc_pkey PRIMARY KEY(cinema_id, distinction_id)
);

CREATE TABLE ambre_fouvez.directors (
	id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE CHECK(name <> '')
);

CREATE TABLE ambre_fouvez.directors_short_film (
	short_film_id INTEGER REFERENCES ambre_fouvez.short_films(id) NOT NULL,
	director_id INTEGER REFERENCES ambre_fouvez.directors(id) NOT NULL,
	CONSTRAINT ds_pkey PRIMARY KEY(short_film_id, director_id)
);

CREATE TABLE ambre_fouvez.directors_cinema (
	cinema_id INTEGER REFERENCES ambre_fouvez.cinemas(id) NOT NULL,
	director_id INTEGER REFERENCES ambre_fouvez.directors(id) NOT NULL,
	CONSTRAINT drc_pkey PRIMARY KEY(cinema_id, director_id)
);

CREATE TABLE ambre_fouvez.strengths (
	id SERIAL PRIMARY KEY,
    label VARCHAR(50) NULL UNIQUE CHECK(label <> '')
);

CREATE TABLE ambre_fouvez.strengths_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	strength INTEGER REFERENCES ambre_fouvez.strengths(id) NOT NULL,
    order_number INTEGER NOT NULL CHECK(order_number > 0),
    UNIQUE(order_number, curriculum_vitae, strength),
    CONSTRAINT scv_pkey PRIMARY KEY(curriculum_vitae, strength)
);



----------------------------------------------------------------------------------------
---------------------------------------- INSERT ----------------------------------------
----------------------------------------------------------------------------------------


------------------------------
-----INSERT nationalities-----
------------------------------

INSERT INTO ambre_fouvez.nationalities(
	id, nationality)
	VALUES (DEFAULT, 'Belge');

----------------------
-----INSERT sizes-----
----------------------

INSERT INTO ambre_fouvez.sizes(
	id, size)
	VALUES (DEFAULT, 'Court');

INSERT INTO ambre_fouvez.sizes(
	id, size)
	VALUES (DEFAULT, 'Moyen');

INSERT INTO ambre_fouvez.sizes(
	id, size)
	VALUES (DEFAULT, 'Long');

------------------------
-----INSERT colours-----
------------------------

INSERT INTO ambre_fouvez.colours(
	id, color)
	VALUES (DEFAULT, 'Châtain');

INSERT INTO ambre_fouvez.colours(
	id, color)
	VALUES (DEFAULT, 'Verts - gris');

--------------------------
-----INSERT addresses-----
--------------------------

INSERT INTO ambre_fouvez.addresses(
	id, country, commune, postcode, street, building_number, unit_number)
	VALUES (DEFAULT, 'France', 'Créteil', '94000', 'Rue Charles Gustave Stoskopf', '34', NULL);

--------------------------
-----INSERT photographers-----
--------------------------

INSERT INTO ambre_fouvez.photographers(
	id, name)
	VALUES (0, 'I Dont No');

--------------------------
-----INSERT photos-----
--------------------------

INSERT INTO ambre_fouvez.photos(
	id, name, picture, photographer)
	VALUES (0, 'Default picture', '/src/photos/defaultPicture.png', 0);

---------------------
-----INSERT tags-----
---------------------

INSERT INTO ambre_fouvez.tags(
	id, label)
	VALUES (DEFAULT, 'Portrait');

INSERT INTO ambre_fouvez.tags(
	id, label)
	VALUES (DEFAULT, 'Artistique');

INSERT INTO ambre_fouvez.tags(
	id, label)
	VALUES (DEFAULT, 'Couple');

---------------------------
-----INSERT tags_photo-----
---------------------------

--INSERT INTO ambre_fouvez.tags_photo(
	--photo_id, tag_id)
	--VALUES (1, 1);

----------------------
-----INSERT USERS-----
----------------------

INSERT INTO ambre_fouvez.users(
	id, username, last_name, first_name, email, is_boss, registration_date, password, 
    profile_picture, address, phone_number, facebook, instagram, twitter, youtube, 
    hair_color, hair_size, eye, height, weight, first_nationality, second_nationality, shoe_size, jacket_size, pant_size, chest, bra_cup, waist_size, hip_size, neck_size, head_size)
	VALUES (0, 'clabi', 'Fouvez', 'Dorian', 'dofou1995@hotmail.com', TRUE, '2021-05-18 23:53:00', '$2a$10$qHUActFexLNl7AS/Yakyy.wsh1EDnSUbnnrMQMXK2vMi4Yof1tBeC', 
    0, NULL, NULL, NULL, NULL, NULL, NULL, 
    NULL, NULL, NULL, 183, NULL, 1, NULL, 42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO ambre_fouvez.users(
	id, username, last_name, first_name, email, is_boss, registration_date, password, 
    profile_picture, address, phone_number, facebook, instagram, twitter, youtube, 
    hair_color, hair_size, eye, height, weight, first_nationality, second_nationality, shoe_size, jacket_size, pant_size, chest, bra_cup, waist_size, hip_size, neck_size, head_size)
	VALUES (DEFAULT, 'ambre', 'Fouvez', 'Ambre', 'ambre.fouvez@gmail.com', TRUE, '2021-05-18 23:55:00', '$2a$10$44L1fxbjQ2Vqtwomy5JBLOktnYbqlEKe28eY18NLvbWwOIb0SZr5K', 
    0, 1, '06 41 80 14 05', 'https://www.facebook.com/ambre.fouvez', 'https://www.instagram.com/ambre.fouvez/', NULL, NULL, 
    1, 3, 2, 168, 50, 1, NULL, 39, 36, 36, 85, 'C', 70, 86, 29.6, 52.5);

------------------------------
-----INSERT professions-----
------------------------------

INSERT INTO ambre_fouvez.professions(
	id, label)
	VALUES (DEFAULT, 'Comédienne');

---------------------------------
-----INSERT curriculum_vitae-----
---------------------------------

INSERT INTO ambre_fouvez.curriculum_vitae(
	id, title, user_id, profession, playing_age, background_picture)
	VALUES (DEFAULT, 'CV ARTISTIQUE', 1, 1, '18 - 25', 0);

--------------------------
-----INSERT trainings-----
--------------------------

INSERT INTO ambre_fouvez.trainings(
	id, start_year, end_year, label, explanations)
	VALUES (DEFAULT, 2017, 2020, 'Formation de l`auteur au Cours Florent', 
    'avec Frédéric Haddou, Hugues Boucher, Christophe Reymond, Laurent Bellambe, Nâzim Boudjenah et Félicien Juttner');

-------------------------------------------
-----INSERT trainings_curriculum_vitae-----
-------------------------------------------

INSERT INTO ambre_fouvez.trainings_curriculum_vitae(
	curriculum_vitae, training, order_number)
	VALUES (1, 1, 1);

----------------------
-----INSERT roles-----
----------------------

INSERT INTO ambre_fouvez.roles(
	id, name)
	VALUES (DEFAULT, 'Rôle principale de Jeanne');

INSERT INTO ambre_fouvez.roles(
	id, name)
	VALUES (DEFAULT, 'Figuration');

INSERT INTO ambre_fouvez.roles(
	id, name)
	VALUES (DEFAULT, 'Rôle principale');

----------------------------
-----INSERT short_films-----
----------------------------

INSERT INTO ambre_fouvez.short_films(
	id, title, role, year)
	VALUES (DEFAULT, 'JEANNE ET JEAN', 1, 2019);

INSERT INTO ambre_fouvez.short_films(
	id, title, role, year)
	VALUES (DEFAULT, 'CHIC', 2, 2019);

INSERT INTO ambre_fouvez.short_films(
	id, title, role, year)
	VALUES (DEFAULT, 'Film institutionnel', 3, 2019);

---------------------------------------------
-----INSERT short_films_curriculum_vitae-----
---------------------------------------------

INSERT INTO ambre_fouvez.short_films_curriculum_vitae(
	curriculum_vitae, short_film, order_number)
	VALUES (1, 1, 1);

INSERT INTO ambre_fouvez.short_films_curriculum_vitae(
	curriculum_vitae, short_film, order_number)
	VALUES (1, 2, 2);

INSERT INTO ambre_fouvez.short_films_curriculum_vitae(
	curriculum_vitae, short_film, order_number)
	VALUES (1, 3, 3);

------------------------------
-----INSERT companies-----
------------------------------

INSERT INTO ambre_fouvez.companies(
	id, name)
	VALUES (DEFAULT, 'DMA');

-------------------------------------
-----INSERT companies_short_film-----
-------------------------------------

INSERT INTO ambre_fouvez.companies_short_film(
	short_film_id, company_id)
	VALUES (3, 1);

------------------------
-----INSERT cinemas-----
------------------------

INSERT INTO ambre_fouvez.cinemas(
	id, title, role, year)
	VALUES (DEFAULT, 'SUBITYA', 2, 2019);

INSERT INTO ambre_fouvez.cinemas(
	id, title, role, year)
	VALUES (DEFAULT, 'LA VIE DEVANT SOI', 2, 2020);

-----------------------------------------
-----INSERT cinemas_curriculum_vitae-----
-----------------------------------------

INSERT INTO ambre_fouvez.cinemas_curriculum_vitae(
	curriculum_vitae, cinema, order_number)
	VALUES (1, 1, 1);

INSERT INTO ambre_fouvez.cinemas_curriculum_vitae(
	curriculum_vitae, cinema, order_number)
	VALUES (1, 2, 2);

-----------------------------
-----INSERT distinctions-----
-----------------------------

INSERT INTO ambre_fouvez.distinctions(
	id, name)
	VALUES (DEFAULT, 'Sélectionné pour l`ACID à Cannes 2020 (annulé)');

-------------------------------------
-----INSERT distinctions_cinema-----
-------------------------------------

INSERT INTO ambre_fouvez.distinctions_cinema(
	cinema_id, distinction_id)
	VALUES (1, 1);

--------------------------
-----INSERT directors-----
--------------------------

INSERT INTO ambre_fouvez.directors(
	id, name)
	VALUES (DEFAULT, 'Hristo Todorov');

INSERT INTO ambre_fouvez.directors(
	id, name)
	VALUES (DEFAULT, 'Gwladys Aldric');

INSERT INTO ambre_fouvez.directors(
	id, name)
	VALUES (DEFAULT, 'Alex Cross');

INSERT INTO ambre_fouvez.directors(
	id, name)
	VALUES (DEFAULT, 'Nessim Chikhaoui');

-------------------------------------
-----INSERT directors_short_film-----
-------------------------------------

INSERT INTO ambre_fouvez.directors_short_film(
	short_film_id, director_id)
	VALUES (1, 1);

INSERT INTO ambre_fouvez.directors_short_film(
	short_film_id, director_id)
	VALUES (2, 2);

INSERT INTO ambre_fouvez.directors_short_film(
	short_film_id, director_id)
	VALUES (2, 3);

---------------------------------
-----INSERT directors_cinema-----
---------------------------------

INSERT INTO ambre_fouvez.directors_cinema(
	cinema_id, director_id)
	VALUES (1, 3);

INSERT INTO ambre_fouvez.directors_cinema(
	cinema_id, director_id)
	VALUES (2, 4);

------------------------------
-----INSERT strengths-----
------------------------------

INSERT INTO ambre_fouvez.strengths(
	id, label)
	VALUES (DEFAULT, '8 ans de danse');

INSERT INTO ambre_fouvez.strengths(
	id, label)
	VALUES (DEFAULT, 'Jeu burlesque');

INSERT INTO ambre_fouvez.strengths(
	id, label)
	VALUES (DEFAULT, 'Belge');

-------------------------------------------
-----INSERT strengths_curriculum_vitae-----
-------------------------------------------

INSERT INTO ambre_fouvez.strengths_curriculum_vitae(
	curriculum_vitae, strength, order_number)
	VALUES (1, 1, 1);

INSERT INTO ambre_fouvez.strengths_curriculum_vitae(
	curriculum_vitae, strength, order_number)
	VALUES (1, 2, 2);

INSERT INTO ambre_fouvez.strengths_curriculum_vitae(
	curriculum_vitae, strength, order_number)
	VALUES (1, 3, 3);