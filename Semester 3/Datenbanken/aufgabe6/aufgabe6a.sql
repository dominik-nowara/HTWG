CREATE TABLE stornierte_buchung (
    buchungsnummer NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    startdatum DATE,
    enddatum DATE,
    stornodatum DATE,
    rechnungsnummer INT,
    rechnungsdatum DATE,
    betrag FLOAT,
    bewertungsdatum DATE,
    bewertungssterne INT CHECK (bewertungssterne BETWEEN 1 AND 5),
    wohnungs_id NUMBER,
    mail VARCHAR2(35),
    CONSTRAINT sb_buchungsnummer_pk PRIMARY KEY (buchungsnummer),
    CONSTRAINT sb_wohnungs_buchungs_fk FOREIGN KEY (wohnungs_id) 
        REFERENCES ferienwohnung (wohnungs_id),
    CONSTRAINT sb_mail_buchungs_fk FOREIGN KEY (mail) 
        REFERENCES kunde (mail),
    CONSTRAINT sb_start_vor_ende CHECK (startdatum < enddatum),
    CONSTRAINT sb_bewertung_nach_ende CHECK (bewertungsdatum > enddatum)
);

CREATE OR REPLACE TRIGGER trg_buchung_stornieren
BEFORE DELETE ON buchung
FOR EACH ROW
BEGIN
    INSERT INTO stornierte_buchung (
        buchungsnummer, startdatum, enddatum, stornodatum, rechnungsnummer, rechnungsdatum, 
        betrag, bewertungsdatum, bewertungssterne, wohnungs_id, mail
    ) VALUES (
        :OLD.buchungsnummer, :OLD.startdatum, :OLD.enddatum, SYSDATE, :OLD.rechnungsnummer, 
        :OLD.rechnungsdatum, :OLD.betrag, :OLD.bewertungsdatum, :OLD.bewertungssterne, 
        :OLD.wohnungs_id, :OLD.mail
    );
END;


DELETE FROM buchung WHERE buchungsnummer = 4;