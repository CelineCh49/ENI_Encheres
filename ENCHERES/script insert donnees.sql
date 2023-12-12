-- Supprimer la base de donn�es si elle existe d�j�
IF DB_ID('BDD_ENCHERES') IS NOT NULL
    DROP DATABASE BDD_ENCHERES;
GO

-- Cr�er la base de donn�es BDD_ENCHERES
CREATE DATABASE BDD_ENCHERES;
GO

-- Utiliser la base de donn�es BDD_ENCHERES
USE BDD_ENCHERES;
GO

-- Cr�er la table CATEGORIES
CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
);
GO

-- Cr�er la table UTILISATEURS
CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(100) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   BIT NOT NULL
);
GO

-- Cr�er la table ARTICLES
CREATE TABLE ARTICLES (
    no_article            INTEGER IDENTITY(1,1) NOT NULL,
    nom_article           VARCHAR(30) NOT NULL,
    description           VARCHAR(300) NOT NULL,
    date_debut_encheres   DATETIME NOT NULL,
    date_fin_encheres     DATETIME NOT NULL,
    prix_initial          INTEGER,
    prix_vente            INTEGER,
    no_utilisateur_vendeur INTEGER NOT NULL,
    no_utilisateur_acheteur INTEGER,
    no_categorie          INTEGER NOT NULL,
    etat_vente            VARCHAR(30) DEFAULT 'EN_ATTENTE'
);
GO

-- Cr�er la table ENCHERES
CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME NOT NULL,
    montant_enchere  INTEGER NOT NULL
);
GO

-- Cr�er la table RETRAITS
CREATE TABLE RETRAITS (
    no_article         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
);
GO




-- Ins�rer les utilisateurs (suite)
INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
('johnsmith', 'Smith', 'John', 'johnsmith@example.com', '0123456789', 'Rue du Commerce', '75000', 'Paris', '123456', 1000, 0),
    ('laurajones', 'Jones', 'Laura', 'laurajones@example.com', '0123456789', 'Avenue des Roses', '69000', 'Lyon', '123456', 500, 0),
    ('davidwilson', 'Wilson', 'David', 'davidwilson@example.com', '0123456789', 'Boulevard du Soleil', '35000', 'Rennes', '123456', 1500, 0),
    ('williammartin', 'Martin', 'William', 'williammartin@example.com', '0123456789', 'Rue des Lilas', '44000', 'Nantes', '123456', 2000, 0),
    ('sophiethomas', 'Thomas', 'Sophie', 'sophiethomas@example.com', '0123456789', 'Rue des Champs', '69000', 'Lyon', '123456', 800, 0),
    ('emilydavis', 'Davis', 'Emily', 'emilydavis@example.com', '0123456789', 'Avenue du Paradis', '75000', 'Paris', '123456', 1200, 0),
    ('charlesmiller', 'Miller', 'Charles', 'charlesmiller@example.com', '0123456789', 'Rue des �toiles', '69000', 'Lyon', '123456', 900, 0),
    ('jessicajackson', 'Jackson', 'Jessica', 'jessicajackson@example.com', '0123456789', 'Boulevard des Lacs', '35000', 'Rennes', '123456', 700, 0),
    ('ethanwhite', 'White', 'Ethan', 'ethanwhite@example.com', '0123456789', 'Avenue de la Mer', '44000', 'Nantes', '123456', 1800, 0),
    ('olivertaylor', 'Taylor', 'Oliver', 'olivertaylor@example.com', '0123456789', 'Rue du Printemps', '69000', 'Lyon', '123456', 1600, 0),
    ('seller1', 'Seller', '1', 'seller1@example.com', '0123456789', 'Seller Street', '12345', 'Seller City', '123456', 1000, 0),
    ('seller2', 'Seller', '2', 'seller2@example.com', '0123456789', 'Seller Street', '12345', 'Seller City', '123456', 1000, 0),
    ('seller3', 'Seller', '3', 'seller3@example.com', '0123456789', 'Seller Street', '12345', 'Seller City', '123456', 1000, 0),
    ('buyer1', 'Buyer', '1', 'buyer1@example.com', '0123456789', 'Buyer Street', '54321', 'Buyer City', '123456', 1500, 0),
    ('buyer2', 'Buyer', '2', 'buyer2@example.com', '0123456789', 'Buyer Street', '54321', 'Buyer City', '123456', 1500, 0),
    ('buyer3', 'Buyer', '3', 'buyer3@example.com', '0123456789', 'Buyer Street', '54321', 'Buyer City', '123456', 1500, 0),
    ('admin', 'Admin', '', 'admin@example.com', '0123456789', 'Admin Street', '12345', 'Admin City', '123456', 2000, 1);
GO

-- Ins�rer les cat�gories
INSERT INTO CATEGORIES (libelle)
VALUES ('Informatique'), ('Ameublement'), ('V�tement'), ('Sport&Loisirs');
GO

-- Ins�rer les articles
DECLARE @vendeurId INT, @acheteurId INT;
SET @vendeurId = 11; -- ID du premier vendeur
SET @acheteurId = 13; -- ID du premier acheteur

-- Articles du vendeur 1
INSERT INTO ARTICLES (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur_vendeur, no_categorie)
VALUES
    ('Ordinateur portable', 'Ordinateur portable en excellent �tat, processeur i7, 16 Go de RAM, SSD 512 Go.', DATEADD(DAY, -3, GETDATE()), DATEADD(DAY, 3, GETDATE()), 500, @vendeurId, 1),
    ('Canap� en cuir', 'Canap� en cuir v�ritable, couleur noir, tr�s confortable.', DATEADD(DAY, -5, GETDATE()), DATEADD(DAY, 5, GETDATE()), 800, @vendeurId, 2),
    ('Chemise en coton', 'Chemise pour homme en coton, taille L, couleur blanche.', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, 2, GETDATE()), 30, @vendeurId, 3),
    ('Raquette de tennis', 'Raquette de tennis de marque Wilson, mod�le Pro Staff 97.', DATEADD(DAY, -1, GETDATE()), DATEADD(DAY, 1, GETDATE()), 100, @vendeurId, 4);

-- Articles du vendeur 2
SET @vendeurId = 12; -- ID du deuxi�me vendeur
INSERT INTO ARTICLES (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur_vendeur, no_categorie)
VALUES
    ('Console de jeu', 'Console de jeu PlayStation 5 en parfait �tat, avec 2 manettes et 3 jeux.', DATEADD(DAY, -4, GETDATE()), DATEADD(DAY, 4,GETDATE()),400, @vendeurId, 1),
    ('Table basse en bois', 'Table basse en bois massif, couleur ch�ne, dimensions 100x50 cm.', DATEADD(DAY, -6, GETDATE()), DATEADD(DAY, 6, GETDATE()), 200, @vendeurId, 2),
    ('Robe de soir�e', 'Robe de soir�e longue, taille M, couleur rouge.', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, 2, GETDATE()), 50, @vendeurId, 3),
    ('V�lo de route', 'V�lo de route en carbone, cadre taille L, mod�le haut de gamme.', DATEADD(DAY, -3, GETDATE()), DATEADD(DAY, 3, GETDATE()), 800, @vendeurId, 4);

-- Ins�rer les ench�res
DECLARE @articleId INT;
SET @articleId = 1; -- ID du premier article

-- Ench�res pour l'article 1
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (@acheteurId, @articleId, DATEADD(DAY, -2, GETDATE()), 550),
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 650),
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 700),
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 750);

SET @articleId = 2; -- ID du deuxi�me article

-- Ench�res pour l'article 2
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (@acheteurId, @articleId, DATEADD(DAY, -3, GETDATE()), 850),
    (@acheteurId, @articleId, DATEADD(DAY, -2, GETDATE()), 900),
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 950),
    (@acheteurId, @articleId, GETDATE(), 1000);

SET @articleId = 3; -- ID du troisi�me article

-- Ench�res pour l'article 3
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 35),
    (@acheteurId, @articleId, GETDATE(), 40);

SET @articleId = 4; -- ID du quatri�me article

-- Ench�res pour l'article 4
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (@acheteurId, @articleId, DATEADD(DAY, -1, GETDATE()), 120),
    (@acheteurId, @articleId, GETDATE(), 130);
-- Ins�rer les retraits
DECLARE @retraitId INT;
SET @retraitId = 1; -- ID du premier article

-- Retraits pour l'article 1
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (@retraitId, 'Rue du Commerce', '75000', 'Paris'),
    (@retraitId, 'Avenue des Roses', '69000', 'Lyon');

SET @retraitId = 2; -- ID du deuxi�me article

-- Retraits pour l'article 2
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (@retraitId, 'Boulevard du Soleil', '35000', 'Rennes'),
    (@retraitId, 'Rue des Lilas', '44000', 'Nantes');

SET @retraitId = 3; -- ID du troisi�me article

-- Retraits pour l'article 3
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (@retraitId, 'Rue des Champs', '69000', 'Lyon'),
    (@retraitId, 'Avenue du Paradis', '75000', 'Paris');

SET @retraitId = 4; -- ID du quatri�me article

-- Retraits pour l'article 4
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (@retraitId, 'Rue des �toiles', '69000', 'Lyon'),
    (@retraitId, 'Boulevard des Lacs', '35000', 'Rennes');

-- Ins�rer les ench�res
DECLARE @enchereId INT;
SET @enchereId = 1; -- ID de la premi�re ench�re

-- Ench�res pour l'article 1
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (2, @enchereId, DATEADD(HOUR, -2, GETDATE()), 600),
    (3, @enchereId, DATEADD(HOUR, -1, GETDATE()), 650),
    (4, @enchereId, DATEADD(MINUTE, -30, GETDATE()), 700);

SET @enchereId = 2; -- ID de la deuxi�me ench�re

-- Ench�res pour l'article 2
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (3, @enchereId, DATEADD(HOUR, -1, GETDATE()), 850),
    (5, @enchereId, DATEADD(MINUTE, -45, GETDATE()), 900),
    (6, @enchereId, DATEADD(MINUTE, -15, GETDATE()), 950);

SET @enchereId = 3; -- ID de la troisi�me ench�re

-- Ench�res pour l'article 3
INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
VALUES
    (4, @enchereId, DATEADD(MINUTE, -45, GETDATE()), 40),
    (6, @enchereId, DATEADD(MINUTE, -30, GETDATE()), 45),
    (7, @enchereId, DATEADD(MINUTE, -15, GETDATE()), 50);



-- Contraintes pour la table CATEGORIES
ALTER TABLE CATEGORIES
ADD CONSTRAINT categorie_pk PRIMARY KEY (no_categorie);
GO

-- Contraintes pour la table UTILISATEURS
ALTER TABLE UTILISATEURS
ADD CONSTRAINT utilisateur_pk PRIMARY KEY (no_utilisateur);
GO
ALTER TABLE UTILISATEURS
ADD CONSTRAINT UC_PSEUDO UNIQUE (pseudo);
GO
ALTER TABLE UTILISATEURS
ADD CONSTRAINT UC_EMAIL UNIQUE (email);
GO

-- Contraintes pour la table ARTICLES
ALTER TABLE ARTICLES
ADD CONSTRAINT articles_pk PRIMARY KEY (no_article);
GO
ALTER TABLE ARTICLES
ADD CONSTRAINT articles_categories_fk FOREIGN KEY (no_categorie) REFERENCES CATEGORIES (no_categorie);
GO
ALTER TABLE ARTICLES
ADD CONSTRAINT encheres_utilisateur_vendeur_fk FOREIGN KEY (no_utilisateur_vendeur) REFERENCES UTILISATEURS (no_utilisateur);
GO