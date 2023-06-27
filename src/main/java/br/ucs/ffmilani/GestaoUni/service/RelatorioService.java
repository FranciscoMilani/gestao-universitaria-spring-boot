package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.model.DTO.AlunoDTO;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.DisciplinaRef;
import br.ucs.ffmilani.GestaoUni.repository.*;
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
    private UniversidadeRepository uniRepo;

    public List<AlunoDTO> listaAlunos(){
        List<AlunoDTO> alunosDto = new ArrayList<>();

        alunoRepo.findAll().forEach(aluno -> {

            alunosDto.add(new AlunoDTO(aluno.getId(), aluno.getNome(),
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
            String nomeUniversidade = uniRepo.findById(curso.getUniversidade()).get().getSigla();
            List<String> nomesDisciplinas = new ArrayList<>();

            for (DisciplinaRef disc : curso.getDisciplinas()) {
                nomesDisciplinas.add(discRepo.findById(disc.getId()).get().getNome());
            }

            cursos.add(new CursoDTO(curso.getId(), curso.getNome(), curso.getCargaHoraria(), nomesDisciplinas, nomeUniversidade));
        });

        return cursos;
    }

    public boolean removeCurso(Integer id){
        try {
            cursoRepo.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public boolean removeAluno(Integer id){
        try {
            alunoRepo.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public boolean removeDisciplina(Integer id){
        try {
            discRepo.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public boolean removeUniversidade(Integer id){
        try {
            uniRepo.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public boolean removeMatricula(Integer id){
        try {
            matRepo.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
