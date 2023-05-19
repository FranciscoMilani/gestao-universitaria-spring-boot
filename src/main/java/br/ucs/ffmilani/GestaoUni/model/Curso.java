package br.ucs.ffmilani.GestaoUni.model;

import java.util.List;
import br.ucs.ffmilani.GestaoUni.model.enums.TipoCurso;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

public final class Curso {

	@Id
	private String id;
	private String nome;
	private Integer cargaHoraria;
	private List<Disciplina> curriculo;
	private TipoCurso tipo;
	
	public Curso(String id, String nome, Integer cargaHoraria, List<Disciplina> curriculo, TipoCurso tipo) {
		this.id = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.curriculo = curriculo;
		this.tipo = tipo;
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
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public List<Disciplina> getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(List<Disciplina> curriculo) {
		this.curriculo = curriculo;
	}
	
	public void addDisciplina(Disciplina d) {
		this.curriculo.add(d);
	}

	public TipoCurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", curriculo=");
		builder.append(curriculo);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}
	
}
