package com.adev.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adev.crud.model.Producto;
import com.adev.crud.service.ProductoService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class ProductoController {


    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // Página principal
    @GetMapping
    public String index(Model model) {
        model.addAttribute("productos", service.listar());
        model.addAttribute("producto", new Producto());
        return "productos/listar";
    }

    // API REST para AJAX
    @GetMapping("/listar")
    @ResponseBody
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto p = service.guardar(producto);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/obtener/{id}")
    @ResponseBody
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

     @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
    
    
}
