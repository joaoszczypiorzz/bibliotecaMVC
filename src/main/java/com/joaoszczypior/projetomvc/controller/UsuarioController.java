package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario("Administrador", "admin", "1234", new ArrayList<>()));
    }

    @GetMapping
    public String inicio(Model model) {
        return login(model);
    }

    @GetMapping("/login")
    public String login(Model model) {
        Usuario usuarioMock = new Usuario();
        model.addAttribute("usuario", usuarioMock);
        return "usuarios/login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String login, @RequestParam String senha, Model model) {
        boolean autenticado = usuarios.stream()
                .anyMatch(u -> u.getLogin() != null && u.getLogin().equals(login)
                        && u.getSenha() != null && u.getSenha().equals(senha));

        if (autenticado) {
            return "redirect:/livros";
        }

        model.addAttribute("usuario", new Usuario());
        model.addAttribute("erroLogin", "Login ou senha inválidos.");
        return "usuarios/login";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        if (usuario.getListaEmprestimos() == null) {
            usuario.setListaEmprestimos(new ArrayList<>());
        }
        usuarios.add(usuario);
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("sucesso", "Usuário cadastrado com sucesso! Faça o login.");
        return "usuarios/login";
    }
}
