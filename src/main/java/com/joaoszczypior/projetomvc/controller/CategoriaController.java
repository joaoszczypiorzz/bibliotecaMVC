package com.joaoszczypior.projetomvc.controller;

import com.joaoszczypior.projetomvc.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final List<Categoria> categorias = new ArrayList<>();
}
