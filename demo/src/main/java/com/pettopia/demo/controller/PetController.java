package com.pettopia.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Pet;
import com.pettopia.demo.service.PetService;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    
    @GetMapping
    public ModelAndView index(){
        var listaPets = petService.getAll();
        return new ModelAndView("pet/index",
                    "listaPets",listaPets);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var pet = new Pet();
        return new ModelAndView("pet/form", 
                                "pet",pet);
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarPets(@RequestParam("pesquisa") String pesquisa) {
        List<Pet> petsEncontrados = petService.pesquisarPets(pesquisa);
        ModelAndView modelAndView = new ModelAndView("pet/index");
        modelAndView.addObject("listaPets", petsEncontrados);
        modelAndView.addObject("pesquisa", pesquisa); 
        return modelAndView;
    }
    
    @PostMapping(params = "form")
    public ModelAndView save(Pet pet){
        
        petService.save(pet);
        return new ModelAndView("redirect:/pet");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") 
                            Pet pet){
        
        return new ModelAndView("pet/form", 
                                    "pet",pet);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        petService.delete(id);
        return new ModelAndView("redirect:/pet");
    }
}
