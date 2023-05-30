package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping("/alunos")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "alunoLayout.html");
        mv.addObject("alunos", alunoRepo.findAll());
        return mv;
    }
}
