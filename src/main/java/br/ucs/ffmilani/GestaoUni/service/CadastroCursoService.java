package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.Curso;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.Universidade;
import br.ucs.ffmilani.GestaoUni.repository.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.repository.DisciplinaRepository;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroCursoService {

    private AlunoRepository alunoRepo;
    private CursoRepository cursoRepo;
    private UniversidadeRepository uniRepo;
    private DisciplinaRepository disciplinaRepo;

    public String cadastraCurso(Curso curso){
        if (curso == null){
            return "Erro ao cadastrar";
        }

        try {
            cursoRepo.save(curso);
            return "Cadastrado";
        } catch (Exception e){
            return "Erro ao cadastrar";
        }
    }

    public Curso converteParaEntidade(CursoCadastroDTO cursoDto){
        Curso curso = cursoRepo.findBySigla(cursoDto.sigla());
        Universidade uni = uniRepo.findBySigla(cursoDto.siglaUniversidade());

        if (curso == null && uni != null){
            Curso novoCurso = new Curso(null, cursoDto.nome(), cursoDto.sigla(), cursoDto.cargaHoraria());
            novoCurso.setUniversidade(uni.getId());

            for (String sigla : cursoDto.siglaDisciplinas()){
                if (disciplinaRepo.findBySigla(sigla) != null){
                    novoCurso.addDisciplinas(disciplinaRepo.findBySigla(sigla));
                } else {
                    return null;
                }
            }

            return novoCurso;
        }

        return null;
    }
}
