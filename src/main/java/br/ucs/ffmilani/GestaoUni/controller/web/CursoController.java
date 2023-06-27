package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.model.Curso;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import br.ucs.ffmilani.GestaoUni.repository.CursoDisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.service.CadastroCursoService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CursoController {

    private RelatorioService relatorioService;
    private CadastroCursoService cadastroCursoService;

    @GetMapping("/cursos")
    public String listaCursos(Model model) {
        model.addAttribute("layout", "cursoLayout.html");
        model.addAttribute("cursos", relatorioService.listaCursos());

        return "index";
    }

    @GetMapping("/cadastro/curso")
    public ModelAndView cadastrarCurso(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "cadastrarCursoLayout.html");

        return mv;
    }

    @PostMapping("/cadastro/curso")
    public ModelAndView cadastrarCurso(ModelAndView mv,
                                       @RequestParam String nome,
                                       @RequestParam String sigla,
                                       @RequestParam Integer cargahoraria,
                                       @RequestParam String disciplinas,
                                       @RequestParam String universidade){

        List<String> disciplinaStringList = Arrays.stream(disciplinas.split(","))
                .map(String::trim)
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());

        mv.setViewName("index");
        mv.addObject("layout", "cadastrarCursoLayout.html");

        Curso curso = cadastroCursoService.converteParaEntidade(new CursoCadastroDTO(nome, sigla, cargahoraria, disciplinaStringList, universidade));
        if (curso == null){
            mv.addObject("resposta", "Erro no cadastro");
            return mv;
        }

        String out = cadastroCursoService.cadastraCurso(curso);
        mv.addObject("resposta", out);
        return mv;
    }

    @GetMapping("cursos/remover/{id}")
    public ModelAndView removeCurso(@PathVariable("id") Integer id){
        relatorioService.removeCurso(id);
        return new ModelAndView("redirect:/cursos");
    }
}
