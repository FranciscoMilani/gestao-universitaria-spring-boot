package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.GestaoUniApplication;
import br.ucs.ffmilani.GestaoUni.repository.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.repository.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.repository.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.DTO.AlunoDTO;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RelatorioService {

    private AlunoRepository alunoRepo;
    private DisciplinaRepository discRepo;
    private CursoRepository cursoRepo;
    private MatriculaRepository matRepo;

    public List<AlunoDTO> listaAlunos(){
        List<AlunoDTO> alunosDto = new ArrayList<>();

        alunoRepo.findAll().forEach(aluno -> {

            alunosDto.add(new AlunoDTO(aluno.getNome(),
                    aluno.getEmail(),
                    cursoRepo.findById(aluno.getCurso().getId()).get().getNome()));
        });

        return alunosDto;
    }

    public List<Disciplina> listaDisciplinas() {
        List<Disciplina> target = new ArrayList<>();
        discRepo.findAll().forEach(target::add);
        return target;
    }

    public List<CursoDTO> listaCursos() {
        List<CursoDTO> cursos = new ArrayList<>();
        cursoRepo.findAll().forEach(curso -> {
            List<String> nomesDisciplinas = new ArrayList<>();

            for (DisciplinaRef disc : curso.getDisciplinas()) {
                nomesDisciplinas.add(discRepo.findById(disc.getId()).get().getNome());
            }

            cursos.add(new CursoDTO(curso.getNome(), curso.getCargaHoraria(), nomesDisciplinas));
        });

        return cursos;
    }
}
