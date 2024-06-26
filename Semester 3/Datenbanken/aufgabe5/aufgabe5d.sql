SELECT f.wohnungsname, COUNT(ba.ausstattungsname) AS ausstattungs_count
FROM ferienwohnung f
JOIN beinhaltete_ausstattung ba ON f.wohnungs_id = ba.wohnungs_id
GROUP BY f.wohnungsname
ORDER BY ausstattungs_count DESC
FETCH FIRST ROW WITH TIES;