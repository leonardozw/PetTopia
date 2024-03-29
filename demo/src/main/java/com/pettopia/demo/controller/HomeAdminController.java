package com.pettopia.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.service.ClienteService;

@Controller
@RequestMapping("/homeAdmin")
@PreAuthorize("hasAuthority('APPROLE_admin')")
public class HomeAdminController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView index(){
        var listaClientes = clienteService.getAll();
        return new ModelAndView("homeAdmin/index",
                    "listaClientes",listaClientes);
    }

}
