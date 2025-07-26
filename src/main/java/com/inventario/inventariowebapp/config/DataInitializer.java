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

        // Actualizar usuario admin
        Usuario admin = usuarioRepository.findByUsername("admin").orElse(new Usuario());
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password")); // Contraseña codificada
        admin.setRol(adminRol);
        admin.setActivo(true);
        usuarioRepository.save(admin);

        // Actualizar usuario almacen
        Usuario almacen = usuarioRepository.findByUsername("almacen").orElse(new Usuario());
        almacen.setUsername("almacen");
        almacen.setPassword(passwordEncoder.encode("password")); // Contraseña codificada
        almacen.setRol(almacenistaRol);
        almacen.setActivo(true);
        usuarioRepository.save(almacen);
    }
}
