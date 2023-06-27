CREATE TABLE universidade (
    id int AUTO_INCREMENT PRIMARY KEY,
    sigla varchar(10) NOT NULL,
    nome varchar(255) NOT NULL,
    UNIQUE sigla
);

CREATE TABLE curso (
    id int AUTO_INCREMENT PRIMARY KEY,
    nome varchar(255) NOT NULL,
    cargaHoraria int NOT NULL,
    sigla varchar(5) NOT NULL UNIQUE,
    universidade int NOT NULL,
    FOREIGN KEY (universidade) REFERENCES universidade(id)
);

CREATE TABLE aluno (
    id int AUTO_INCREMENT PRIMARY KEY,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(30) NOT NULL,
    curso int,
    UNIQUE (email),
    FOREIGN KEY (curso) REFERENCES curso(id)
);

CREATE TABLE disciplina (
    id int AUTO_INCREMENT PRIMARY KEY,
    nome varchar(255) NOT NULL,
    sigla varchar(255) NOT NULL,
    creditos int NOT NULL,
    cargaHoraria int NOT NULL,
    UNIQUE (sigla)
);

CREATE TABLE curso_disciplina (
    curso int,
    disciplina int,
    FOREIGN KEY (curso) REFERENCES curso(id),
    FOREIGN KEY (disciplina) REFERENCES disciplina(id)
);

CREATE TABLE matricula (
    id int AUTO_INCREMENT PRIMARY KEY,
    semestre varchar(6) NOT NULL,
    aluno int NOT NULL,
    disciplina int NOT NULL,
    UNIQUE (aluno, disciplina),
    FOREIGN KEY (aluno) REFERENCES aluno(id),
    FOREIGN KEY (disciplina) REFERENCES disciplina(id)
);