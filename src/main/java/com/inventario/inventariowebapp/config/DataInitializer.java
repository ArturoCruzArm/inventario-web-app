package com.inventario.inventariowebapp.config;

import com.inventario.inventariowebapp.model.Rol;
import com.inventario.inventariowebapp.model.Usuario;
import com.inventario.inventariowebapp.repository.RolRepository;
import com.inventario.inventariowebapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear rol de Administrador si no existe
        Rol adminRol = rolRepository.findByNombre("Administrador").orElseGet(() -> {
            Rol newRol = new Rol();
            newRol.setNombre("Administrador");
            return rolRepository.save(newRol);
        });

        // Crear rol de Almacenista si no existe
        Rol almacenistaRol = rolRepository.findByNombre("Almacenista").orElseGet(() -> {
            Rol newRol = new Rol();
            newRol.setNombre("Almacenista");
            return rolRepository.save(newRol);
        });

        // Crear usuario admin si no existe
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña codificada
            admin.setRol(adminRol);
            admin.setActivo(true);
            usuarioRepository.save(admin);
        }

        // Crear usuario almacen si no existe
        if (usuarioRepository.findByUsername("almacen").isEmpty()) {
            Usuario almacen = new Usuario();
            almacen.setUsername("almacen");
            almacen.setPassword(passwordEncoder.encode("almacen123")); // Contraseña codificada
            almacen.setRol(almacenistaRol);
            almacen.setActivo(true);
            usuarioRepository.save(almacen);
        }
    }
}
