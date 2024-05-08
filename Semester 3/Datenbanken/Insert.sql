SET TRANSACTION READ WRITE;
begin

INSERT INTO land (laendername) VALUES ('Deutschland');
INSERT INTO adresse (adress_id, strasse, hausnummer, postleitzahl, stadt, laendername) 
    VALUES (1, 'Max Mustermanstraße', '1', 78452, 'Musterhausen', 'Deutschland');
INSERT INTO adresse (adress_id, strasse, hausnummer, postleitzahl, stadt, laendername) 
    VALUES (2, 'Max Musterwald', '5a', 62422, 'Musterwalden', 'Deutschland');

INSERT INTO ferienwohnung (wohnungsname, zimmer, groesse, preis, wohnungs_id, adress_id)  
    VALUES ('Tolle Waldhütte', 3, 4.5, 4300, 1, 2);
INSERT INTO ausstattung (ausstattungsname) VALUES ('Sauna');
INSERT INTO beinhaltete_ausstattung (wohnungs_id, ausstattungsname) VALUES (1, 'Sauna');
INSERT INTO bild (bildlink, wohnungs_id)  
    VALUES ('https://bild.com', 1);

INSERT INTO attraktion (attraktionsname, attraktionsbeschreibung) 
    VALUES ('Turm der Türme', 'Dies ist einfach nur ein Turm');
INSERT INTO attraktion (attraktionsname) 
    VALUES ('Super friedlicher See');
INSERT INTO attraktions_entfernung (wohnungs_id, attraktionsname, kilometer) 
    VALUES (1, 'Turm der Türme', 4);
INSERT INTO attraktions_entfernung (wohnungs_id, attraktionsname, kilometer) 
    VALUES (1, 'Super friedlicher See', 2);

INSERT INTO kunde (mail, passwort, kundenname, iban, adress_id)
    VALUES ('mustermann@muster.com', 'Passwort1', 'Max Mustermann', '2619215573829842799350', 1);

INSERT INTO buchung (buchungsnummer, startdatum, enddatum, rechnungsnummer, rechnungsdatum, betrag, wohnungs_id, mail)
    VALUES (
        1, 
        TO_DATE('2024/05/03 21:00:00', 'yyyy/mm/dd hh24:mi:ss'), 
        TO_DATE('2024/05/10 8:30:00', 'yyyy/mm/dd hh24:mi:ss'),
        1,
        TO_DATE('2024/05/12 12:00:00', 'yyyy/mm/dd hh24:mi:ss'),
        600,
        1,
        'mustermann@muster.com'
    );

INSERT INTO anzahlung (anzahlungs_id, geldbetrag, zahlungsdatum, buchungsnummer)
    VALUES (
        1, 
        200, 
        TO_DATE('2024/05/14 21:22:00', 'yyyy/mm/dd hh24:mi:ss'),
        1
    );

commit;

end;