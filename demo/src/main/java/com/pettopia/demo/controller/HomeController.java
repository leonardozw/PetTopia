package com.pettopia.demo.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Categoria;
import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.service.CategoriaService;
import com.pettopia.demo.service.ProdutoService;

@Controller
@RequestMapping("/home")
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

    @GetMapping("/filtrar")
    public String filtrarPorPreco(@RequestParam("filtro") String filtro, Model model) {

        List<Produto> produtos = produtoService.getAll();

        List<Produto> produtosFiltrados;

        if ("maiorPreco".equals(filtro)) {
            produtosFiltrados = produtos.stream()
                    .sorted(Comparator.comparingDouble(Produto::getValor).reversed())
                    .collect(Collectors.toList());
        } else if ("menorPreco".equals(filtro)) {
            produtosFiltrados = produtos.stream()
                    .sorted(Comparator.comparingDouble(Produto::getValor))
                    .collect(Collectors.toList());
        } else {
            produtosFiltrados = produtos;
        }

        model.addAttribute("listaProdutos", produtosFiltrados);
        return "produto/fragment";
    }

    @GetMapping("/filtrar/categoria")
    public String filtrarPorCategoria(@RequestParam("categoria") Long categoriaId, Model model) {
        Categoria categoria = categoriaService.getById(categoriaId);
        List<Produto> produtosFiltrados = produtoService.getByCategoria(categoria);

        model.addAttribute("listaProdutos", produtosFiltrados);
        return "produto/fragment";
    }

    @GetMapping("/pesquisar")
    public String pesquisarProduto(@RequestParam("search") String searchTerm, Model model) {
        List<Produto> produtos = produtoService.pesquisarProdutos(searchTerm);
        model.addAttribute("listaProdutos", produtos);
        return "produto/fragment";
    }

}
