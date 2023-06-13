package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepo;
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private DisciplinaRepository disciplinaRepo;
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/matriculas")
    public String listaMatriculas(Model model){
        model.addAttribute("layout", "matriculaLayout.html");
        model.addAttribute("matriculas", matriculaService.listarMatriculas());
        return "index";
    }

    @GetMapping("/matricular")
    public ModelAndView matricular(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("layout", "matricularLayout.html");
        return mv;
    }

    @PostMapping(value = "/matricular")
    public String matricular(@RequestParam("aluno") String paramAluno,
                             @RequestParam("disciplina") String paramDisciplina,
                             @RequestParam("semestre") String paramSemestre,
                             Model model) {

        String resposta = matriculaService.matricularAluno(new MatriculaDTO(paramAluno, paramDisciplina, paramSemestre));
        model.addAttribute("resposta", resposta);
        model.addAttribute("layout", "matricularLayout.html");
        return "index";
    }
}
