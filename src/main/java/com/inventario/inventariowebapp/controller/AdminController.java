package com.inventario.inventariowebapp.controller;

import com.inventario.inventariowebapp.model.Producto;
import com.inventario.inventariowebapp.service.ProductoService;
import com.inventario.inventariowebapp.repository.HistorialInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private HistorialInventarioRepository historialRepository;

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/producto-form";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.save(producto);
        return "redirect:/admin/productos";
    }

    @PostMapping("/productos/baja/{id}")
    public String darDeBaja(@PathVariable Integer id) {
        productoService.cambiarEstado(id, false);
        return "redirect:/admin/productos";
    }

    @PostMapping("/productos/reactivar/{id}")
    public String reactivar(@PathVariable Integer id) {
        productoService.cambiarEstado(id, true);
        return "redirect:/admin/productos";
    }

    @PostMapping("/productos/stock/agregar")
    public String agregarStock(@RequestParam Integer id, @RequestParam int cantidad) {
        productoService.agregarStock(id, cantidad);
        return "redirect:/admin/productos";
    }
    
    @GetMapping("/historial")
    public String verHistorial(
            @RequestParam(value = "productoId", required = false) Integer productoId,
            @RequestParam(value = "fechaInicio", required = false) String fechaInicioStr,
            @RequestParam(value = "fechaFin", required = false) String fechaFinStr,
            Model model) {

        List<com.inventario.inventariowebapp.model.HistorialInventario> historial;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        if (productoId != null) {
            historial = historialRepository.findByProductoId(productoId);
        } else if (fechaInicioStr != null && fechaFinStr != null) {
            LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
            LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
            historial = historialRepository.findByFechaTransaccionBetween(fechaInicio, fechaFin);
        } else {
            historial = historialRepository.findAll();
        }

        model.addAttribute("historial", historial);
        model.addAttribute("productos", productoService.findAll()); // Para el filtro por producto
        return "admin/historial";
    }
}
