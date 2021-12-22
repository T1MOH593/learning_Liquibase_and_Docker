create table users (
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) ,
    name VARCHAR (64) ,
    company_id INT REFERENCES users(id)
);