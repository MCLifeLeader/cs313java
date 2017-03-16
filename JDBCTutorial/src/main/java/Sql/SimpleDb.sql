create table person
(
	id SERIAL not null primary key,
	name TEXT not null,
	birthday DATE not null
);

create table childof
(
	id SERIAL not null primary key,
	parent_id INT not null,
	child_id INT not null
);


INSERT INTO person (name, birthday) VALUES
('Barack', '1961-08-04'),
('Michelle', '1964-01-17'),
('Malia', '1998-07-04'),
('Sasha', '2001-06-10'),
('Donald', '1946-06-14'),
('Melania', '1970-04-26'),
('Baron', '2006-03-20');
