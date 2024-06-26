SELECT a.stadt, COUNT(*) AS wohnungs_count
FROM adresse a
JOIN ferienwohnung f ON a.adress_id = f.adress_id
GROUP BY a.stadt;