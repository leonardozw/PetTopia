package com.pettopia.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pettopia.demo.entity.Pet;
import com.pettopia.demo.repository.PetRepository;
import com.pettopia.demo.service.PetService;

@Service
public class PetServiceImpl implements PetService{
    @Autowired
    private PetRepository repository;

    @Override
    public List<Pet> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Pet> pesquisarPets(String pesquisa){
        return repository.findByNomeContainingIgnoreCase(pesquisa);
    }

    @Override
    public void save(Pet pet) {
        repository.save(pet);
    }
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
