package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("curso_disciplina")
public class DisciplinaRef {
    @Column("disciplina")
    private Integer disciplina;

    DisciplinaRef(Integer disciplina) {
        this.disciplina = disciplina;
    }

    public Integer getId(){
        return this.disciplina;
    }
}
