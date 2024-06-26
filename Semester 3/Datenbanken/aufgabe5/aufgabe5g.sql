SELECT f.wohnungsname
FROM ferienwohnung f
JOIN buchung b ON f.wohnungs_id = b.wohnungs_id
GROUP BY f.wohnungsname
HAVING MIN(b.bewertungssterne) = 1 AND MAX(b.bewertungssterne) = 1;