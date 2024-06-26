SELECT k.mail
FROM buchung b
JOIN kunde k ON b.mail = k.mail
GROUP BY k.mail, b.wohnungs_id
HAVING COUNT(*) > 1;