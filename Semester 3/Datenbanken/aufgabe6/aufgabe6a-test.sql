SET TRANSACTION READ WRITE;
begin

DELETE FROM buchung WHERE buchungsnummer = 3;
commit;
end;