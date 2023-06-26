package br.ucs.ffmilani.GestaoUni.repository;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Integer> {
    Aluno findByEmail(String email);
}
