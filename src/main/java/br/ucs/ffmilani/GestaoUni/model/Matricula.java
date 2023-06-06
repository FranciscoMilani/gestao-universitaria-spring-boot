package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

@Table
public final class Matricula {

	@Id
	private Integer id;
	@Column("aluno")
	private AggregateReference<Aluno, Integer> aluno;
	@Column("disciplina")
	private AggregateReference<Disciplina, Integer> disciplina;
	private String semestre;

	public Matricula(Integer id, AggregateReference<Aluno, Integer> aluno, AggregateReference<Disciplina, Integer> disciplina, String semestre) {
		this.id = id;
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.semestre = semestre;
	}

	public Matricula() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AggregateReference<Aluno, Integer> getAluno() {
		return aluno;
	}

	public void setAluno(AggregateReference<Aluno, Integer> aluno) {
		this.aluno = aluno;
	}

	public AggregateReference<Disciplina, Integer> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(AggregateReference<Disciplina, Integer> aluno) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		return "Matricula{" +
				"id=" + id +
				", aluno=" + aluno +
				", disciplina=" + disciplina +
				", semestre='" + semestre + '\'' +
				'}';
	}
}
