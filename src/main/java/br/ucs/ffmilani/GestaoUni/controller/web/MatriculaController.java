package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.model.DTO.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class MatriculaController {

    private MatriculaService matriculaService;
    private RelatorioService relatorioService;

    @GetMapping("/matriculas")
    public String listaMatriculas(Model model){
        model.addAttribute("layout", "matriculaLayout.html");
        model.addAttribute("matriculas", matriculaService.listarMatriculas());
        return "index";
    }

    @GetMapping("/matricular")
    public ModelAndView matricular(){
        ModelAndView mv = new ModelAndView();
        MatriculaDTO mat = new MatriculaDTO(null, null, null, null);

        mv.setViewName("index");
        mv.addObject("matricula", mat);
        mv.addObject("layout", "matricularLayout.html");
        mv.addObject("options",  matriculaService.getSiglas());

        return mv;
    }

    @PostMapping(value = "/matricular")
    public String matricular(@ModelAttribute("matricula") MatriculaDTO matriculaDTO,
                             @RequestParam("disciplina") String disciplina,
                             Model model) {

        List<String> lista = Arrays.stream(disciplina.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        String resposta = matriculaService.matricularAluno(matriculaDTO, lista);

        model.addAttribute("resposta", resposta);
        model.addAttribute("layout", "matricularLayout.html");

        return "index";
    }

    @GetMapping("/preencherDropdown")
    public ResponseEntity<Object> preencherDropdown(Model model) {

        Object response = new Object() {
            public final String status = "success";
            public final List<String> data =  matriculaService.getSiglas();
        };

        model.addAttribute("layout", "matricularLayout.html");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("matriculas/remover/{id}")
    public ModelAndView removeMatricula(@PathVariable("id") Integer id){
        relatorioService.removeMatricula(id);
        return new ModelAndView("redirect:/matriculas");
    }
}
