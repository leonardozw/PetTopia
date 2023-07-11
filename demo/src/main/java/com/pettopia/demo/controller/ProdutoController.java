package com.pettopia.demo.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.service.CategoriaService;
import com.pettopia.demo.service.ProdutoService;
import com.pettopia.demo.service.SalvarArquivosService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private SalvarArquivosService salvarArquivoService;

    @GetMapping
    public ModelAndView index() {
        var listaProdutos = produtoService.getAll();
        return new ModelAndView("produto/index",
                "listaProdutos", listaProdutos);
    }

    @GetMapping("/produto/{id}")
    public String exibirDetalhesProduto(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoService.buscarProdutoPorId(id);

        model.addAttribute("produto", produto);
        return "produto/detalhes";
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        var produto = new Produto();
        var categorias = categoriaService.getAll();
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("produto", produto);
        dados.put("categorias", categorias);
        return new ModelAndView("produto/form", dados);
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
    public ModelAndView save(@Valid Produto produto, BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("produto/form", "produto", produto);
        }
        if (file.getSize() != 0) {
            String caminho = salvarArquivoService.save(file);
            produto.setFoto(caminho);
        }

        produtoService.save(produto);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Produto produto) {
        var categorias = categoriaService.getAll();
        HashMap<String, Object> dados = new HashMap<>();
        dados.put("produto", produto);
        dados.put("categorias", categorias);
        return new ModelAndView("produto/form", dados);
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") long id) {

        produtoService.delete(id);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping(value = "/image/{id}")
    public @ResponseBody byte[] getImage(@PathVariable("id") Produto produto) {
        try {
            File file = new File(produto.getFoto());
            byte[] bytes = new byte[(int) file.length()];
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file));) {
                dis.readFully(bytes);
            }
            return bytes;
        } catch (Exception e) {
            return new byte[0];
        }
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
    
}
