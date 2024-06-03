CREATE TABLE cooks (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(45) DEFAULT NULL,
  quantity varchar(45) DEFAULT NULL,
  timePreparation varchar(45) DEFAULT NULL,
  fk_user_id int NOT NULL,
  photo varchar(45) DEFAULT NULL,
  state varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  Foreign key (fk_user_id) references users (id)
);

CREATE TABLE ingredients (
  ingredient_id int NOT NULL AUTO_INCREMENT,
  ingredient longtext DEFAULT NULL,
  fk_recipe_id int NOT NULL,
  PRIMARY KEY (ingredient_id),
  Foreign key (fk_recipe_id) references cooks (id)
);

CREATE TABLE preparations (
  preparation_id int NOT NULL AUTO_INCREMENT,
  preparation longtext DEFAULT NULL,
  fk_recipe_id int NOT NULL,
  PRIMARY KEY (preparation_id),
   Foreign key (fk_recipe_id) references cooks (id)
) ;

CREATE TABLE users (
 id int  NOT NULL AUTO_INCREMENT,
 user_name VARCHAR(100) DEFAULT NULL,
 user_password VARCHAR(45) NOT NULL,
 email VARCHAR(45) DEFAULT NULL,
 PRIMARY KEY (id)
);

INSERT IN users (user_name, user_password, email ) values('luisa', 'luisbs123','luisa2@gmail.com');

select * FROM users;
SELECT id, user_name, email, user_password FROM users;

-- seleciona la recetas y los ingredientes que haya creado el usuario x
SELECT c.id, title, quantity, timePreparation, u.id AS autor_id, u.user_name AS author, photo, state FROM cooks c INNER JOIN users u ON c.fk_user_id = 5 and  u.id = 4;

SELECT *
FROM ingredients
WHERE fk_recipe_id = 14;


SELECT *
FROM preparations
WHERE fk_recipe_id = 14;

-- borrar cuando termine
SELECT *
FROM cooks c
INNER JOIN users u ON c.fk_user_id = u.id
LEFT JOIN ingredients i ON i.fk_recipe_id = c.id
LEFT JOIN preparations p ON p.fk_recipe_id = c.id;

UPDATE users set  user_password  where   email  = ?;

UPDATE cooks set title = 'Pollo', quantity = 8, timePreparation='10 horas', author = 'Panza', state='brote' where id = 1;
UPDATE preparations set preparation='Añade azucar' where preparation_id = 2 and fk_recipe_id = 2;
UPDATE ingredients set ingredient='añado tierra' where ingredient_id = 3 and fk_recipe_id = 2;


DELETE FROM preparations WHERE fk_recipe_id = 13;
DELETE FROM ingredients WHERE fk_recipe_id = 2;
DELETE FROM cooks WHERE id = 1;

-- DELETE  FROM preparations p, ingredients i,
             -- cooks c WHERE c.id = 35  and  p.fk_recipe_id = 35 and i.fk_recipe_id = 35;


