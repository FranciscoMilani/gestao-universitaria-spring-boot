package br.ucs.ffmilani.GestaoUni.controller.web;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.DTO.AlunoCadastroDTO;
import br.ucs.ffmilani.GestaoUni.repository.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.service.CadastroAlunoService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import br.ucs.ffmilani.GestaoUni.service.ValidadorEmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AlunoController {

    private RelatorioService relatorioService;
    private CadastroAlunoService cadastroService;
    private AlunoRepository alunoRepo;
    private CursoRepository cursoRepo;
    private ValidadorEmailService validadorEmail;

    @GetMapping("/alunos")
    public ModelAndView listaAlunos(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("alunos", relatorioService.listaAlunos());
        mv.addObject("layout", "alunoLayout.html");
        return mv;
    }

    @GetMapping("/cadastro/aluno")
    public ModelAndView cadastraAluno(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("layout", "cadastrarLayout.html");

        return mv;
    }

    @PostMapping("/cadastro/aluno")
    public ModelAndView cadastrarAluno(ModelAndView mv,
                                       @RequestParam String nome,
                                       @RequestParam String email,
                                       @RequestParam String curso,
                                       @RequestParam String password){
        mv.setViewName("index");

        if (!validadorEmail.emailEhValido(email)){
            mv.addObject("layout", "cadastrarLayout.html");
            mv.addObject("resposta", "E-mail não é válido");
            return mv;
        }

        Aluno aluno = cadastroService.converteParaEntidade(new AlunoCadastroDTO(nome, email, curso, password));
        String out = cadastroService.cadastraAluno(aluno);
        mv.addObject("layout", "cadastrarLayout.html");
        mv.addObject("resposta", out);
        return mv;
    }

    @GetMapping("/preencherDropdownCursos")
    public ResponseEntity<Object> preencherDropdown(Model model) {
        List <String> cursos = new ArrayList<>();
        relatorioService.listaCursos().forEach(dto -> cursos.add(dto.nome()));
        Object response = new Object() {
            public final String status = "success";
            public final List<String> data = cursos;
        };

        model.addAttribute("layout", "cadastrarLayout.html");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("alunos/remover/{id}")
    public ModelAndView removeAluno(@PathVariable("id") Integer id){
        relatorioService.removeAluno(id);
        return new ModelAndView("redirect:/alunos");
    }
}
