package br.ucs.ffmilani.GestaoUni.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
public final class Disciplina {

	@Id
	private Integer id;
	private String nome;
	private String sigla;
	private Integer creditos;
	private Integer cargaHoraria;
	private List<Disciplina> preRequisitos;
	
	public Disciplina(Integer id, String nome, String sigla, Integer creditos, Integer cargaHoraria, List<Disciplina> preRequisitos) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.creditos = creditos;
		this.cargaHoraria = cargaHoraria;
		this.preRequisitos = preRequisitos;
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
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer horas) {
		this.cargaHoraria = horas;
	}

	public List<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(List<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

}
