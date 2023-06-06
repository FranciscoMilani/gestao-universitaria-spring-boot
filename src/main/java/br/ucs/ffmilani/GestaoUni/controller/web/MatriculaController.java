package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public String listaMatriculas(Model model, @Autowired HttpSession session){
        List<MatriculaDTO> mats = new ArrayList<>();
        System.out.println(session.getAttribute("dbms"));
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
                             @RequestParam("semestre") String paramSemestre,
                             Model model) {

        Aluno aluno;
        Disciplina disciplina;

        model.addAttribute("layout", "matricularLayout.html");

        if (paramAluno.isBlank() || paramDisciplina.isBlank() || paramSemestre.isBlank()) {
            model.addAttribute("resposta", "Preencha todos os campos.");
            return "index";
        }

        aluno = alunoRepo.findByEmail(paramAluno);
        disciplina = disciplinaRepo.findBySigla(paramDisciplina);

        if (aluno != null && disciplina != null){
            AggregateReference<Aluno, Integer> alunoReference = AggregateReference.to(aluno.getId());
            AggregateReference<Disciplina, Integer> disciplinaReference = AggregateReference.to(disciplina.getId());

            Matricula matricula = new Matricula(null, alunoReference, disciplinaReference, paramSemestre);
            matriculaRepo.save(matricula);
        } else {
            model.addAttribute("resposta", "Aluno e/ou disciplina não existe!");
            return "index";
        }

        model.addAttribute("resposta", "Matricula realizada!");
        return "index";
    }
}
