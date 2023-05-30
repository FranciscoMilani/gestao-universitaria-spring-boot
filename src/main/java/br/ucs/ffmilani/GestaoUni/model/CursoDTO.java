package br.ucs.ffmilani.GestaoUni.model;

import java.util.List;

public record CursoDTO (String nome, Integer cargaHoraria, List<String> disciplinas) {
}
