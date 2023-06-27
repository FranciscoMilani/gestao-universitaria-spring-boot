package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroDisciplinaService {

    private DisciplinaRepository disciplinaRepo;

    public String cadastraDisciplina(Disciplina disciplina){
        if (disciplina == null){
            return "Erro ao cadastrar";
        }

        try {
            disciplinaRepo.save(disciplina);
            return "Cadastrado";
        } catch (Exception e){
            return "Erro ao cadastrar";
        }
    }
}
