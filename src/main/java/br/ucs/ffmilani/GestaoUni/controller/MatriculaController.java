package br.ucs.ffmilani.GestaoUni.controller;

import br.ucs.ffmilani.GestaoUni.dao.MatriculaJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaJdbcDAO matriculaDao;

    @GetMapping("/matriculas")
    public String listaMatriculas(Model model){
        model.addAttribute("matriculas", matriculaDao.listar());
        return "matriculas";
    }

    @PostMapping("/matricular")
    public void matricular(Model model){
        System.out.println(model.getAttribute("nome").toString());
    }
}
