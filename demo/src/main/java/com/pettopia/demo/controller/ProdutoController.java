package com.pettopia.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.service.CategoriaService;
import com.pettopia.demo.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView index(){
        var listaProdutos = produtoService.getAll();
        return new ModelAndView("produto/index",
                    "listaProdutos",listaProdutos);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var produto = new Produto();
        var categorias = categoriaService.getAll();
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("produto", produto);
        dados.put("categorias", categorias);
        return new ModelAndView("produto/form",dados);
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarProdutos(@RequestParam("pesquisa") String pesquisa) {
        List<Produto> produtosEncontrados = produtoService.pesquisarProdutos(pesquisa);
        ModelAndView modelAndView = new ModelAndView("produto/index");
        modelAndView.addObject("listaProdutos", produtosEncontrados);
        modelAndView.addObject("pesquisa", pesquisa); 
        return modelAndView;
    }
    
    @PostMapping(params = "form")
    public ModelAndView save(Produto produto){
        
        produtoService.save(produto);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Produto produto){
        var categorias = categoriaService.getAll();
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("produto", produto);
        dados.put("categorias", categorias);
        return new ModelAndView("produto/form",dados);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        produtoService.delete(id);
        return new ModelAndView("redirect:/produto");
    }
}

