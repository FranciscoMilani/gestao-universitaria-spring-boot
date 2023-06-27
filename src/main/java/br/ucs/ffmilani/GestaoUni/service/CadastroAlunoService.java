package br.ucs.ffmilani.GestaoUni.service;

import br.ucs.ffmilani.GestaoUni.repository.AlunoRepository;
import br.ucs.ffmilani.GestaoUni.repository.CursoRepository;
import br.ucs.ffmilani.GestaoUni.model.Aluno;
import br.ucs.ffmilani.GestaoUni.model.DTO.AlunoCadastroDTO;
import br.ucs.ffmilani.GestaoUni.model.Curso;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroAlunoService {

    private AlunoRepository alunoRepo;
    private CursoRepository cursoRepo;

    public String cadastraAluno(Aluno aluno){
        if (aluno == null){
            return "Erro ao cadastrar";
        }

        try {
            alunoRepo.save(aluno);
            return "Cadastrado";
        } catch (Exception e){
            return "Erro ao cadastrar";
        }
    }

    public Aluno converteParaEntidade(AlunoCadastroDTO alunoDto){
        Curso curso = cursoRepo.findByNome(alunoDto.nomeCurso());
        if (curso != null){
            return new Aluno(null, alunoDto.nome(), alunoDto.email(), alunoDto.password(), AggregateReference.to(curso.getId()));
        }

        return null;
    }

    public AlunoCadastroDTO converteParaDTO(Aluno aluno){
        Integer cursoId = aluno.getCurso().getId();
        String cursoNome = cursoRepo.findById(cursoId).map(Curso::getNome).orElseThrow();

        return new AlunoCadastroDTO(
                aluno.getNome(),
                aluno.getEmail(),
                cursoNome,
                aluno.getPassword()
        );
    }
}
