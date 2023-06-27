package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Integer> {
    Curso findByNome(String nome);
    Curso findBySigla(String sigla);
}
