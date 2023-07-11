package com.pettopia.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.service.ProdutoService;

@Controller
public class DetalhesController {

    @Autowired
    ProdutoService produtoService;
    
    @GetMapping("/produto/detalhes")
    public ModelAndView index() {;
        return new ModelAndView("produto/detalhes");
    }

    @GetMapping("/produto/detalhes/{id}")
    public ModelAndView exibirDetalhesProduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);

        ModelAndView modelAndView = new ModelAndView("produto/detalhes");
        modelAndView.addObject("produto", produto);

        return modelAndView;
    }

}
