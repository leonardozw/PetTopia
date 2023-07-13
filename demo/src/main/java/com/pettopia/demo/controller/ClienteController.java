package com.pettopia.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Cliente;
import com.pettopia.demo.entity.Pet;
import com.pettopia.demo.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView index(){
        var listaClientes = clienteService.getAll();
        return new ModelAndView("cliente/index",
                    "listaClientes",listaClientes);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
       var cliente = new Cliente();
        return new ModelAndView("cliente/form", 
                                "cliente",cliente); 
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarClientes(@RequestParam("pesquisa") String pesquisa) {
        List<Cliente> clientesEncontradas = clienteService.pesquisarClientes(pesquisa);
        ModelAndView modelAndView = new ModelAndView("cliente/index");
        modelAndView.addObject("listaClientes", clientesEncontradas);
        modelAndView.addObject("pesquisa", pesquisa); 
        return modelAndView;
    }

    @PostMapping(params = "form")
    public ModelAndView save(@ModelAttribute("cliente") Cliente cliente) {
        Pet novoPet = cliente.getPets().get(0); 
    
        novoPet.setCliente(cliente);
    
        clienteService.save(cliente);
    
        return new ModelAndView("redirect:/cliente");
    }
    
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") 
                            Cliente cliente){
        
        return new ModelAndView("cliente/form", 
                                    "cliente",cliente);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        clienteService.delete(id);
        return new ModelAndView("redirect:/cliente");
    }
    @GetMapping("/pet/novo/{id}")
    public ModelAndView novoPet(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.findClienteById(id);
        Pet pet = new Pet();
        return new ModelAndView("cliente/petForm", "cliente", cliente)
                .addObject("pet", pet);
    }


    @PostMapping("/petForm")
    public ModelAndView savePet(@ModelAttribute("cliente") Cliente cliente, @ModelAttribute("pet") Pet pet) {

        var cli = clienteService.findClienteById(cliente.getId());
        pet.setId(0L);
        cli.getPets().add(pet);
        pet.setCliente(cli);
        clienteService.save(cli);
    
        return new ModelAndView("redirect:/cliente");
    }
}
