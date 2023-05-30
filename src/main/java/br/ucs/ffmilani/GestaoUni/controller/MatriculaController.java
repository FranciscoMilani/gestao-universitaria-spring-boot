package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepo;
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private DisciplinaRepository disciplinaRepo;

    @GetMapping("/matriculas")
    public String listaMatriculas(Model model){
        List<MatriculaDTO> mats = new ArrayList<>();

        matriculaRepo.findAll().forEach( m -> {
                Optional<Aluno> a = alunoRepo.findById(m.getAluno().getId());
                Optional<Disciplina> d = disciplinaRepo.findById(m.getDisciplina().getId());

                mats.add(new MatriculaDTO(a.get().getNome(), d.get().getNome(), m.getSemestre()));
        });

        model.addAttribute("layout", "matriculaLayout.html");
        model.addAttribute("matriculas", mats);
        return "index";
    }

    @GetMapping("/matricular")
    public ModelAndView matricular(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("layout", "matricularLayout.html");
        return mv;
    }

    @PostMapping("/matricular")
    public String matricular(@RequestParam("aluno") String paramAluno,
                             @RequestParam("disciplina") String paramDisciplina,
                             @RequestParam("semestre") String paramSemestre) {

        Optional<Aluno> aluno;
        Optional<Disciplina> disciplina;

        try {
            aluno = alunoRepo.findById(Integer.parseInt(paramAluno));
            disciplina = disciplinaRepo.findById(Integer.parseInt(paramDisciplina));
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido foi inserido.");
            return "index";
        }

        if (aluno.isPresent() && disciplina.isPresent()){
            AggregateReference<Aluno, Integer> alunoReference = AggregateReference.to(aluno.get().getId());
            AggregateReference<Disciplina, Integer> disciplinaReference = AggregateReference.to(disciplina.get().getId());

            Matricula matricula = new Matricula(null, alunoReference, disciplinaReference, paramSemestre);
            matriculaRepo.save(matricula);
        } else {
            System.out.println("Aluno e/ou disciplina não existe");
        }


        return "index";
    }
}
