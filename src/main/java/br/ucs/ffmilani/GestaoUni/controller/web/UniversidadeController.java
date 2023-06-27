package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.model.DTO.UniversidadeCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Universidade;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import br.ucs.ffmilani.GestaoUni.service.CadastroUniversidadeService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UniversidadeController {

    private UniversidadeRepository uniRepo;
    private RelatorioService relatorioService;
    private CadastroUniversidadeService cadastroService;

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

//    @GetMapping("/cadastro/universidade")
//    public ModelAndView cadastraAluno(ModelAndView mv){
//        mv.setViewName("index");
//        mv.addObject("layout", "cadastrarUniversidadeLayout.html");
//        return mv;
//    }
//
//    @PostMapping("/cadastro/universidade")
//    public ModelAndView cadastrarAluno(ModelAndView mv,
//                                       @RequestParam String sigla,
//                                       @RequestParam String nome,
//                                       @RequestParam String cursos){
//        mv.setViewName("index");
//        mv.addObject("layout", "cadastrarUniversidadeLayout.html");
//
//        List<String> cursoStringList = Arrays.stream(cursos.split(","))
//                .map(String::trim)
//                .filter(str -> !str.isEmpty())
//                .collect(Collectors.toList());
//
//        UniversidadeCadastroDTO dto = new UniversidadeCadastroDTO(sigla, nome, cursoStringList);
//        Universidade uni = cadastroService.converteParaEntidade(dto);
//        String out = cadastroService.cadastraUniversidade(uni);
//        mv.addObject("resposta", out);
//        return mv;
//    }
}
