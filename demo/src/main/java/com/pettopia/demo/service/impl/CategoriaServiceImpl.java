package com.pettopia.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pettopia.demo.entity.Categoria;
import com.pettopia.demo.repository.CategoriaRepository;
import com.pettopia.demo.service.CategoriaService;

@Service
public class CategoriaServiceImpl
        implements CategoriaService{
    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Categoria> pesquisarCategorias(String pesquisa){
        return repository.findByNomeContainingIgnoreCase(pesquisa);
    }

    @Override
    public void save(Categoria categoria) {
        repository.save(categoria);
    }
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}