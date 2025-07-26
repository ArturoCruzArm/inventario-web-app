# Sistema de Inventario Web

## Descripción
Aplicación web desarrollada en Spring Boot para la gestión de inventarios con autenticación y roles de usuario.

## Tecnologías Utilizadas
- **Backend**: Spring Boot 3.3.1
- **Base de datos**: MySQL/MariaDB
- **Seguridad**: Spring Security
- **Frontend**: Thymeleaf
- **Java**: 17

## Configuración de la Base de Datos

### Conexión
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventario_db
spring.datasource.username=root
spring.datasource.password=r00t
```

### Script de Inicialización
Ejecutar el archivo `SCRIPTS/full_setup.sql` para crear:
- Base de datos `inventario_db`
- Tablas: `roles`, `usuarios`, `productos`, `historial_inventario`
- Datos iniciales

## Usuarios del Sistema

### Credenciales de Acceso
- **Administrador**
  - Usuario: `admin`
  - Contraseña: `password`
  - Acceso: Panel administrativo completo

- **Almacenista**
  - Usuario: `almacen`
  - Contraseña: `password`
  - Acceso: Gestión de inventario

## Instalación y Ejecución

### Prerrequisitos
1. Java 17
2. Maven
3. MySQL/MariaDB (XAMPP recomendado)

### Pasos de Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/ArturoCruzArm/inventario-web-app.git
   ```

2. Configurar base de datos:
   ```bash
   mysql -u root -pr00t -e "CREATE DATABASE IF NOT EXISTS inventario_db;"
   mysql -u root -pr00t inventario_db < SCRIPTS/full_setup.sql
   ```

3. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

4. Acceder a: http://localhost:8080

## Estructura del Proyecto

### Modelos de Datos
- **Usuario**: Gestión de usuarios y autenticación
- **Rol**: Roles del sistema (Administrador, Almacenista)
- **Producto**: Información de productos en inventario
- **HistorialInventario**: Registro de transacciones

### Controladores
- **LoginController**: Manejo de autenticación
- **AdminController**: Funciones administrativas
- **AlmacenController**: Gestión de inventario
- **HomeController**: Página principal

### Seguridad
- Configuración en `SecurityConfig.java`
- Encriptación de contraseñas con BCrypt
- Control de acceso basado en roles

## Funcionalidades

### Panel Administrativo
- Gestión completa de usuarios
- Reportes de inventario
- Historial de transacciones
- Configuración del sistema

### Panel de Almacén
- Registro de productos
- Actualización de stock
- Consulta de inventario
- Generación de reportes básicos

## Solución de Problemas

### Error de Autenticación
Si no puedes iniciar sesión:
1. Verificar que la base de datos esté corriendo
2. Confirmar que las tablas fueron creadas correctamente
3. Las contraseñas se actualizan automáticamente al iniciar la aplicación

### Error de Base de Datos
- Verificar conexión MySQL en puerto 3306
- Confirmar credenciales en `application.properties`
- Ejecutar script `full_setup.sql` si las tablas no existen

### Puerto en Uso
Si el puerto 8080 está ocupado:
- Cambiar puerto en `application.properties`: `server.port=8081`
- O detener el proceso que usa el puerto 8080

## Desarrollo

### Configuración de Desarrollo
- Spring Boot DevTools habilitado para recarga automática
- Perfil de desarrollo por defecto
- Logs SQL habilitados para depuración

### Estructura de Archivos
```
src/
├── main/
│   ├── java/com/inventario/inventariowebapp/
│   │   ├── config/          # Configuraciones
│   │   ├── controller/      # Controladores
│   │   ├── model/          # Modelos de datos
│   │   ├── repository/     # Repositorios JPA
│   │   └── service/        # Servicios de negocio
│   └── resources/
│       ├── static/         # CSS, JS, imágenes
│       └── templates/      # Plantillas Thymeleaf
```

## Contacto
- GitHub: https://github.com/ArturoCruzArm/inventario-web-app
- Desarrollado con Spring Boot y MySQL