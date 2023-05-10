package com.pettopia.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.repository.ProdutoRepository;
import com.pettopia.demo.service.ProdutoService;

@Service
public class ProdutoServiceImpl
        implements ProdutoService{
    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<Produto> getAll() {
        return repository.findAll();
    }
    @Override
    public void save(Produto produto) {
        repository.save(produto);
    }
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
