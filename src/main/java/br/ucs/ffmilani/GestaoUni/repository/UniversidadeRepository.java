package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.Universidade;
import org.springframework.data.repository.CrudRepository;

public interface UniversidadeRepository extends CrudRepository<Universidade, Integer> {
    Universidade findBySigla(String sigla);
}
