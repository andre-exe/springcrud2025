
package com.adev.crud.service;

import java.util.List;
import java.util.Optional;


import com.adev.crud.model.Producto;

public interface ProductoService {
    List<Producto> listar();
    Producto guardar(Producto producto);
    void eliminar(Long id);
    Optional<Producto> obtener(Long id);
    
} 
