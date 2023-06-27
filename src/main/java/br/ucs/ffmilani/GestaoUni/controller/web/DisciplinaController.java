package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.service.CadastroDisciplinaService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class DisciplinaController {
    private RelatorioService relatorioService;
    private CadastroDisciplinaService cadastroService;

    @GetMapping("/disciplinas")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "disciplinaLayout.html");
        mv.addObject("disciplinas", relatorioService.listaDisciplinas());
        return mv;
    }

    @GetMapping("/cadastro/disciplina")
    public ModelAndView cadastraAluno(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "cadastrarDisciplinaLayout.html");

        return mv;
    }

    @PostMapping("/cadastro/disciplina")
    public ModelAndView cadastrarAluno(ModelAndView mv,
                                       @RequestParam String nome,
                                       @RequestParam String sigla,
                                       @RequestParam Integer creditos,
                                       @RequestParam Integer cargahoraria){
        mv.setViewName("index");
        mv.addObject("layout", "cadastrarDisciplinaLayout.html");
        Disciplina disc = new Disciplina(null, nome, sigla, creditos, cargahoraria);
        String out = cadastroService.cadastraDisciplina(disc);
        mv.addObject("resposta", out);
        return mv;
    }

    @GetMapping("disciplinas/remover/{id}")
    public ModelAndView removeDisciplina(@PathVariable("id") Integer id){
        relatorioService.removeDisciplina(id);
        return new ModelAndView("redirect:/disciplinas");
    }
}
