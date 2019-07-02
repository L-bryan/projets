----------------------
-- Créer les tables
----------------------

CREATE TABLE MEMBERS (
	id	BIGINT	NOT NULL,
	name VARCHAR(30) NOT NULL,
	firstName VARCHAR(30) NOT NULL,
	birthDate	 DATE NOT NULL,
	sex varchar(10) NOT NULL,
	email VARCHAR(30),
	tel varchar(30),
	sexPref varchar(10),
	ageMin INT,
	ageMax INT,
	friendDist INT,
	PRIMARY KEY (id)
);

CREATE TABLE HOBBIES (
	id	BIGINT	NOT NULL,
	idMember    BIGINT	NOT NULL,
	name VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE LINKS (
	id	BIGINT	NOT NULL,
	linkType VARCHAR(30) NOT NULL,
	weight INT NOT NULL,
	sender INT NOT NULL,
	receiver INT NOT NULL,
	confirmed BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE SEQUENCES (
    id      VARCHAR(50)	NOT NULL,
    sValue  numeric(10) NOT NULL,
    constraint IDSEQUENCE primary key (id)
);