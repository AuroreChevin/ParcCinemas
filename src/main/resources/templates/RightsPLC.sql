-- ------------------------------------------------------------------------------
-- - Gestion des droits d'accès                                     ---
-- ------------------------------------------------------------------------------
USE ParkCinema;

-- -----------------------------------------------------------------------------
-- - Construction de la table des Users                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	username	varchar(25)		PRIMARY KEY,
	password	varchar(250),
	active		boolean
)ENGINE = InnoDB;

INSERT INTO T_Users (username, password, active) VALUES ('rory', '$2a$12$puEFCrs8eXtFHfjRjHSqDu6Ps6.MMlBK2U13kmrHjeQa57hgvyddq', '1');   -- pwd 12345
INSERT INTO T_Users (username, password, active) VALUES ('ladyhead', '$2a$12$bqgu5I3icH6D6rJWzrbwQuA9QUdZflHUS6hvRY9DJSPbFqzgbRLn2', '1'); -- pwd 67891

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table avec 2 Roles principaux                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Roles (
	role	varchar(25)		PRIMARY KEY
)ENGINE = InnoDB;

INSERT INTO T_Roles (role) VALUES ('ADMIN');
INSERT INTO T_Roles (role) VALUES ('USER');

-- -----------------------------------------------------------------------------
-- - Construction de la table des rôles par utilisateur	                     ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users_Roles (
	username	varchar(25),	
	role		varchar(25),
	PRIMARY KEY(username,role)
)ENGINE = InnoDB;
INSERT INTO T_Users_Roles (username,role) VALUES ('rory','ADMIN');
INSERT INTO T_Users_Roles (username,role) VALUES ('rory','USER');
INSERT INTO T_Users_Roles (username,role) VALUES ('ladyhead','USER');