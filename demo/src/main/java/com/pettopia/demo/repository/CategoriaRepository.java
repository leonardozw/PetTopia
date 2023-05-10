package com.pettopia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pettopia.demo.entity.Categoria;

@Repository
public interface CategoriaRepository 
    extends JpaRepository<Categoria, Long>{
    
}