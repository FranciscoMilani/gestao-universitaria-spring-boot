package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository disciplinaRepo;

    @GetMapping("/disciplinas")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "disciplinaLayout.html");
        mv.addObject("disciplinas", disciplinaRepo.findAll());
        return mv;
    }

}
