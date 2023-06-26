package br.ucs.ffmilani.GestaoUni.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("curso")
public final class Curso {

	@Id
	private Integer id;
	private String nome;
	private Integer cargahoraria;
	@MappedCollection(idColumn="curso", keyColumn="disciplina")
	private Set<DisciplinaRef> disciplinas = new HashSet<>();

//	@Column("id")
//	private Universidade universidade;

	public Curso(Integer id, String nome, Integer cargahoraria) {
		this.id = id;
		this.nome = nome;
		this.cargahoraria = cargahoraria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCargaHoraria() {
		return cargahoraria;
	}

	public void setCargaHoraria(Integer cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public Set<DisciplinaRef> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<DisciplinaRef> disciplina) {
		this.disciplinas = disciplina;
	}

	public void addDisciplinas(Disciplina disciplina) {
		this.disciplinas.add(new DisciplinaRef(disciplina.getId()));
	}

}
