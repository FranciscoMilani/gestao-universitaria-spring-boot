package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.config.DataInsertionConfiguration;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DadosController {

    private DataInsertionConfiguration dataInsert;
    private UniversidadeRepository uniRepo;

    @GetMapping("/insere-dados")
    public String testaInsereDados(){
        var values = uniRepo.findAll();

        if (values.spliterator().getExactSizeIfKnown() <= 0) {
            dataInsert.carregaDados();
        }

        return "index";
    }
}
