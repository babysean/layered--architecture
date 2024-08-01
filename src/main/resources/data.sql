/* client system 데이터 insert */
INSERT INTO `client_system` (id, name, password)
SELECT 1, 'front', '1234'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `client_system` WHERE id = 1);
/* ---------------------------------- */

/* shopping_cart 데이터 insert */
INSERT INTO `shopping_cart` (id, consumer_id, store_id, is_purchase_completed)
SELECT 1, 1, 1, false
FROM dual
WHERE NOT EXISTS(SELECT * FROM `shopping_cart` WHERE id = 1);
/* ---------------------------------- */

/* shopping_cart 데이터 insert */
INSERT INTO `shopping_cart_item` (id, shopping_cart_id, fruit_id, quantity, price)
SELECT 1, 1, 1, 10, 40000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `shopping_cart_item` WHERE id = 1);
/* ---------------------------------- */

-- user 데이터 insert --

INSERT INTO `consumer` (id, name, shopping_cart_id, age, money)
SELECT 1, '상훈', 1, 33, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 1);

INSERT INTO `consumer` (id, name)
SELECT 2, '현종', 2, 29, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 2);

INSERT INTO `consumer` (id, name)
SELECT 3, '윤하', 3, 28, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 3);

INSERT INTO `consumer` (id, name)
SELECT 4, '유경', 1, 27, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 4);

-- storeEntity 데이터 insert --

INSERT INTO `store` (id, name)
SELECT 1, '동화상회'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store` WHERE id = 1);

INSERT INTO `store` (id, name)
SELECT 2, '일렉과수원'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store` WHERE id = 2);

-- fruitEntity 데이터 insert --

INSERT INTO `fruit` (id, name)
SELECT 1, '사과'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 1);

INSERT INTO `fruit` (id, name)
SELECT 2, '바나나'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 2);

INSERT INTO `fruit` (id, name)
SELECT 3, '키위'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 3);

INSERT INTO `fruit` (id, name)
SELECT 4, '오렌지'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 4);

-- store_fruit_list 데이터 insert --

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 1, 1, 1, 4000, 100
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 1);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 2, 1, 2, 3000, 10
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 2);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 3, 1, 3, 1500, 20
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 3);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 4, 1, 4, 2000, 30
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 4);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 5, 2, 1, 4300, 50
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 5);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 6, 2, 2, 2800, 40
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 6);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 7, 2, 3, 1650, 50
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 7);

INSERT INTO `store_fruit_list` (id, store_id, fruit_id, price, quantity)
SELECT 8, 2, 4, 2150, 30
FROM dual
WHERE NOT EXISTS(SELECT * FROM `store_fruit_list` WHERE id = 8);
