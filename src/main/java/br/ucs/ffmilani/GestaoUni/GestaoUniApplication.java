package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.dao.*;
import br.ucs.ffmilani.GestaoUni.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@RestController
public class GestaoUniApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoUniApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(@Autowired AlunoJdbcDAO alunoDao,
                                        @Autowired AlunoRepository alunoRepo,
                                        @Autowired DisciplinaRepository disciplinaRepo,
										@Autowired CursoRepository cursoRepo,
										@Autowired UniversidadeRepository uniRepo) {
		return args -> {

			Universidade universidade = new Universidade(null, "UCS", "Universidade de Caxias do Sul");
			universidade = uniRepo.save(universidade);

			/*
			 * =============================================
			 * 				 	  CURSOS
			 * =============================================
			 */

			Curso ads = new Curso(null, "Análise e Desenvolvimento de Sistemas", 2100);
			Curso jgs = new Curso(null, "Jogos Digitais", 2100);
			Curso bio = new Curso(null, "Biomedicina", 3400);

			ads = cursoRepo.save(ads);
//			cursoRepo.save(jgs);
//			cursoRepo.save(bio);

			/*
			* =============================================
			* 					ALUNOS
			* =============================================
			*/
			List<Aluno> alunosAds = Arrays.asList(
					new Aluno(null, "Francisco Milani", "ffmilani@ucs.br", "1234fd5a"),
					new Aluno(null, "Eduardo Viezzer", "eviezzer@ucs.br", "123443DFSfb"),
					new Aluno(null, "Yago Martins", "ymartins@ucs.br", "123fdsgrewTFc"),
					new Aluno(null, "Ezequiel", "ezequiel@ucs.br", "12sFD22d"),
					new Aluno(null, "Raul Tomedi Pilotti", "rtpilotti@ucs.br", "1dsfdse")
			);

			Curso finalAds = ads;
			alunosAds.forEach(aluno -> aluno.setCurso(finalAds));

//			List<Aluno> alunosJogos = Arrays.asList(
//					new Aluno(null, "Pedro Henrique", "phcosta@ucs.br", "12dx3c", jgs),
//					new Aluno(null, "Bruno Afonso", "basilva5@ucs.br", "1GFD122vd2d", jgs),
//					new Aluno(null, "Juliano Giotti", "jgiotti@ucs.br", "1e$aavde", jgs),
//					new Aluno(null, "Gustavo Silva", "gsilva2@ucs.br", "21eaadvc", jgs),
//					new Aluno(null, "Fulano da Silva", "fdsilva8@ucs.br", "1!efg%ss", jgs)
//			);
//
//			List<Aluno> alunosBio = Arrays.asList(
//					new Aluno(null, "Laura Santos", "lssantos@ucs.br", "23213SfdjF#", bio),
//					new Aluno(null, "Luiz Augusto Fornasier Milani", "lafmilani@ucs.br", "1234fdsFFb", bio),
//					new Aluno(null, "Gabriel Ferreira", "gferreira@ucs.br", "EE123cFc1", bio),
//					new Aluno(null, "Ana Oliveira", "aoliveira@ucs.br", "123fdF", bio),
//					new Aluno(null, "Carolina Ribeiro", "cribeiro@ucs.br", "FDDF1EWd12", bio)
//			);

			alunoRepo.saveAll(alunosAds);
//			alunoRepo.saveAll(alunosJogos);
//			alunoRepo.saveAll(alunosBio);

			/*
			 * =============================================
			 * 					DISCIPLINAS
			 * =============================================
			 */

			List<Disciplina> disciplinasAds = Arrays.asList(
					new Disciplina(null, "Lógica para Computação", "FBI1234", 4, 80),
					new Disciplina(null, "Programação de Computadores I", "FBI1234", 4, 80),
					new Disciplina(null, "Programação Orientada a Objetos", "FBI1234", 4, 80),
					new Disciplina(null, "Projeto Temático I", "FBI1234", 4, 80),
					new Disciplina(null, "Laboratório de Software", "FBI1234", 4, 80),
					new Disciplina(null, "Projeto e Arquitetura de Software", "FBI1234", 2, 40),
					new Disciplina(null, "Fundamentos de Banco de Dados", "FBI1234", 4, 80),
					new Disciplina(null, "Algoritmos e Estrutura de Dados I", "FBI1234", 4, 80),
					new Disciplina(null, "Programação de Aplicações Web I", "FBI1234", 4, 80),
					new Disciplina(null, "Programação de Aplicações Web II", "FBI1234", 4, 80)
			);

			List<Disciplina> disciplinasJogos = Arrays.asList(
					new Disciplina(null, "Ilustração Digital", "JGS0000", 4, 80),
					new Disciplina(null, "Game Design", "JGS1111", 4, 80),
					new Disciplina(null, "Game Design Aplicado", "JGS2222", 4, 80),
					new Disciplina(null, "Projeto Temático: Casual Games", "JGS3333", 2, 40),
					new Disciplina(null, "Modelagem Digital", "JGS4444", 4, 80),
					new Disciplina(null, "Animação Digital", "JGS5555", 4, 40),
					new Disciplina(null, "Programação para Jogos Digitais", "JGS6666", 4, 80),
					new Disciplina(null, "Infraestrutura Tecnológica", "JGS7777", 4, 80),
					new Disciplina(null, "Projeto Temático: Jogo Desktop", "JGS8888", 2, 40),
					new Disciplina(null, "Realidade Virtual e Realidade Aumentada", "JGS9999", 4, 80)
			);

			List<Disciplina> disciplinasBio = Arrays.asList(
					new Disciplina(null, "Bioquímica Geral", "BIO0000", 4, 80),
					new Disciplina(null, "Microbiologia Geral", "BIO1111", 2, 40),
					new Disciplina(null, "Fisiologia Humana I", "BIO2222", 4, 80),
					new Disciplina(null, "Projeto Temático II", "BIO3333", 2, 40),
					new Disciplina(null, "Toxicologia", "BIO4444", 3, 60),
					new Disciplina(null, "Anatomia Humana", "BIO5555", 4, 80),
					new Disciplina(null, "Saúde Coletiva", "BIO6666", 4, 80),
					new Disciplina(null, "Virologia", "BIO7777", 2, 40),
					new Disciplina(null, "Bioquímica Clínica", "BIO8888", 3, 60),
					new Disciplina(null, "Citopatologia", "BIO9999", 3, 60)
			);



//			uniRepo.save(universidade);
//			cursoRepo.saveAll(cursos);
//
//
//			Disciplina d = disciplinaRepo.save(disciplina);
//			curso.addDisciplinas(d);
//			aluno.setCursoMatriculado(curso);
//
//
//			cursoRepo.save(curso);
//			alunoRepo.saveAll(alunos);
		};
	}
}
