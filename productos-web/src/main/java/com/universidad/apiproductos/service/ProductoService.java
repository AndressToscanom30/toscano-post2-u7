package com.universidad.apiproductos.service;

import com.universidad.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {

    private final Map<Long, Producto> productos = new LinkedHashMap<>();
    private Long contadorId = 1L;

    public ProductoService() {
        guardar(new Producto(null, "Laptop", "Laptop 15 pulgadas 16GB RAM", 1299.99));
        guardar(new Producto(null, "Mouse", "Mouse inalámbrico ergonómico", 29.99));
        guardar(new Producto(null, "Teclado", "Teclado mecánico TKL", 89.99));
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    // 🔥 CAMBIO CLAVE: ya no retorna Optional
    public Producto obtenerPorId(Long id) {
        Producto producto = productos.get(id);

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado: " + id);
        }

        return producto;
    }

    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    // 🔥 También validas aquí
    public void eliminar(Long id) {
        if (!productos.containsKey(id)) {
            throw new RuntimeException("Producto no encontrado: " + id);
        }
        productos.remove(id);
    }
}