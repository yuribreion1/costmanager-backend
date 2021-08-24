-- CREATE DATABASE
CREATE DATABASE COSTMANAGER;

-- CREATE SCHEMA
CREATE SCHEMA FINANCE;

--accounts payable/receivable entry

CREATE TABLE FINANCE.USER (
    id BIGSERIAL not null primary key ,
    name character varying(150),
    email character varying(100),
    password character varying(64),
    create_date date default now()
);

CREATE TABLE FINANCE.ENTRY (
    id bigserial NOT NULL PRIMARY KEY ,
    description character varying(100) not null,
    month integer not null,
    year integer not null,
    value numeric(16,2) not null,
    type character varying(30) check ( type in ('payable', 'receivable') ) not null,
    status character varying(20) check ( status in ('pending','canceled','completed') ) not null,
    id_user bigint references FINANCE.USER (id) not null,
    create_date date default now()
);