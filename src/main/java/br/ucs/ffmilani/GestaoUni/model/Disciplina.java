package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public final class Disciplina {

	@Id
	private Integer id;
	private String nome;
	private String sigla;
	private Integer creditos;
	private Integer cargahoraria;
	
	public Disciplina(Integer id, String nome, String sigla, Integer creditos, Integer cargahoraria) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.creditos = creditos;
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

	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Integer getCreditos() {
		return creditos;
	}
	
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	
	public Integer getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(Integer horas) {
		this.cargahoraria = horas;
	}

}
