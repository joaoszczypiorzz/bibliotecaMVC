package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final List<Categoria> categorias = new ArrayList<>();

    public CategoriaController() {
        categorias.add(new Categoria("Romance"));
        categorias.add(new Categoria("Ficção Científica"));
        categorias.add(new Categoria("Literatura Brasileira"));
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categorias);
        return "categorias/lista";
    }

    @GetMapping("/categoria")
    public String novo(Model model) {
        Categoria categoriaMock = new Categoria();
        model.addAttribute("categoria", categoriaMock);
        return "categorias/form";
    }

    @GetMapping("/categoria/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        if (indice >= 0 && indice < categorias.size()) {
            model.addAttribute("categoria", categorias.get(indice));
            model.addAttribute("indice", indice);
            return "categorias/form";
        }
        return "redirect:/categorias";
    }

    @PostMapping("/categoria")
    public String cadastrar(@ModelAttribute Categoria categoria) {
        categorias.add(categoria);
        return "redirect:/categorias";
    }

    @PutMapping("/categoria/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Categoria categoria) {
        if (indice >= 0 && indice < categorias.size()) {
            categorias.set(indice, categoria);
        }
        return "redirect:/categorias";
    }

    @DeleteMapping("/categoria/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < categorias.size()) {
            categorias.remove(indice);
        }
        return "redirect:/categorias";
    }
}
