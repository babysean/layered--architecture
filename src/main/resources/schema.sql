CREATE TABLE IF NOT EXISTS `user`
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `store`
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `fruit`
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `store_fruit_list`
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    store_id BIGINT,
    fruit_id BIGINT,
    price    BIGINT,
    quantity BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (store_id) REFERENCES `store` (id) ON UPDATE CASCADE,
    FOREIGN KEY (fruit_id) REFERENCES `fruit` (id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `shopping_cart`
(
    id                    BIGINT NOT NULL AUTO_INCREMENT,
    user_id               BIGINT,
    store_id              BIGINT,
    is_purchase_completed BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES `user` (id) ON UPDATE CASCADE,
    FOREIGN KEY (store_id) REFERENCES `store` (id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `shopping_cart_item`
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    shopping_cart_id BIGINT,
    fruit_id         BIGINT,
    quantity         BIGINT,
    price            BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (shopping_cart_id) REFERENCES `shopping_cart` (id) ON UPDATE CASCADE,
    FOREIGN KEY (fruit_id) REFERENCES `fruit` (id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `receipt`
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    user_id          BIGINT,
    store_id         BIGINT,
    shopping_cart_id BIGINT,
    total_price      BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES `user` (id) ON UPDATE CASCADE,
    FOREIGN KEY (store_id) REFERENCES `store` (id) ON UPDATE CASCADE,
    FOREIGN KEY (shopping_cart_id) REFERENCES `shopping_cart` (id) ON UPDATE CASCADE
);
