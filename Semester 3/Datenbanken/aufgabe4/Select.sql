SELECT * FROM dbsys17.adresse WHERE ADRESS_ID = 1;
SELECT * FROM dbsys17.adresse WHERE ADRESS_ID = 2;
SELECT * FROM dbsys17.ferienwohnung w, dbsys17.beinhaltete_ausstattung baus, dbsys17.ausstattung aus
    WHERE w.WOHNUNGS_ID = 1 AND baus.WOHNUNGS_ID = w.WOHNUNGS_ID AND aus.AUSSTATTUNGSNAME = baus.AUSSTATTUNGSNAME;
SELECT * FROM dbsys17.bild b, dbsys17.ferienwohnung f WHERE f.WOHNUNGS_ID = 1 AND f.WOHNUNGS_ID = b.wohnungs_id;
SELECT attr.* FROM dbsys17.ferienwohnung f, dbsys17.attraktion attr, dbsys17.attraktions_entfernung ae
    WHERE f.WOHNUNGS_ID = 1 AND f.WOHNUNGS_ID = ae.WOHNUNGS_ID AND attr.ATTRAKTIONSNAME = ae.ATTRAKTIONSNAME;
SELECT * FROM dbsys17.buchung WHERE buchungsnummer = 1;
SELECT * FROM dbsys17.kunde WHERE mail = 'mustermann@muster.com';