package model;

public class Producto {
    private int id;
    private String codigoCastores;  // Nuevo campo para Castores
    private String nombre;
    private int stock;
    private String ubicacion;       // Bodega A/B/C

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigoCastores() { return codigoCastores; }
    public void setCodigoCastores(String codigo) { this.codigoCastores = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}