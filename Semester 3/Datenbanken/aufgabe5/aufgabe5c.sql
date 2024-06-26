SELECT f.wohnungsname, AVG(b.bewertungssterne)
FROM ferienwohnung f
JOIN adresse a ON f.adress_id = a.adress_id
LEFT JOIN buchung b ON f.wohnungs_id = b.wohnungs_id
WHERE a.laendername = 'Spain'
GROUP BY f.wohnungsname
HAVING AVG(b.bewertungssterne) > 4;