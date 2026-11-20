package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Autor;
import com.joaoszczypior.projetomvc.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final List<Autor> autores = new ArrayList<>();

    public AutorController() {
        List<Livro> obrasMachado = new ArrayList<>();
        obrasMachado.add(new Livro("Dom Casmurro", "Machado de Assis", "978-85-00-00000-0"));
        obrasMachado.add(new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "978-85-00-00000-1"));
        autores.add(new Autor("Machado de Assis", "Brasileira", obrasMachado));

        List<Livro> obrasClarice = new ArrayList<>();
        obrasClarice.add(new Livro("A Hora da Estrela", "Clarice Lispector", "978-85-00-00000-2"));
        autores.add(new Autor("Clarice Lispector", "Brasileira", obrasClarice));
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autores);
        return "autores/lista";
    }

    @GetMapping("/autor")
    public String novo(Model model) {
        Autor autorMock = new Autor();
        model.addAttribute("autor", autorMock);
        return "autores/form";
    }

    @GetMapping("/autor/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        if (indice >= 0 && indice < autores.size()) {
            model.addAttribute("autor", autores.get(indice));
            model.addAttribute("indice", indice);
            return "autores/form";
        }
        return "redirect:/autores";
    }

    @PostMapping("/autor")
    public String cadastrar(@ModelAttribute Autor autor) {
        if (autor.getObrasPublicadas() == null) {
            autor.setObrasPublicadas(new ArrayList<>());
        }
        autores.add(autor);
        return "redirect:/autores";
    }

    @PutMapping("/autor/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Autor autor) {
        if (indice >= 0 && indice < autores.size()) {
            Autor existente = autores.get(indice);
            existente.setNome(autor.getNome());
            existente.setNacionalidade(autor.getNacionalidade());
        }
        return "redirect:/autores";
    }

    @DeleteMapping("/autor/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < autores.size()) {
            autores.remove(indice);
        }
        return "redirect:/autores";
    }

    @GetMapping("/obras/{indice}")
    public String verObras(@PathVariable int indice, Model model) {
        if (indice >= 0 && indice < autores.size()) {
            Autor autor = autores.get(indice);
            model.addAttribute("autor", autor);
            model.addAttribute("indice", indice);
            model.addAttribute("novoLivro", new Livro());
            return "autores/obras/detalhe";
        }
        return "redirect:/autores";
    }

    @PostMapping("/obras/{indice}/livros")
    public String adicionarLivro(@PathVariable int indice, @ModelAttribute Livro novoLivro) {
        if (indice >= 0 && indice < autores.size()) {
            Autor autor = autores.get(indice);
            autor.addBibliografia(novoLivro);
            return "redirect:/autores/obras/" + indice;
        }
        return "redirect:/autores";
    }
}
