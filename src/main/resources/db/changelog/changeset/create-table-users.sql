create table users (
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) ,
    name VARCHAR (64) ,
    company_id BIGINT REFERENCES users(id)
);