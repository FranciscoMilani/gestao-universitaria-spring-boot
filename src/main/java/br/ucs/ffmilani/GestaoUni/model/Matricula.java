package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
public final class Matricula {

	@Id
	private Integer id;
	private Aluno aluno;
	private Disciplina disciplina;
	private String semestre;

	public Matricula(Integer id, Aluno aluno, Disciplina disciplina, String semestre) {
		this.id = id;
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.semestre = semestre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

}
