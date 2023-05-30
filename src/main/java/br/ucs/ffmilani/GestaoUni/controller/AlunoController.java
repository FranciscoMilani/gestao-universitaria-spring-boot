package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.AlunoJdbcDAO;
import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.CursoRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @GetMapping("/alunos")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "alunoLayout.html");
        List<AlunoDTO> alunosDto = new ArrayList<>();
        alunoRepo.findAll().forEach(aluno -> {

            alunosDto.add(new AlunoDTO(aluno.getNome(),
                    aluno.getEmail(),
                    cursoRepo.findById(aluno.getCurso().getId()).get().getNome()));
        });


        mv.addObject("alunos", alunosDto);
        return mv;
    }
}
