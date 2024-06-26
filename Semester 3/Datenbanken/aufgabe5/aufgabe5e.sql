SELECT a.laendername, COALESCE(COUNT(b.buchungsnummer), 0) AS reservation_count
FROM land l
LEFT JOIN adresse a ON l.laendername = a.laendername
LEFT JOIN ferienwohnung f ON a.adress_id = f.adress_id
LEFT JOIN buchung b ON f.wohnungs_id = b.wohnungs_id
GROUP BY a.laendername
ORDER BY reservation_count DESC;