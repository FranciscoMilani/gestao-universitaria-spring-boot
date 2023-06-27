package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.model.Curso;
import br.ucs.ffmilani.GestaoUni.model.DTO.UniversidadeCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.Universidade;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CadastroUniversidadeService {

    private UniversidadeRepository uniRepo;
    private CursoRepository cursoRepo;

    public String cadastraUniversidade(Universidade uni){
        if (uni == null){
            return "Erro ao cadastrar";
        }

        try {
            uniRepo.save(uni);
            return "Cadastrado";
        } catch (Exception e){
            return "Erro ao cadastrar";
        }
    }

    public Universidade converteParaEntidade(UniversidadeCadastroDTO uniDto){
        Universidade uni = uniRepo.findBySigla(uniDto.sigla());
        Set<Curso> cursos = new HashSet<>();

        for (String siglaCurso : uniDto.siglaCursos()) {
            Curso curso = cursoRepo.findBySigla(siglaCurso);

            if (curso != null) {
                cursos.add(curso);
            }
        }

        if (uni != null) {
            Universidade newUni = new Universidade(null, uniDto.sigla(), uniDto.nome(), cursos);
            return newUni;
        }

        return null;
    }
}
