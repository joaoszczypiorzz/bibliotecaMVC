package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Emprestimo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public EmprestimoController() {
        LocalDateTime agora = LocalDateTime.now().withNano(0);
        emprestimos.add(new Emprestimo("João Silva", "Dom Casmurro", agora.minusDays(2), null));
        emprestimos.add(new Emprestimo("Maria Santos", "A Hora da Estrela", agora.minusDays(7), agora.minusDays(1)));
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimos/lista";
    }

    @GetMapping("/emprestimo")
    public String novo(Model model) {
        Emprestimo emprestimoMock = new Emprestimo();
        LocalDateTime agora = LocalDateTime.now().withNano(0);
        emprestimoMock.setDataEmprestimo(agora);
        emprestimoMock.setDataDevolucao(agora.plusDays(5));
        model.addAttribute("emprestimo", emprestimoMock);
        return "emprestimos/form";
    }

    @GetMapping("/emprestimo/{indice}")
    public String editar(@PathVariable int indice, Model model) {
        if (indice >= 0 && indice < emprestimos.size()) {
            model.addAttribute("emprestimo", emprestimos.get(indice));
            model.addAttribute("indice", indice);
            return "emprestimos/form";
        }
        return "redirect:/emprestimos";
    }

    @PostMapping("/emprestimo")
    public String cadastrar(@ModelAttribute Emprestimo emprestimo) {
        if (emprestimo.getDataEmprestimo() != null && emprestimo.getDataDevolucao() == null) {
            emprestimo.setDataDevolucao(emprestimo.getDataEmprestimo().plusDays(5));
        }
        emprestimos.add(emprestimo);
        return "redirect:/emprestimos";
    }

    @PutMapping("/emprestimo/{indice}")
    public String atualizar(@PathVariable int indice, @ModelAttribute Emprestimo emprestimo) {
        if (indice >= 0 && indice < emprestimos.size()) {
            emprestimos.set(indice, emprestimo);
        }
        return "redirect:/emprestimos";
    }

    @DeleteMapping("/emprestimo/{indice}")
    public String excluir(@PathVariable int indice) {
        if (indice >= 0 && indice < emprestimos.size()) {
            emprestimos.remove(indice);
        }
        return "redirect:/emprestimos";
    }
}
