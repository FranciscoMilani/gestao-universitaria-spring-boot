package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.repository.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.repository.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.repository.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.*;
import br.ucs.ffmilani.GestaoUni.model.DTO.MatriculaDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MatriculaService {
    private MatriculaRepository matriculaRepo;
    private AlunoRepository alunoRepo;
    private DisciplinaRepository disciplinaRepo;
    private CursoRepository cursoRepository;

    public List<MatriculaDTO> listarMatriculas(){
        List<MatriculaDTO> mats = new ArrayList<>();
        matriculaRepo.findAll().forEach( m -> {
            Optional<Aluno> a = alunoRepo.findById(m.getAluno().getId());
            Optional<Disciplina> d = disciplinaRepo.findById(m.getDisciplina().getId());

            mats.add(new MatriculaDTO(a.get().getNome(), d.get().getNome(), m.getSemestre()));
        });

        return mats;
    }

    public String matricularAluno(MatriculaDTO matriculaDTO, List<String> disciplinas) {
        Aluno aluno;
        Disciplina disciplina;

        if (matriculaDTO.nomeAluno().isBlank()
                || matriculaDTO.semestre().isBlank()) {

            return "Preencha todos os campos";
        }

        if (disciplinas == null || disciplinas.size() < 3){
            return "Selecione ao menos 3 disciplinas";
        }

        aluno = alunoRepo.findByEmail(matriculaDTO.nomeAluno());
        if (aluno == null){
            return "Aluno não existe no sistema";
        } else {
            List<Matricula> byAlunoSemestre = matriculaRepo.findByAlunoAndSemestre(aluno.getId(), matriculaDTO.semestre());

            if (byAlunoSemestre.size() > 1){
                return "Este aluno já foi matriculado nesse semestre";
            }
        }

        for (String disc : disciplinas) {
            disciplina = disciplinaRepo.findBySigla(disc);

            if (disciplina != null) {
                Disciplina bySigla = disciplinaRepo.findBySigla(disc);
                Optional<Curso> curso = cursoRepository.findById(aluno.getCurso().getId());
                DisciplinaRef disciplinaRef = curso.get().getDisciplinas()
                        .stream()
                        .filter(d -> bySigla.getId().equals(d.getId()))
                        .findAny()
                        .orElse(null);

                if (disciplinaRef == null) {
                    return "Disciplina não pertence ao curso";
                }

                AggregateReference<Aluno, Integer> alunoReference = AggregateReference.to(aluno.getId());
                AggregateReference<Disciplina, Integer> disciplinaReference = AggregateReference.to(disciplina.getId());

                Matricula matricula = new Matricula(null, alunoReference, disciplinaReference, matriculaDTO.semestre());

                try {
                    matriculaRepo.save(matricula);
                } catch (DbActionExecutionException e) {
                    return "Já está matriculado na disciplina [" + disc + "]";
                }

            } else {
                return "Disciplina não existe no sistema";
            }
        }

        return "Matricula realizada!";
    }

    public List<String> getSiglas(){
        Iterable<Disciplina> iterable = disciplinaRepo.findAll();
        List<String> siglas = StreamSupport.stream(iterable.spliterator(), false)
                .map(element -> element.getSigla())
                .collect(Collectors.toList());

        return siglas;
    }
}
