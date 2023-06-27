package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoDisciplinaRepository extends CrudRepository<DisciplinaRef, Integer> {
    @Query("SELECT COUNT(*) FROM curso_disciplina WHERE disciplina = :disciplina")
    int countByDisciplina(Integer disciplina);
}
