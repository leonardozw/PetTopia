package com.pettopia.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pettopia.demo.entity.Cliente;

@Repository
public interface ClienteRepository 
    extends JpaRepository<Cliente, Long>{
        List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
