package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import org.springframework.data.repository.CrudRepository;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer> {
    Disciplina findBySigla(String sigla);
}
