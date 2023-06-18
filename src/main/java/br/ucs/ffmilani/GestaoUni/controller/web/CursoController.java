package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CursoController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/cursos")
    public String listaCursos(Model model) {
        model.addAttribute("layout", "cursoLayout.html");
        model.addAttribute("cursos", relatorioService.listaCursos());

        return "index";
    }
}
