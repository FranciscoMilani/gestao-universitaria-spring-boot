package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlunoController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/alunos")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("alunos", relatorioService.listaAlunos());
        mv.addObject("layout", "alunoLayout.html");
        return mv;
    }
}
