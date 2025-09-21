
package com.adev.crud.service;


import java.util.List;
import java.util.Optional;
import com.adev.crud.model.Producto;
import com.adev.crud.repository.ProductoRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listar() {
        return repository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Producto> obtener(Long id) {
        return repository.findById(id);
    }
    
}
