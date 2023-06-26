package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.Matricula;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatriculaRepository extends CrudRepository<Matricula, Integer> {
    List<Matricula> findByAlunoAndSemestre(Integer aluno, String semestre);
}
