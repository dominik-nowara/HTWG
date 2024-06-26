SET TRANSACTION READ WRITE;
begin

INSERT INTO land (laendername) VALUES ('Austria');
INSERT INTO land (laendername) VALUES ('Germany');
INSERT INTO land (laendername) VALUES ('Spain');
INSERT INTO land (laendername) VALUES ('Italy');
commit;
end;