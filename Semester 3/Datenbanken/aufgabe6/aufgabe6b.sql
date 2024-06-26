CREATE OR REPLACE VIEW Kundenstatistik AS
SELECT 
    k.mail AS Kundenmail,
    COUNT(DISTINCT b.buchungsnummer) AS AnzahlBuchungen,
    COUNT(DISTINCT s.buchungsnummer) AS AnzahlStornierungen,
    COALESCE(SUM(a.geldbetrag), 0) AS SummeAllerZahlungen
FROM 
    kunde k
LEFT JOIN 
    buchung b ON k.mail = b.mail
LEFT JOIN 
    stornierte_buchung s ON k.mail = s.mail
LEFT JOIN 
    anzahlung a ON b.buchungsnummer = a.buchungsnummer
GROUP BY 
    k.mail;