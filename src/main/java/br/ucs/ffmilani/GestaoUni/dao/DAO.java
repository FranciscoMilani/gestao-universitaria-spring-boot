package br.ucs.ffmilani.GestaoUni.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T>{
    List<T> listar();

    void criar(T t);

    Optional<T> encontrar(int id);
}
