create table universidade (
    id serial primary key,
    sigla varchar(10) not null,
    nome varchar(255) not null
);

create table curso (
    id serial primary key,
    nome varchar(255) not null,
    cargaHoraria integer not null,
    tipo varchar(30),
    universidade integer not null,
    foreign key (universidade) references universidade(id) on delete cascade
);

create table aluno (
    id serial primary key,
    nome varchar(255) not null,
    email varchar(255) not null,
    password varchar(30) not null,
    curso integer,
    foreign key (curso) references curso(id) on delete cascade
);

create table disciplina (
    id serial primary key,
    nome varchar(255) not null,
    sigla varchar(255) not null,
    creditos integer not null,
    cargaHoraria integer not null
);

create table curso_disciplina (
	curso integer,
	disciplina integer,
	foreign key (curso) references curso(id) on delete cascade,
	foreign key (disciplina) references disciplina(id) on delete cascade
);

create table matricula (
    id serial primary key,
    semestre varchar(6) not null, -- 2023/2
    aluno integer not null,
    disciplina integer not null,
    foreign key (aluno) references aluno(id) on delete cascade,
    foreign key (disciplina) references disciplina(id) on delete cascade
);