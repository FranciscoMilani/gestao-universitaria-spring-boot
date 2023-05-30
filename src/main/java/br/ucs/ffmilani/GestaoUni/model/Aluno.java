package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("aluno")
public final class Aluno {
	@Id
	private Integer id;
	private String nome;
	private String email;
	private String password;

	//@Transient
//	@Column("id")
//	private Curso curso;

	private AggregateReference<Curso, Integer> curso;

	public Aluno(Integer id, String nome, String email, String password, AggregateReference<Curso, Integer> curso) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.curso = curso;
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

	public AggregateReference<Curso, Integer> getCurso() {
		return curso;
	}

	public void setCurso(AggregateReference<Curso, Integer> curso) {
		this.curso = curso;
	}
}
