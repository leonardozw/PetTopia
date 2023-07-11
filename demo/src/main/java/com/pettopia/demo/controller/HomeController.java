package com.pettopia.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.service.CategoriaService;
import com.pettopia.demo.service.ProdutoService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView index() {
        var listaCategorias = categoriaService.getAll();
        var listaProdutos = produtoService.getAll();
        ModelAndView modelAndView = new ModelAndView("home/index");
        modelAndView.addObject("listaProdutos", listaProdutos);
        modelAndView.addObject("listaCategorias", listaCategorias);
    
        return modelAndView;
    }

}
