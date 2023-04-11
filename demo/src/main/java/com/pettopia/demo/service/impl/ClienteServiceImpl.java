package com.pettopia.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pettopia.demo.entity.Cliente;
import com.pettopia.demo.repository.ClienteRepository;
import com.pettopia.demo.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> getAll(){
        return repository.findAll();
    }
}
