package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@RestController
public class GestaoUniApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoUniApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(@Autowired AlunoJdbcDAO alunoDao, @Autowired AlunoRepository alunoRepo){
		return args -> {
			List<Aluno> alunos = new ArrayList<>();

			alunoRepo.findAll()
					.iterator()
					.forEachRemaining(alunos::add);

			Aluno aluno = new Aluno(null, "Francisco Milani", "ffmilani@ucs.br", "123");
			alunoDao.criar(aluno);
			//alunos.save(new Aluno(null, "Francisco Milani", "ffmilani@ucs,br", "123"));
		};
	}
}
