package dao;

import model.Producto;
import java.util.List;

public interface ProductoDAO {
    List<Producto> obtenerTodos();
    Producto buscarPorCodigo(String codigoCastores);
    void actualizarStock(String codigoCastores, int cantidad);
}