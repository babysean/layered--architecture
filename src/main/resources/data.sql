INSERT INTO member (id, name)
SELECT 1, 'Apple'
FROM dual
WHERE NOT EXISTS(SELECT * FROM member WHERE id = 1);

INSERT INTO member (id, name)
SELECT 2, 'Banana'
FROM dual
WHERE NOT EXISTS(SELECT * FROM member WHERE id = 2);

INSERT INTO member (id, name)
SELECT 3, 'Cherry'
FROM dual
WHERE NOT EXISTS(SELECT * FROM member WHERE id = 3);
