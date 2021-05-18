DROP SCHEMA IF EXISTS ambre_fouvez CASCADE;
CREATE SCHEMA ambre_fouvez;

CREATE TABLE ambre_fouvez.users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL UNIQUE CHECK(username <> ''),
	last_name VARCHAR(100) NOT NULL CHECK(last_name <> ''),
	first_name VARCHAR(100) NOT NULL CHECK(first_name <> ''),
	email VARCHAR(100) NOT NULL UNIQUE CHECK(email SIMILAR TO '[\w\.\/\\$é~#èà&=+*-]+@[\w\.]+\.[a-zA-Z]{2,4}'),
	is_boss BOOLEAN DEFAULT FALSE,
	registration_date TIMESTAMP NOT NULL,
	password VARCHAR(60) NOT NULL CHECK(password <> '')
);

CREATE TABLE ambre_fouvez.photos (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NULL CHECK(name <> ''),
    pictures VARCHAR NOT NULL CHECK(pictures <> ''),
    photographer_name VARCHAR NOT NULL CHECK(photographer_name <> '')
);

CREATE TABLE ambre_fouvez.tags (
	id SERIAL PRIMARY KEY,
    label VARCHAR NOT NULL CHECK(label <> '')
);

CREATE TABLE ambre_fouvez.tags_photo (
	photo_id INTEGER REFERENCES ambre_fouvez.photos(id) NOT NULL,
	tag_id INTEGER REFERENCES ambre_fouvez.tags(id) NOT NULL,
	CONSTRAINT id PRIMARY KEY(photo_id, tag_id)
);

CREATE TABLE ambre_fouvez.curriculum_vitae (
	id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL CHECK(title <> ''),
    user INTEGER REFERENCES ambre_fouvez.users(id) NOT NULL,
    profession VARCHAR(50) NOT NULL CHECK(profession <> '')
);

CREATE TABLE ambre_fouvez.trainings (
	id SERIAL PRIMARY KEY,
    start_year INTEGER NOT NULL CHECK(start_year BETWEEN YEAR(GETDATE())-120 AND YEAR(GETDATE())+1),
    end_year INTEGER NOT NULL CHECK(end_year = 0 OR (end_year BETWEEN YEAR(GETDATE())-120 AND YEAR(GETDATE())+1)),
    label VARCHAR(100) NOT NULL CHECK(label <> ''),
    explanations VARCHAR(200) NOT NULL CHECK(explanations <> '')
);

CREATE TABLE ambre_fouvez.trainings_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	training INTEGER REFERENCES ambre_fouvez.trainings(id) NOT NULL,
    CONSTRAINT id PRIMARY KEY(curriculum_vitae, training)
);

CREATE TABLE ambre_fouvez.short_films (
	id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL CHECK(title <> ''),
    director VARCHAR(100) NOT NULL CHECK(director <> ''), --A sortir car possible plusieurs ET possible pour une entreprise plutot que par.
    role VARCHAR(100) NOT NULL CHECK(role <> ''),
    year INTEGER NOT NULL CHECK(year BETWEEN YEAR(GETDATE())-120 AND YEAR(GETDATE())+1)
);

CREATE TABLE ambre_fouvez.short_films_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	short_film INTEGER REFERENCES ambre_fouvez.short_films(id) NOT NULL,
    CONSTRAINT id PRIMARY KEY(curriculum_vitae, short_film)
);

CREATE TABLE ambre_fouvez.cinemas (
	id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL CHECK(title <> ''),
    --Recompense en autre table
    director VARCHAR(100) NOT NULL CHECK(director <> ''), --A sortir car possible plusieurs ET possible pour une entreprise plutot que par.
    role VARCHAR(100) NOT NULL CHECK(role <> ''),
    year INTEGER NOT NULL CHECK(year BETWEEN YEAR(GETDATE())-120 AND YEAR(GETDATE())+1)
);

CREATE TABLE ambre_fouvez.cinemas_curriculum_vitae (
	curriculum_vitae INTEGER REFERENCES ambre_fouvez.curriculum_vitae(id) NOT NULL,
	cinema INTEGER REFERENCES ambre_fouvez.cinemas(id) NOT NULL,
    CONSTRAINT id PRIMARY KEY(curriculum_vitae, cinema)
);

CREATE TABLE ambre_fouvez.strengths (
	id SERIAL PRIMARY KEY,
);

CREATE TABLE ambre_fouvez.contacts (
	id SERIAL PRIMARY KEY,
);

--Mettre dans users ?--
CREATE TABLE ambre_fouvez.characteristics (
	id SERIAL PRIMARY KEY,
);

CREATE TABLE ambre_fouvez.mensurations (
	id SERIAL PRIMARY KEY,
);



----------------------
-----INSERT USERS-----
----------------------

INSERT INTO ambre_fouvez.users(
	id, username, last_name, first_name, email, is_boss, registration_date, password)
	VALUES (DEFAULT, 'clabi', 'Fouvez', 'Dorian', 'dofou1995@hotmail.com', TRUE, '2021-05-18 23:53:00', '$2a$10$g3anrXSvu7tEhm3l.Ychru4SKdxJt8OG047jQwSyTxVhtllZSUYVC');

INSERT INTO ambre_fouvez.users(
	id, username, last_name, first_name, email, is_boss, registration_date, password)
	VALUES (DEFAULT, 'ambre', 'Fouvez', 'Ambre', 'ambre.fouvez@gmail.com', TRUE, '2021-05-18 23:55:00', '$2a$10$g3anrXSvu7tEhm3l.Ychru4SKdxJt8OG047jQwSyTxVhtllZSUYVC');
