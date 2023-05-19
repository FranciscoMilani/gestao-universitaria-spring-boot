create table aluno (
    id serial primary key,
    --version int,
    nome varchar(255) not null,
    email varchar(255) not null,
    password varchar(30) not null
);

create table universidade (
    id serial primary key,
    sigla varchar(10) not null,
    nome varchar(255) not null
);

