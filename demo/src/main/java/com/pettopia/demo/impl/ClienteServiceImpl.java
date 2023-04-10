package com.pettopia.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pettopia.demo.entity.Cliente;
import com.pettopia.demo.repository.ClienteRepository;
import com.pettopia.demo.service.ClienteService;

public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> getAll(){
        return repository.findAll();
    }
}
