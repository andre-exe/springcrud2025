package com.adev.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.adev.crud.model.Producto;

import com.adev.crud.service.ProductoService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("nuevoProducto", new Producto());
        return "productos/index";
    } 
    
    @GetMapping("/nuevo")
    public String crearProductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/nuevo";
    }

    @PostMapping
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProductoForm(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "productos/editar";
    }

    @PostMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @ModelAttribute("producto") Producto producto) {
        Producto prodExistente = productoService.obtenerProductoPorId(id);
        prodExistente.setNombre(producto.getNombre());
        prodExistente.setDescripcion(producto.getDescripcion());
        prodExistente.setPrecio(producto.getPrecio());
        productoService.guardarProducto(prodExistente);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }   
}
