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

import com.pettopia.demo.entity.Categoria;
import com.pettopia.demo.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ModelAndView index(){
        var listaCategorias = categoriaService.getAll();
        return new ModelAndView("categoria/index",
                    "listaCategorias",listaCategorias);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        var categoria = new Categoria();
        return new ModelAndView("categoria/form", 
                                "categoria",categoria);
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarCategorias(@RequestParam("pesquisa") String pesquisa) {
        List<Categoria> categoriasEncontradas = categoriaService.pesquisarCategorias(pesquisa);
        ModelAndView modelAndView = new ModelAndView("categoria/index");
        modelAndView.addObject("listaCategorias", categoriasEncontradas);
        modelAndView.addObject("pesquisa", pesquisa); 
        return modelAndView;
    }

    @PostMapping(params = "form")
    public ModelAndView save(Categoria categoria){

        categoriaService.save(categoria);
        return new ModelAndView("redirect:/categoria");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") 
                            Categoria categoria){
        
        return new ModelAndView("categoria/form", 
                                    "categoria",categoria);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        categoriaService.delete(id);
        return new ModelAndView("redirect:/categoria");
    }
}
