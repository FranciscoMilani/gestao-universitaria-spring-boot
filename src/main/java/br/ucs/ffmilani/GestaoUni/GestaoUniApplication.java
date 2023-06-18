package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.Swing.MainFrame;
import br.ucs.ffmilani.GestaoUni.controller.swing.MainMenuController;
import br.ucs.ffmilani.GestaoUni.controller.swing.RelatorioController;
import br.ucs.ffmilani.GestaoUni.dao.*;
import br.ucs.ffmilani.GestaoUni.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GestaoUniApplication {
	@Autowired
	public DataSource dataSource;

	public static void main(String[] args) {
		new SpringApplicationBuilder(GestaoUniApplication.class)
				.headless(false)
				.run(args);
	}

	@Bean
	@ConditionalOnProperty(prefix = "dados", value = "cadastro")
	ApplicationRunner applicationRunner(@Autowired AlunoRepository alunoRepo,
                                        @Autowired DisciplinaRepository disciplinaRepo,
										@Autowired CursoRepository cursoRepo,
										@Autowired UniversidadeRepository uniRepo,
										@Autowired MatriculaRepository matriculaRepo) {
		return args -> {

			Universidade universidade = new Universidade(null, "UCS", "Universidade de Caxias do Sul");
			universidade = uniRepo.save(universidade);

			/*
			 * =============================================
			 * 				 	  CURSOS
			 * =============================================
			 */

			Curso cursoAds = new Curso(null, "Análise e Desenvolvimento de Sistemas", 2100);
			Curso cursoJgs = new Curso(null, "Jogos Digitais", 2100);
			Curso cursoBio = new Curso(null, "Biomedicina", 3400);

			/*
			 * =============================================
			 * 					DISCIPLINAS
			 * =============================================
			 */

			List<Disciplina> disciplinasAds = Arrays.asList(
					new Disciplina(null, "Lógica para Computação", "ADS1111", 4, 80),
					new Disciplina(null, "Programação de Computadores I", "ADS2222", 4, 80),
					new Disciplina(null, "Programação Orientada a Objetos", "ADS3333", 4, 80),
					new Disciplina(null, "Projeto Temático I", "ADS4444", 4, 80),
					new Disciplina(null, "Laboratório de Software", "ADS5555", 4, 80),
					new Disciplina(null, "Projeto e Arquitetura de Software", "ADS6666", 2, 40),
					new Disciplina(null, "Fundamentos de Banco de Dados", "ADS7777", 4, 80),
					new Disciplina(null, "Algoritmos e Estrutura de Dados I", "ADS8888", 4, 80),
					new Disciplina(null, "Programação de Aplicações Web I", "ADS9999", 4, 80),
					new Disciplina(null, "Programação de Aplicações Web II", "ADS0000", 4, 80)
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

			disciplinasAds.forEach(disciplina -> {
				Disciplina d = disciplinaRepo.save(disciplina);
				cursoAds.addDisciplinas(d);
			});

            disciplinasJogos.forEach(disciplina -> {
                Disciplina d = disciplinaRepo.save(disciplina);
                cursoJgs.addDisciplinas(d);
            });

            disciplinasBio.forEach(disciplina -> {
                Disciplina d = disciplinaRepo.save(disciplina);
                cursoBio.addDisciplinas(d);
            });

			Curso ads = cursoRepo.save(cursoAds);
			Curso jgs = cursoRepo.save(cursoJgs);
			Curso bio = cursoRepo.save(cursoBio);


			/*
			* =============================================
			* 					ALUNOS
			* =============================================
			*/
			List<Aluno> alunosAds = Arrays.asList(
					new Aluno(null, "Francisco Milani", "ffmilani@ucs.br", "1234fd5a", AggregateReference.to(ads.getId())),
					new Aluno(null, "Eduardo Viezzer", "eviezzer@ucs.br", "123443DFSfb", AggregateReference.to(ads.getId())),
					new Aluno(null, "Yago Martins", "ymartins@ucs.br", "123fdsgrewTFc", AggregateReference.to(ads.getId())),
					new Aluno(null, "Ezequiel", "ezequiel@ucs.br", "12sFD22d", AggregateReference.to(ads.getId())),
					new Aluno(null, "Raul Tomedi Pilotti", "rtpilotti@ucs.br", "1dsfdse", AggregateReference.to(ads.getId()))
			);

			List<Aluno> alunosJogos = Arrays.asList(
					new Aluno(null, "Pedro Henrique", "phcosta@ucs.br", "12dx3c", AggregateReference.to(jgs.getId())),
					new Aluno(null, "Bruno Afonso", "basilva5@ucs.br", "1GFD122vd2d", AggregateReference.to(jgs.getId())),
					new Aluno(null, "Juliano Giotti", "jgiotti@ucs.br", "1e$aavde", AggregateReference.to(jgs.getId())),
					new Aluno(null, "Gustavo Silva", "gsilva2@ucs.br", "21eaadvc", AggregateReference.to(jgs.getId())),
					new Aluno(null, "Fulano da Silva", "fdsilva8@ucs.br", "1!efg%ss", AggregateReference.to(jgs.getId()))
			);

			List<Aluno> alunosBio = Arrays.asList(
					new Aluno(null, "Laura Santos", "lssantos@ucs.br", "23213SfdjF#", AggregateReference.to(bio.getId())),
					new Aluno(null, "Luiz Augusto Fornasier Milani", "lafmilani@ucs.br", "1234fdsFFb", AggregateReference.to(bio.getId())),
					new Aluno(null, "Gabriel Ferreira", "gferreira@ucs.br", "EE123cFc1", AggregateReference.to(bio.getId())),
					new Aluno(null, "Ana Oliveira", "aoliveira@ucs.br", "123fdF", AggregateReference.to(bio.getId())),
					new Aluno(null, "Carolina Ribeiro", "cribeiro@ucs.br", "FDDF1EWd12", AggregateReference.to(bio.getId()))
			);

			alunoRepo.saveAll(alunosAds);
			alunoRepo.saveAll(alunosJogos);
			alunoRepo.saveAll(alunosBio);

			/*
			 * =============================================
			 * 				    MATRICULAS
			 * =============================================
			 */

			Matricula m1 = new Matricula(null,
					AggregateReference.to(alunoRepo.findByEmail("ffmilani@ucs.br").getId()),
					AggregateReference.to(disciplinaRepo.findBySigla("ADS1111").getId()), "2023/2");

			Matricula m2 = new Matricula(null,
					AggregateReference.to(alunoRepo.findByEmail("ffmilani@ucs.br").getId()),
					AggregateReference.to(disciplinaRepo.findBySigla("ADS2222").getId()), "2023/2");

			Matricula m3 = new Matricula(null,
					AggregateReference.to(alunoRepo.findByEmail("ffmilani@ucs.br").getId()),
					AggregateReference.to(disciplinaRepo.findBySigla("ADS3333").getId()), "2023/2");

			matriculaRepo.save(m1);
			matriculaRepo.save(m2);
			matriculaRepo.save(m3);
		};
	}

	@Bean(name = "dbms")
	public String dbms(){
		try {
			return dataSource.getConnection().getMetaData().getDatabaseProductName();
		} catch (Exception e) {
			return "?";
		}
	}

//	@Bean
//	CommandLineRunner runner(@Autowired MainFrame frame) {
//		return args -> SwingUtilities.invokeLater(() -> new Window(frame));
//	}

	@Bean
	CommandLineRunner runner(@Autowired MainMenuController controller) {
		return args -> SwingUtilities.invokeLater(() -> controller.prepareAndOpen());
	}
}