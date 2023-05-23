package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaJdbcDAO;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepo;
    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private DisciplinaRepository disciplinaRepo;

    @GetMapping("/matriculas")
    public String listaMatriculas(Model model, ModelAndView mv){
        mv.
        List<>
        for (Matricula mat : matriculaRepo.findAll()){
            mat.
        }
        model.addAttribute("matriculas", matriculaRepo.findAll());
        return "matriculas";
    }

    @PostMapping("/matricular")
    public String matricular(@RequestParam("aluno") String aluno,
                             @RequestParam("disciplina") String disciplina,
                             @RequestParam("semestre") String semestre) {

        try {
            alunoRepo.findById(Integer.parseInt(aluno));
            disciplinaRepo.findById(Integer.parseInt(disciplina));
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido foi inserido.");
            return "index";
        }

        AggregateReference<Aluno, Integer> alunoReference = AggregateReference.to(Integer.parseInt(aluno));
        AggregateReference<Disciplina, Integer> disciplinaReference = AggregateReference.to(Integer.parseInt(disciplina));

        Matricula matricula = new Matricula(null, alunoReference, disciplinaReference, semestre);
        matriculaRepo.save(matricula);

        return "index";
    }
}
