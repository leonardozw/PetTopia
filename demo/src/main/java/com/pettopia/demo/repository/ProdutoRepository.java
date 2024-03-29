package com.pettopia.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pettopia.demo.entity.Categoria;
import com.pettopia.demo.entity.Produto;

@Repository
public interface ProdutoRepository 
    extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoria(Categoria categoria);
}
