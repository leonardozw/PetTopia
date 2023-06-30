package com.pettopia.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/produtos")
public class HomeProdutosController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("home/produtos");
    }

}