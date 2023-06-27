package br.ucs.ffmilani.GestaoUni.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table("universidade")
@Getter
public class Universidade {

    @Id
    private Integer id;
    private String sigla;
    private String nome;
    @MappedCollection(idColumn = "universidade", keyColumn = "id")
    private Set<Curso> cursos;

    public Universidade(Integer id, String sigla, String nome, Set<Curso> cursos) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.cursos = cursos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public Set<Curso> getCursos() {
//        return cursos;
//    }
//
//    public void setCursos(Set<Curso> cursos) {
//        this.cursos = cursos;
//    }
}
