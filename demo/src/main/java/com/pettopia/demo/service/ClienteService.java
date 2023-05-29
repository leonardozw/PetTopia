package com.pettopia.demo.service;

import java.util.List;

import com.pettopia.demo.entity.Cliente;

public interface ClienteService {
    List<Cliente> getAll();

    void save(Cliente cliente);

    List<Cliente> pesquisarClientes(String pesquisa);

    void delete(long id);

}
