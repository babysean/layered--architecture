/* ------------------ client system 데이터 insert ----------------- */
INSERT INTO `client_system` (name, password)
SELECT 'GREENNET', '1234'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `client_system` WHERE name = 'GREENNET');
/* -------------------------------------------------------------- */
