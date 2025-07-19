package com.inventario.inventariowebapp.controller;

import com.inventario.inventariowebapp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/index")
    public String index() {
        return "almacen/index";
    }

    @GetMapping("/inventario")
    public String verInventario(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "almacen/inventario";
    }

    @PostMapping("/inventario/salida")
    public String sacarMaterial(@RequestParam Integer id, @RequestParam int cantidad, Model model) {
        try {
            productoService.sacarStock(id, cantidad);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("productos", productoService.findAll()); // Recargar productos para la vista
            return "almacen/inventario";
        }
        return "redirect:/almacen/inventario";
    }
}
