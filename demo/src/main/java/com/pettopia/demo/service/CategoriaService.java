package com.pettopia.demo.service;

import java.util.List;

import com.pettopia.demo.entity.Categoria;

public interface CategoriaService {
    List<Categoria> getAll();

    void save(Categoria categoria);

    List<Categoria> pesquisarCategorias(String pesquisa);

    void delete(long id);

    Categoria getById(Long categoriaId);
}
