package com.pettopia.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pettopia.demo.entity.Categoria;

public interface CategoriaRepository 
    extends JpaRepository<Categoria, Long>{
        List<Categoria> findByNomeContainingIgnoreCase(String nome);
        
}