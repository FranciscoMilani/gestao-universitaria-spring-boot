package br.ucs.ffmilani.GestaoUni.dao;

import br.ucs.ffmilani.GestaoUni.model.Curso;
import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepository extends CrudRepository<Curso, Integer> {
}
