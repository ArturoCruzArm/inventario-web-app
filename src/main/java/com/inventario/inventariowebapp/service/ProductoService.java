package com.inventario.inventariowebapp.service;

import com.inventario.inventariowebapp.model.Producto;
import com.inventario.inventariowebapp.model.Usuario;
import com.inventario.inventariowebapp.model.HistorialInventario;
import com.inventario.inventariowebapp.repository.ProductoRepository;
import com.inventario.inventariowebapp.repository.HistorialInventarioRepository;
import com.inventario.inventariowebapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private HistorialInventarioRepository historialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto save(Producto producto) {
        boolean isNew = producto.getId() == null;
        Producto savedProducto = productoRepository.save(producto);
        
        String tipoTransaccion = isNew ? "Registro de producto nuevo" : "Actualización de producto";
        registrarHistorial(savedProducto, savedProducto.getCantidad(), tipoTransaccion);
        
        return savedProducto;
    }

    @Transactional
    public void cambiarEstado(Integer id, boolean activo) {
        Producto producto = findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        producto.setActivo(activo);
        productoRepository.save(producto);
        
        String tipoTransaccion = activo ? "Reactivación de producto" : "Baja de producto";
        registrarHistorial(producto, 0, tipoTransaccion);
    }

    @Transactional
    public void agregarStock(Integer id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser positiva.");
        }
        Producto producto = findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        producto.setCantidad(producto.getCantidad() + cantidad);
        productoRepository.save(producto);
        
        registrarHistorial(producto, cantidad, "Entrada de existencias");
    }
    
    @Transactional
    public void sacarStock(Integer id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a sacar debe ser positiva.");
        }
        Producto producto = findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        if (producto.getCantidad() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock para realizar la operación.");
        }
        producto.setCantidad(producto.getCantidad() - cantidad);
        productoRepository.save(producto);

        registrarHistorial(producto, cantidad, "Salida de material");
    }

    private void registrarHistorial(Producto producto, int cantidad, String tipoTransaccion) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        HistorialInventario historial = new HistorialInventario();
        historial.setProducto(producto);
        historial.setUsuario(usuario);
        historial.setCantidad(cantidad);
        historial.setTipoTransaccion(tipoTransaccion);
        historialRepository.save(historial);
    }
}
