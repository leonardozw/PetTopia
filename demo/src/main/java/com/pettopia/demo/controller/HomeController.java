package com.pettopia.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.service.ProdutoService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ModelAndView index() {
        var listaProdutos = produtoService.getAll();
        return new ModelAndView("home/index",
                "listaProdutos", listaProdutos);
    }

}
