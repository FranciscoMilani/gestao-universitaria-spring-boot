package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.service.DatabaseStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DadosController {

    private DatabaseStatusService dbss;

    @GetMapping("/insere-dados")
    public String testaInsereDados(){
        dbss.tentaCadastrarDados();
        return "index";
    }
}
