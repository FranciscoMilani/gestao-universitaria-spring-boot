package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.model.Curso;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.Universidade;
import br.ucs.ffmilani.GestaoUni.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroCursoService {

    private CursoRepository cursoRepo;
    private UniversidadeRepository uniRepo;
    private DisciplinaRepository disciplinaRepo;
    private CursoDisciplinaRepository cursoDisciplinaRepo;

    public String cadastraCurso(Curso curso){
        if (curso == null){
            return "Erro ao cadastrar";
        }

        try {
            cursoRepo.save(curso);
            return "Cadastrado";
        } catch (Exception e){
            return "Erro ao cadastrar. Algo incorreto.";
        }
    }

    public Curso converteParaEntidade(CursoCadastroDTO cursoDto){
        Curso curso = cursoRepo.findBySigla(cursoDto.sigla());
        Universidade uni = uniRepo.findBySigla(cursoDto.siglaUniversidade());

        if (curso == null && uni != null){
            Curso novoCurso = new Curso(null, cursoDto.nome(), cursoDto.sigla(), cursoDto.cargaHoraria());
            novoCurso.setUniversidade(uni.getId());

            for (String sigla : cursoDto.siglaDisciplinas()){
                Disciplina d = disciplinaRepo.findBySigla(sigla);

                if (d != null){
                    int count = cursoDisciplinaRepo.countByDisciplina(d.getId());

                    if (count == 0){
                        novoCurso.addDisciplinas(disciplinaRepo.findBySigla(sigla));
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            return novoCurso;
        }

        return null;
    }
}
