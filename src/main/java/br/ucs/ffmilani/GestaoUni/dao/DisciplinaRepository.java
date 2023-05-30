package br.ucs.ffmilani.GestaoUni.dao;

import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer> {
    Disciplina findBySigla(String sigla);
}
