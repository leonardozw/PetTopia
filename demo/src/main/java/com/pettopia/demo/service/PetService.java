package com.pettopia.demo.service;

import java.util.List;

import com.pettopia.demo.entity.Pet;

public interface PetService {
    List<Pet> getAll();

    void save(Pet pet);

    List<Pet> pesquisarPets(String pesquisa);

    void delete(long id);
}
