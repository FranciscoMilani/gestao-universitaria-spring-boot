package br.ucs.ffmilani.GestaoUni.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public final class Curso {

	@Id
	private String id;
	private String nome;
	private Integer cargahoraria;
	private List<Disciplina> curriculo = new ArrayList<>();
	
	public Curso(String id, String nome, Integer cargahoraria) {
		this.id = id;
		this.nome = nome;
		this.cargahoraria = cargahoraria;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<Disciplina> getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(List<Disciplina> curriculo) {
		this.curriculo = curriculo;
	}
	
	public void addDisciplina(Disciplina disciplina) {
		curriculo.add(disciplina);
	}
	
}
