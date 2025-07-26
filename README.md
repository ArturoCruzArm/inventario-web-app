# Aplicación Web de Gestión de Inventario

Este proyecto es una aplicación web para la gestión de inventario, desarrollada como parte de un ejercicio práctico.

## Datos Relevantes del Desarrollo

*   **IDE Utilizado:** IntelliJ IDEA (se puede adaptar a otros IDEs como Eclipse o VS Code)
*   **Versión del Lenguaje de Programación:** Java 17
*   **DBMS Utilizado y Versión:** MySQL 8.0
*   **Framework Web:** Spring Boot

## Pasos para Correr la Aplicación

Sigue estos pasos para configurar y ejecutar la aplicación en tu entorno local:

1.  **Clonar el Repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd inventario-web-app
    ```

2.  **Configurar la Base de Datos MySQL:**
    *   Asegúrate de tener un servidor MySQL 8.0 (o superior) en ejecución.
    *   Ejecuta el scripts SQL ubicado en la carpeta `SCRIPTS`:
        1.  `full_setup.sql`: Crea las tablas necesarias y las llena.

3.  **Configurar las Propiedades de Conexión a la Base de Datos:**
    *   Abre el archivo `src/main/resources/application.properties`.
    *   Actualiza las siguientes líneas con tus credenciales de MySQL:
        ```properties
        spring.datasource.username=TU_USUARIO_DE_MYSQL
        spring.datasource.password=TU_CONTRASEÑA_DE_MYSQL
        ```

4.  **Construir el Proyecto:**
    *   Abre una terminal en la raíz del proyecto (`inventario-web-app`).
    *   Ejecuta el siguiente comando Maven para construir el proyecto:
        ```bash
        mvn clean install
        ```

5.  **Ejecutar la Aplicación:**
    *   Desde la misma terminal, ejecuta la aplicación Spring Boot:
        ```bash
        mvn spring-boot:run
        ```
    *   La aplicación se iniciará y estará disponible en `http://localhost:8080`.

## Estructura del Repositorio

*   `SCRIPTS/`: Contiene los scripts SQL para la base de datos.
*   `src/`: Contiene el código fuente de la aplicación Java.
*   `README.md`: Este archivo, con la documentación del proyecto.# Sistema de Inventario Web - Versión Final
