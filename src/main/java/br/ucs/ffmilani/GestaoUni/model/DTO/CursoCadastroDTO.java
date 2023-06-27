package br.ucs.ffmilani.GestaoUni.model.DTO;

import java.util.List;

public record CursoCadastroDTO(String nome, String sigla, Integer cargaHoraria, List<String> siglaDisciplinas, String siglaUniversidade){
}
