package br.ucs.ffmilani.GestaoUni.model.DTO;

import java.util.List;

public record CursoDTO (Integer id, String nome, Integer cargaHoraria, List<String> disciplinas, String siglaUniversidade) {
}
