ALTER TABLE ingredients
MODIFY COLUMN quantity VARCHAR(255);

ALTER TABLE recipes
MODIFY COLUMN average_cost VARCHAR(100) NOT NULL;



