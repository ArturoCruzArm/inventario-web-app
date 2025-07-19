package com.inventario.inventariowebapp.repository;

import com.inventario.inventariowebapp.model.HistorialInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistorialInventarioRepository extends JpaRepository<HistorialInventario, Integer> {
    List<HistorialInventario> findByProductoId(Integer productoId);
    List<HistorialInventario> findByFechaTransaccionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
