package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class UniversidadeController {

    private UniversidadeRepository uniRepo;
    private RelatorioService relatorioService;

    @GetMapping("/universidades")
    public ModelAndView listaUniversidades(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("universidades", uniRepo.findAll());
        mv.addObject("layout", "universidadeLayout.html");
        return mv;
    }

    @GetMapping("universidades/remover/{id}")
    public ModelAndView removeUniversidade(@PathVariable("id") Integer id){
        relatorioService.removeUniversidade(id);
        return new ModelAndView("redirect:/universidades");
    }
}
