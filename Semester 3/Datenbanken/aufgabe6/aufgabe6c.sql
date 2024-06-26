SELECT DISTINCT
    A.mail AS EmpfehlungFuerKunde,
    B.mail AS BewertetVonKunde,
    FW.wohnungsname,
    C.wohnungs_id
FROM
    buchung A
JOIN
    buchung B ON A.wohnungs_id = B.wohnungs_id AND A.mail <> B.mail
JOIN
    buchung C ON A.mail = C.mail AND C.wohnungs_id <> A.wohnungs_id
JOIN
    ferienwohnung FW ON FW.wohnungs_id = c.wohnungs_id
WHERE
    A.bewertungssterne = 5 AND
    B.bewertungssterne = 5 AND
    C.bewertungssterne = 5
ORDER BY
    A.mail, C.wohnungs_id, FW.wohnungsname;

UPDATE BUCHUNG SET BEWERTUNGSSTERNE = 5 WHERE BUCHUNGSNUMMER = 142 OR BUCHUNGSNUMMER = 88 OR BUCHUNGSNUMMER = 32 OR BUCHUNGSNUMMER = 27 OR BUCHUNGSNUMMER = 42;
UPDATE BUCHUNG SET BEWERTUNGSSTERNE = NULL WHERE BUCHUNGSNUMMER = 63;