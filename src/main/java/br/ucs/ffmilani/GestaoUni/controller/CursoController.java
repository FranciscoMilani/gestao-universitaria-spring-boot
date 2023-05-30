package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.CursoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepo;
    @Autowired
    private DisciplinaRepository disciplinaRepo;

    @GetMapping("/cursos")
    public String listaCursos(Model model) {
        List<CursoDTO> cursos = new ArrayList<>();
        cursoRepo.findAll().forEach(curso -> {
            List<String> nomesDisciplinas = new ArrayList<>();
            for (var disc : curso.getDisciplinas()){
                nomesDisciplinas.add(disciplinaRepo.findById(disc.getId()).get().getNome());
            }

            cursos.add(new CursoDTO(curso.getNome(), curso.getCargaHoraria(), nomesDisciplinas));
        });

        model.addAttribute("layout", "cursoLayout.html");
        model.addAttribute("cursos", cursos);

        return "index";
    }
}
