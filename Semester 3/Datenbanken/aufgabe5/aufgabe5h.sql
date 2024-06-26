SELECT f.wohnungs_id, f.wohnungsname, AVG(b.bewertungssterne) AS average_rating
FROM ferienwohnung f
JOIN adresse a ON f.adress_id = a.adress_id
JOIN beinhaltete_ausstattung ba ON f.wohnungs_id = ba.wohnungs_id
LEFT JOIN buchung b ON f.wohnungs_id = b.wohnungs_id 
AND NOT (b.startdatum <= DATE '2024-05-21' AND b.enddatum >= DATE '2024-05-01')
AND NOT (b.startdatum <= DATE '2024-05-21' AND b.startdatum >= DATE '2024-05-01')
AND NOT (b.enddatum <= DATE '2024-05-21' AND b.enddatum >= DATE '2024-05-01')
AND NOT (b.startdatum <= DATE '2024-05-01' AND b.enddatum >= DATE '2024-05-21')
WHERE a.laendername = 'Spain' AND ba.ausstattungsname = 'Sauna' AND b.buchungsnummer IS NOT NULL
GROUP BY f.wohnungs_id, f.wohnungsname
ORDER BY average_rating DESC;