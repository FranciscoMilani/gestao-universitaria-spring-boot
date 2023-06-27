package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.config.DataInsertionConfiguration;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseStatusService {

    private UniversidadeRepository uniRepo;
    private DataInsertionConfiguration dataInsert;

    public void tentaCadastrarDados(){
        var values = uniRepo.findAll();

        if (values.spliterator().getExactSizeIfKnown() <= 0) {
            dataInsert.carregaDados();
        }
    }

}
