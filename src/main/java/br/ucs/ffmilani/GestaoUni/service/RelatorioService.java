package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.dao.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.dao.CursoRepository;
import br.ucs.ffmilani.GestaoUni.dao.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.AlunoDTO;
import br.ucs.ffmilani.GestaoUni.model.CursoDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import lombok.AllArgsConstructor;
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
