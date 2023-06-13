package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Matricula;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatriculaService {
    private MatriculaRepository matriculaRepo;
    private AlunoRepository alunoRepo;
    private DisciplinaRepository disciplinaRepo;

    public List<MatriculaDTO> listarMatriculas(){
        List<MatriculaDTO> mats = new ArrayList<>();
        matriculaRepo.findAll().forEach( m -> {
            Optional<Aluno> a = alunoRepo.findById(m.getAluno().getId());
            Optional<Disciplina> d = disciplinaRepo.findById(m.getDisciplina().getId());

            mats.add(new MatriculaDTO(a.get().getNome(), d.get().getNome(), m.getSemestre()));
        });

        return mats;
    }

    public String matricularAluno(MatriculaDTO matriculaDTO) {
        Aluno aluno;
        Disciplina disciplina;

        if (matriculaDTO.nomeAluno().isBlank()
                || matriculaDTO.nomeDisciplina().isBlank()
                || matriculaDTO.semestre().isBlank()) {

            return "Preencha todos os campos";
        }

        aluno = alunoRepo.findByEmail(matriculaDTO.nomeAluno());
        disciplina = disciplinaRepo.findBySigla(matriculaDTO.nomeDisciplina());

        if (aluno != null && disciplina != null){
            AggregateReference<Aluno, Integer> alunoReference = AggregateReference.to(aluno.getId());
            AggregateReference<Disciplina, Integer> disciplinaReference = AggregateReference.to(disciplina.getId());

            Matricula matricula = new Matricula(null, alunoReference, disciplinaReference, matriculaDTO.semestre());
            matriculaRepo.save(matricula);
        } else {
            return "Aluno e/ou disciplina não existe!";
        }

        return "Matricula realizada!";
    }
}
