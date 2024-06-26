SELECT COUNT(*) AS never_booked_count
FROM ferienwohnung f
LEFT JOIN buchung b ON f.wohnungs_id = b.wohnungs_id
WHERE b.wohnungs_id IS NULL;