DROP TABLE IF EXISTS TYP_JEDNOSTKI CASCADE
;
DROP TABLE IF EXISTS REPREZENTANT CASCADE
;
DROP TABLE IF EXISTS JEDNOSTKA_ORGANIZACYJNA CASCADE
;
DROP TABLE IF EXISTS TYP_ZADANIA CASCADE
;
DROP TABLE IF EXISTS ZADANIE CASCADE
;
DROP TABLE IF EXISTS PANSTWO CASCADE
;
DROP TABLE IF EXISTS URZAD_SKARBOWY CASCADE
;
DROP TABLE IF EXISTS STATUS_PRACOWNIKA CASCADE
;
DROP TABLE IF EXISTS PRACOWNIK CASCADE
;
DROP TABLE IF EXISTS ADRES CASCADE
;
DROP TABLE IF EXISTS TYP_UMOWY CASCADE
;
DROP TABLE IF EXISTS PLATNOSC CASCADE
;
DROP TABLE IF EXISTS UMOWA CASCADE
;
DROP TABLE IF EXISTS RACHUNEK CASCADE
;

CREATE TABLE TYP_JEDNOSTKI
(
	nazwa 		VARCHAR(50) NOT NULL,
	typ_nadrzedny 	VARCHAR(50),
	PRIMARY KEY(nazwa)
)
;

ALTER TABLE TYP_JEDNOSTKI
ADD FOREIGN KEY(typ_nadrzedny) REFERENCES TYP_JEDNOSTKI(nazwa)
;

CREATE TABLE REPREZENTANT
(
	nazwa		VARCHAR(100) NOT NULL,
	biernik		VARCHAR(100) NOT NULL,
	PRIMARY KEY(nazwa)
)
;

CREATE TABLE JEDNOSTKA_ORGANIZACYJNA
(
	nazwa		VARCHAR(50) NOT NULL,
	typ_jednostki	VARCHAR(50) NOT NULL,
	jednostka_nadrzedna	VARCHAR(50),
	reprezentant	VARCHAR(100) NOT NULL,
	PRIMARY KEY(nazwa),
	FOREIGN KEY(reprezentant) REFERENCES REPREZENTANT(nazwa),
	FOREIGN KEY(typ_jednostki) REFERENCES TYP_JEDNOSTKI(nazwa)
)
;

ALTER TABLE JEDNOSTKA_ORGANIZACYJNA
ADD FOREIGN KEY(jednostka_nadzrzedna) REFERENCES JEDNOSTKA_ORGANIZACYJNA(nazwa)
;

CREATE TABLE TYP_ZADANIA
(
	nazwa		VARCHAR(50) NOT NULL,
	jednostka_organizacyjna	VARCHAR(50) NOT NULL,
	PRIMARY KEY(nazwa, jednostka_organizacyjna),
	FOREIGN KEY(jednostka_organizacyjna) REFERENCES JEDNOSTKA_ORGANIZACYJNA(nazwa)
)
;

CREATE TABLE ZADANIE
(
	id	INTEGER NOT NULL,
	nazwa		VARCHAR(50) NOT NULL,
	typ_zadania	VARCHAR(50) NOT NULL,
	jednostka_organizacyjna	VARCHAR(50) NOT NULL,
	opis		VARCHAR(512),
	budzet		DECIMAL(10,2),
	data_rozpoczecia	DATE,
	data_zakonczenia	DATE,
	rozliczone	BOOLEAN,
	PRIMARY KEY(id),
	FOREIGN KEY(typ_zadania, jednostka_organizacyjna) REFERENCES TYP_ZADANIA(nazwa, jednostka_organizacyjna),
	FOREIGN KEY(jednostka_organizacyjna) REFERENCES JEDNOSTKA_ORGANIZACYJNA(nazwa)
)
;

CREATE TABLE PANSTWO
(
	kod	VARCHAR(2) NOT NULL,
	nazwa	VARCHAR(50) NOT NULL,
	obywatelstwo	VARCHAR(50) NOT NULL,
	PRIMARY KEY(kod)
)
;

CREATE TABLE URZAD_SKARBOWY
(
	nazwa	VARCHAR(50) NOT NULL,
	PRIMARY KEY(nazwa)
)
;

CREATE TABLE SKLADKA 
(
	nazwa	VARCHAR(50) NOT NULL,
	PRIMARY KEY(nazwa)
)
;

CREATE TABLE SKLADKA_PRACOWNIKA
(
	status_pracownika	VARCHAR(50) NOT NULL,
	nazwa_skladki	VARCHAR(50) NOT NULL,
	PRIMARY KEY(status_pracownika, nazwa_skladki),
	FOREIGN KEY(status_pracownika) REFERENCES STATUS_PRACOWNIKA(nazwa),
	FOREIGN KEY(nazwa_skladki) REFERENCES SKLADKA(nazwa)
)
;

CREATE TABLE STATUS_PRACOWNIKA
(
	nazwa	VARCHAR(50) NOT NULL,
	opis	VARCHAR(200),
	PRIMARY KEY(nazwa)
)
;


CREATE TABLE PRACOWNIK
(
	id	INTEGER NOT NULL,
	nazwisko VARCHAR(50) NOT NULL,
	pierwsze_imie	VARCHAR(50) NOT NULL,
	imiona_pozostale	VARCHAR(50),
	plec	VARCHAR(1) NOT NULL,
	data_urodzenia	DATE NOT NULL,
	miejsce_urodzenia VARCHAR(50) NOT NULL,
	obywatelstwo	VARCHAR(2) NOT NULL,
	urzad_skarbowy	VARCHAR(50),
	pesel	DECIMAL(11),
	nip	DECIMAL(10),
	nr_dok_tozsamosci	VARCHAR(50) NOT NULL,
	typ_dok_tozsamosci	VARCHAR(20) NOT NULL,
	nr_konta	VARCHAR(50),
	status		VARCHAR(50) NOT NULL,
	dobrowolne_ub_chorobowe BOOLEAN,
	PRIMARY KEY(id),
	FOREIGN KEY(obywatelstwo) REFERENCES PANSTWO(kod),
	FOREIGN KEY(urzad_skarbowy) REFERENCES URZAD_SKARBOWY(nazwa),
	FOREIGN KEY(status) REFERENCES STATUS_PRACOWNIKA(nazwa),
	CHECK (plec IN ('K', 'M')),
	CHECK (typ_dok_tozsamosci IN ('dowod_osobisty', 'paszport'))
)
;

CREATE TABLE ADRES
(
	id	INTEGER NOT NULL,
	typ_adresu	VARCHAR(50),
	pracownik	INTEGER,
	urzad_skarbowy	VARCHAR(50),
	miejscowowsc	VARCHAR(50) NOT NULL,
	ulica	VARCHAR(50),
	nr_domu	INTEGER NOT NULL,
	nr_mieszkania	INTEGER,
	kod_pocztowy	VARCHAR(10) NOT NULL,
	poczta	VARCHAR(50) NOT NULL,
	panstwo	VARCHAR(2) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(panstwo) REFERENCES PANSTWO(kod),
	FOREIGN KEY(pracownik) REFERENCES PRACOWNIK(id),
	FOREIGN KEY(urzad_skarbowy) REFERENCES URZAD_SKARBOWY(nazwa),
	CHECK (typ_adresu in ('w_celach_pdatkowych', 'korespondencyjny', 'urzedu_skarbowego'))
)
;

CREATE TABLE TYP_UMOWY
(
	nazwa	VARCHAR(50) NOT NULL,
	PRIMARY KEY(nazwa)
)
;

CREATE TABLE PLATNOSC
(
	nazwa VARCHAR(50) NOT NULL,
	dni	INTEGER,
	miesiace	INTEGER,
	PRIMARY KEY(nazwa)
)
;

CREATE TABLE UMOWA
(
	nr_umowy	VARCHAR(50) NOT NULL,
	typ_umowy	VARCHAR(50) NOT NULL,
	pracownik	INTEGER	NOT NULL,
	zadanie	INTEGER NOT NULL,
	platnosc VARCHAR(50) NOT NULL,
	data_zawarcia	DATE NOT NULL,
	data_rozpoczecia	DATE NOT NULL,
	wynagrodzenie	DECIMAL(10,2),
	wykonywana_u_zleceniodawcy	BOOLEAN,
	PRIMARY KEY(nr_umowy),
	FOREIGN KEY(pracownik) REFERENCES PRACOWNIK(id),
	FOREIGN KEY(zadanie) REFERENCES ZADANIE(id),
	FOREIGN KEY(typ_umowy) REFERENCES TYP_UMOWY(nazwa),
	FOREIGN KEY(platnosc) REFERENCES PLATNOSC(nazwa)
)
;

CREATE TABLE RACHUNEK
(
	nr_umowy 	VARCHAR(50) NOT NULL,
	nr_rachunku	INTEGER NOT NULL,
	kwota	DECIMAL(10,2) NOT NULL,
	data_wytawienia	DATE NOT NULL,
	PRIMARY KEY(nr_rachunku, nr_umowy),
	FOREIGN KEY(nr_umowy) REFERENCES UMOWA(nr_umowy)
)
;
