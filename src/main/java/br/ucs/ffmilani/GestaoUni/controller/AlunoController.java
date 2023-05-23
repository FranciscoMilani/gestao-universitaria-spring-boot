package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlunoController {

    @Autowired
    private AlunoJdbcDAO alunoDao;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/lista/alunos/")
    public String listaAlunos(Model model){
        model.addAttribute("alunos", alunoDao.listar());
        return "alunos";
    }
}
