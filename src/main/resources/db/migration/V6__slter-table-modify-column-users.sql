ALTER TABLE users
MODIFY COLUMN login varchar(250) not null unique;
