package com.adev.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adev.crud.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
