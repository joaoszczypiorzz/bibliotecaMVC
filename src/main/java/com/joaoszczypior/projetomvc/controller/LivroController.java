package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();

    public LivroController() {
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "978-85-00-00000-0"));
        livros.add(new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "978-85-00-00000-1"));
        livros.add(new Livro("A Hora da Estrela", "Clarice Lispector", "978-85-00-00000-2"));
    }

    @GetMapping
    public String inicio() {
        return "livros/inicio";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        return "livros/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        Livro livroMock = new Livro();
        model.addAttribute("livro", livroMock);
        return "livros/form";
    }

    @GetMapping("/livro")
    public String exibirLivro(Model model) {
        Livro livro = new Livro();
        livro.setTitutlo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("978-85-00-00000-0");

        model.addAttribute("livro", livro);
        return "livro";
    }

    @GetMapping("/livro/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        if (indice >= 0 && indice < livros.size()) {
            model.addAttribute("livro", livros.get(indice));
            model.addAttribute("indice", indice);
            return "livros/form";
        }
        return "redirect:/livros/listar";
    }

    @PostMapping("/livro")
    public String cadastrar(@ModelAttribute Livro livro) {
        livros.add(livro);
        return "redirect:/livros/listar";
    }

    @PutMapping("/livro/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Livro livro) {
        if (indice >= 0 && indice < livros.size()) {
            livros.set(indice, livro);
        }
        return "redirect:/livros/listar";
    }

    @DeleteMapping("/livro/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < livros.size()) {
            livros.remove(indice);
        }
        return "redirect:/livros/listar";
    }
}
