INSERT INTO DEELNEMER (ID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, POSTCODE, STRAAT, PLAATS, LAND, HUISNUMMER, HUISNUMMERTOEVOEGING, EMAIL, GEBOORTEDATUM, PENSIOENREKENING) VALUES
(
1,
 'D',
NULL,
 'Testachternaam',
 '1234AB',
 'Teststraat',
 'Testplaats',
 'Testland',
 1,
 NULL,
 'test@tester.test',
 '2000-01-01',
 'ABC123'
);

INSERT INTO WERKGEVER (ID, NAAM) VALUES (1, 'Testbedrijf');

INSERT INTO DIENSTVERBAND(ID,DEELNEMER_ID, WERKGEVER_ID, SALARIS, PARTTIME, INGANSDATUM, EINDDATUM) VALUES
(
 1,
 1,
 1,
 12.5,
 1,
 '2018-01-01',
 NULL
);