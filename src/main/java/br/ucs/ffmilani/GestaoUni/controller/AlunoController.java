package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoController {

    @Autowired
    private AlunoJdbcDAO alunoDao;

    @GetMapping("/lista/alunos/")
    public String listaAlunos(Model model){
        model.addAttribute("alunos", alunoDao.listar());
        return "alunos";
    }
}
