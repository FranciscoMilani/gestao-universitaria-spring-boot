package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class UniversidadeController {

    private UniversidadeRepository uniRepo;

    @GetMapping("/universidades")
    public ModelAndView listaUniversidades(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("universidades", uniRepo.findAll());
        mv.addObject("layout", "universidadeLayout.html");
        return mv;
    }
}
