package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("aluno")
public final class Aluno {
	@Id
	private Long id;
	private String nome;
	private String email;
	private String password;
	private Curso cursoMatriculado;

	public Aluno(Long id, String nome, String email, String password) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Curso getCursoMatriculado() {
		return cursoMatriculado;
	}

	public void setCursoMatriculado(Curso cursoMatriculado) {
		this.cursoMatriculado = cursoMatriculado;
	}
}
